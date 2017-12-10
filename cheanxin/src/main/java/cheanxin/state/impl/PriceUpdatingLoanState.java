package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * 待定价
 * Created by Jawinton on 2017/2/22.
 */
public class PriceUpdatingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_PRICE_UPDATE.name());
    public static final int status = 3;

    public PriceUpdatingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public int getStatus() {
        return PriceUpdatingLoanState.status;
    }

    /**
     * 只可修改贷款车辆相关的字段
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        if (unsavedLoan.getVehiclePredictPrice() != null) savedLoan.setVehiclePredictPrice(unsavedLoan.getVehiclePredictPrice());
        if (unsavedLoan.getVehicleBrand() != null) savedLoan.setVehicleBrand(unsavedLoan.getVehicleBrand());
        if (unsavedLoan.getVehicleSeries() != null) savedLoan.setVehicleSeries(unsavedLoan.getVehicleSeries());
        if (unsavedLoan.getVehicleType() != null) savedLoan.setVehicleType(unsavedLoan.getVehicleType());
        if (unsavedLoan.getVehicleVin() != null) savedLoan.setVehicleVin(unsavedLoan.getVehicleVin());
        if (unsavedLoan.getVehicleProductionYearMonth() != null) savedLoan.setVehicleProductionYearMonth(unsavedLoan.getVehicleProductionYearMonth());
        if (unsavedLoan.getVehicleKilometers() != null) savedLoan.setVehicleKilometers(unsavedLoan.getVehicleKilometers());
        if (unsavedLoan.getVehicleRegistrationYearMonth() != null) savedLoan.setVehicleRegistrationYearMonth(unsavedLoan.getVehicleRegistrationYearMonth());
        if (unsavedLoan.getVehicleEmission() != null) savedLoan.setVehicleEmission(unsavedLoan.getVehicleEmission());
        if (unsavedLoan.getVehicleUtilityType() != null) savedLoan.setVehicleUtilityType(unsavedLoan.getVehicleUtilityType());

        Assert.notNull(savedLoan.getVehiclePredictPrice(), "Vehicle predict price is empty.");
        Assert.notNull(savedLoan.getVehicleDealPrice(), "Vehicle deal price is empty.");
        Assert.isTrue(savedLoan.getVehiclePredictPrice() <= savedLoan.getVehicleDealPrice(), "Vehicle predict price cannot be greater than vehicle deal price.");
        Assert.notNull(savedLoan.getVehicleBrand(), "Vehicle brand is empty");
        Assert.notNull(savedLoan.getVehicleSeries(), "Vehicle series is empty");
        Assert.notNull(savedLoan.getVehicleType(), "Vehicle type is empty");
        Assert.notNull(savedLoan.getVehicleVin(), "Vehicle vin is empty");
        Assert.notNull(savedLoan.getVehicleProductionYearMonth(), "Vehicle production year month is empty");
        Assert.notNull(savedLoan.getVehicleKilometers(), "Vehicle kilometers is empty");
        Assert.notNull(savedLoan.getVehicleRegistrationYearMonth(), "Vehicle registration year month is empty");
        Assert.notNull(savedLoan.getVehicleEmission(), "Vehicle emission is empty.");
        Assert.notNull(savedLoan.getVehicleUtilityType(), "Vehicle utility type is empty.");
        return super.updateLoan(user, unsavedLoan, savedLoan, false);
    }

    /**
     * 提交之后转到待复审
     * @param user
     * @param unsavedLoan
     * @param savedLoan
     * @return
     */
    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        Assert.notNull(savedLoan.getVehiclePredictPrice(), "Vehicle predict price is empty.");
        Assert.notNull(savedLoan.getVehicleBrand(), "Vehicle brand is empty");
        Assert.notNull(savedLoan.getVehicleSeries(), "Vehicle series is empty");
        Assert.notNull(savedLoan.getVehicleType(), "Vehicle type is empty");
        Assert.notNull(savedLoan.getVehicleVin(), "Vehicle vin is empty");
        Assert.notNull(savedLoan.getVehicleProductionYearMonth(), "Vehicle production year month is empty");
        Assert.notNull(savedLoan.getVehicleKilometers(), "Vehicle kilometers is empty");
        Assert.notNull(savedLoan.getVehicleRegistrationYearMonth(), "Vehicle registration year month is empty");
        Assert.notNull(savedLoan.getVehicleEmission(), "Vehicle emission is empty.");
        Assert.notNull(savedLoan.getVehicleUtilityType(), "Vehicle utility type is empty.");

        return super.updateLoanStatus(user, unsavedLoan, savedLoan, SecondReviewingLoanState.status);
    }

    @Override
    public Authority[] getReadAuthorities() {
        return new Authority[]{Authority.ROLE_LOAN_PRICE_READ, Authority.ROLE_FIRST_REVIEWER, Authority.ROLE_APPRAISER};
    }
}
