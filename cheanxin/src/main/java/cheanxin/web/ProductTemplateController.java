package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Product;
import cheanxin.domain.ProductTemplate;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.service.DeptCityService;
import cheanxin.service.ProductService;
import cheanxin.service.ProductTemplateService;
import cheanxin.service.UserPostService;
import cheanxin.util.ReflectUtil;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 17/01/07.
 */
@RestController
@RequestMapping("/product_templates")
public class ProductTemplateController extends BaseController {
    @Autowired
    private ProductTemplateService productTemplateService;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<ProductTemplate> list(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user) {
        // TODO
        return productTemplateService.list(name, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ProductTemplate> get(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        ProductTemplate productTemplate = productTemplateService.findOne(id);
        Assert.notNull(productTemplate, "Product template not found.");
        return new ResponseEntity<>(productTemplate, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ProductTemplate> save(
            @Valid @RequestBody ProductTemplate unsavedProductTemplate,
            Errors errors,
            @AuthenticationPrincipal User user) {
        assertFieldError(errors);

        Assert.isTrue(LoanPolicy.contains(unsavedProductTemplate.getLoanPolicy().intValue()), "Product's loan policy does not exist.");
        Assert.isTrue(ProductType.contains(unsavedProductTemplate.getProductType().intValue()), "Product's product type does not exist.");
        Assert.isTrue(RepaymentMethod.contains(unsavedProductTemplate.getRepaymentMethod().intValue()), "Product's repayment method does not exist.");
        Assert.isTrue(unsavedProductTemplate.getMinAvailableRate() <= unsavedProductTemplate.getMaxAvailableRate(), "Product's min available rate should be less than max available rate.");

        // TODO

        long now = System.currentTimeMillis() / 1000;
        unsavedProductTemplate.setCreatorUsername(user.getUsername());
        unsavedProductTemplate.setCreatedTime(now);
        unsavedProductTemplate.setModifiedTime(now);
        return new ResponseEntity<>(productTemplateService.save(unsavedProductTemplate), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        ProductTemplate savedProductTemplate = productTemplateService.findOne(id);

        Assert.notNull(savedProductTemplate);
        Assert.isTrue(user.getUsername().equals(savedProductTemplate.getCreatorUsername()), "You are not the owner of this product");
        Assert.isTrue(!productService.hasChildProducts(id), "This product template already has child product.");

        // TODO

        productTemplateService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ProductTemplate> patch(
            @PathVariable(value = "id") long id,
            @RequestBody ProductTemplate unsavedProductTemplate,
            @AuthenticationPrincipal User user) throws IllegalAccessException {
        // TODO

        unsavedProductTemplate.setId(id);
        unsavedProductTemplate.setCreatorUsername(null);
        unsavedProductTemplate.setCreatedTime(null);
        unsavedProductTemplate.setModifiedTime(System.currentTimeMillis() / 1000);

        ProductTemplate savedProductTemplate = productTemplateService.findOne(id);
        Assert.notNull(savedProductTemplate, "ProductTemplate not found");

        ReflectUtil.mergeObject(unsavedProductTemplate, savedProductTemplate);


        return new ResponseEntity<>(productTemplateService.save(savedProductTemplate), HttpStatus.OK);
    }
}
