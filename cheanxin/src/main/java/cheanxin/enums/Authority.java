package cheanxin.enums;

/**
 * 权限
 * Created by Jawinton on 17/01/03.
 */
public enum Authority {
    // 按岗位划定的权限
    ROLE_ADMIN, // 超级管理员
    ROLE_SELLER, // 金融专员
    ROLE_FIRST_REVIEWER, // 初审
    ROLE_SECOND_REVIEWER, // 复审
    ROLE_FINANCE, // 财务
    ROLE_APPRAISER, // 评估师

    // 贷款审核相关权限
    ROLE_LOAN_DRAFT_UPDATE,
    ROLE_LOAN_FIRST_REVIEW,
    ROLE_LOAN_PRICE_UPDATE,
    ROLE_LOAN_SECOND_REVIEW,
    ROLE_LOAN_MATERIALS_UPDATE,
    ROLE_LOAN_CONTRACT_REVIEW,
    ROLE_LOAN_SCHEME_UPDATE,
    ROLE_LOAN_CONTRACT_ABORT_REVIEW,
    ROLE_LOAN_CONTRACT_ACCEPT_REVIEW,
    ROLE_LOAN_SCHEME_UPDATE_REVIEW,
    ROLE_LOAN_TRANSFER_UPDATE,
    ROLE_LOAN_TRANSFER_ABORT_REVIEW,
    ROLE_LOAN_RELEASE_REVIEW,
    ROLE_LOAN_RELEASE_UPDATE,
    ROLE_LOAN_RELEASE_MORTGAGE_UPDATE,
    ROLE_LOAN_RELEASE_MORTGAGE_REVIEW,
    ROLE_LOAN_MORTGAGE_UPDATE,
    ROLE_LOAN_MORTGAGE_ABORT_REVIEW,
    ROLE_LOAN_MORTGAGE_RELEASE_REVIEW,
    ROLE_LOAN_MORTGAGE_RELEASE_UPDATE,

    ROLE_LOAN_DRAFT_READ,
    ROLE_LOAN_FIRST_READ,
    ROLE_LOAN_PRICE_READ,
    ROLE_LOAN_SECOND_READ,
    ROLE_LOAN_CONTRACT_READ,
    ROLE_LOAN_CONTRACT_ACCEPT_READ,
    ROLE_LOAN_CONTRACT_ABORT_READ,
    ROLE_LOAN_RELEASE_READ,
    ROLE_LOAN_TRANSFER_ABORT_READ,
    ROLE_LOAN_RELEASE_UPDATE_READ,
    ROLE_LOAN_TRANSFER_READ,
    ROLE_LOAN_RELEASE_MORTGAGE_READ,
    ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ,
    ROLE_LOAN_MORTGAGE_READ,
    ROLE_LOAN_MORTGAGE_ABORT_READ,
    ROLE_LOAN_MORTGAGE_RELEASE_READ,

    // 产品相关权限
    ROLE_PRODUCT_TEMPLATE_READ,
    ROLE_PRODUCT_READ,
    ROLE_PRODUCT_CITY_READ,
    ROLE_PRODUCT_REVIEW,
    ROLE_PRODUCT_UPDATE
}