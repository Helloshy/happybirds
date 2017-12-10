package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Product;
import cheanxin.domain.ProductTemplate;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.service.ProductService;
import cheanxin.service.ProductTemplateService;
import cheanxin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 17/01/07.
 */
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTemplateService productTemplateService;

    public final static Authority[] PRODUCT_READ_AUTHORITIES = new Authority[]{
            Authority.ROLE_ADMIN,
            Authority.ROLE_PRODUCT_READ,
            Authority.ROLE_PRODUCT_CITY_READ,
            Authority.ROLE_FIRST_REVIEWER,
            Authority.ROLE_SECOND_REVIEWER
    };

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> list(
            @RequestParam(value = "cityId", defaultValue = "0") long cityId,
            @RequestParam(value = "productTemplateId", defaultValue = "-1") long productTemplateId,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user) {
        Set<Long> cityIds = getReadAuthorityCities(user, cityId, PRODUCT_READ_AUTHORITIES);
        return productService.list(cityIds, productTemplateId, name, status, page, size);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> list(
            @RequestParam(value = "cityId") long cityId,
            @AuthenticationPrincipal User user) {
        Assert.isTrue(user.getCityIds().contains(cityId), "Product city error.");
        return productService.list(cityId, ProductStatus.ACCEPTED.value().intValue(), true);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> get(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        Product product = productService.findOne(id);
        Assert.notNull(product, "Product not found.");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Product> save(
            @Valid @RequestBody Product unsavedProduct,
            Errors errors,
            @AuthenticationPrincipal User user) {
        assertFieldError(errors);

        Set<Long> cityIds = getReadAuthorityCities(user, -1L, PRODUCT_READ_AUTHORITIES);
        Assert.isTrue(cityIds == null || cityIds.contains(unsavedProduct.getCityId()), "Product city error.");

        long now = System.currentTimeMillis() / 1000;
        unsavedProduct.setCreatorUsername(user.getUsername());
        unsavedProduct.setCreatedTime(now);
        unsavedProduct.setModifiedTime(now);
        unsavedProduct.setEnabled(true);
        return new ResponseEntity<>(save(unsavedProduct), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Product unsavedProduct,
            Errors errors,
            @AuthenticationPrincipal User user) {
        assertFieldError(errors);

        Set<Long> cityIds = getReadAuthorityCities(user, -1L, PRODUCT_READ_AUTHORITIES);
        Assert.isTrue(cityIds == null || cityIds.contains(unsavedProduct.getCityId()), "Product city error.");

        Product savedProduct = productService.findOne(id);
        Assert.notNull(savedProduct, "Product not found.");
        Assert.isTrue(savedProduct.getCreatorUsername().equals(user.getUsername()), "Current user is not owner of this product.");
        Assert.isTrue(savedProduct.getStatus().intValue() == ProductStatus.PENDING_REVIEW.value(), "Only product of status pending review can be modified.");

        // attributes below can not be modified.
        unsavedProduct.setId(id);
        unsavedProduct.setProductTemplateId(savedProduct.getProductTemplateId());
        unsavedProduct.setStatus(savedProduct.getStatus());
        unsavedProduct.setCreatorUsername(savedProduct.getCreatorUsername());
        unsavedProduct.setCreatedTime(savedProduct.getCreatedTime());
        unsavedProduct.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedProduct.setEnabled(savedProduct.getEnabled());

        return new ResponseEntity<>(save(unsavedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Product> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Product unsavedProduct,
            @AuthenticationPrincipal User user) {
        //审批通过的时候，备注无需填写
//        Assert.isTrue(unsavedProduct.getRemark() != null && !unsavedProduct.getRemark().trim().isEmpty(), "Remark should not be empty.");

        Product savedProduct = productService.findOne(id);
        Assert.notNull(savedProduct, "Product not found");

        return new ResponseEntity<>(productService.review(user, savedProduct, unsavedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PATCH)
    public ResponseEntity<Product> enable(
            @PathVariable(value = "id") long id) {
        return new ResponseEntity<>(enableOrDisableProduct(id, true), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/disable", method = RequestMethod.PATCH)
    public ResponseEntity<Product> disable(
            @PathVariable(value = "id") long id) {
        return new ResponseEntity<>(enableOrDisableProduct(id, false), HttpStatus.OK);
    }

    private Product enableOrDisableProduct(long id, boolean enabled) {
        Product savedProduct = productService.findOne(id);
        Assert.notNull(savedProduct, "Product not found");
        Assert.isTrue(savedProduct.getStatus() == ProductStatus.ACCEPTED.value(), "Product status error.");

        savedProduct.setEnabled(enabled);

        return save(savedProduct);
    }

    private Product save(Product product) {
        Assert.isTrue(LoanPolicy.contains(product.getLoanPolicy().intValue()), "Product's loan policy does not exist.");
        Assert.isTrue(ProductType.contains(product.getProductType().intValue()), "Product's product type does not exist.");
        Assert.isTrue(RepaymentMethod.contains(product.getRepaymentMethod().intValue()), "Product's repayment method does not exist.");
        Assert.isTrue(product.getMinAvailableRate() <= product.getMaxAvailableRate(), "Product's min available rate should be less than max available rate.");

        Assert.notNull(product.getProductTemplateId(), "Product template id cannot be null.");
        ProductTemplate productTemplate = productTemplateService.findOne(product.getProductTemplateId().longValue());
        Assert.notNull(productTemplate, "Product template not found.");

        Assert.isTrue(product.getMinAvailableRate() >= productTemplate.getMinAvailableRate(), "Product's min available rate should be greater than product template's min available rate.");
        Assert.isTrue(product.getMaxAvailableRate() <= productTemplate.getMaxAvailableRate(), "Product's max available rate should be less than product template's max available rate.");
        Assert.isTrue(StringUtil.containsAll(productTemplate.getAvailableTerms(), product.getAvailableTerms()), "Product's terms are unavailable.");
        Assert.isTrue(product.getCityId() > 0L, "Product's city id should be assigned.");
        Assert.notNull(product.getMaxAvailableVehicleYear(), "Product's max available vehicle year cannot be null.");

        return productService.save(product);
    }
}
