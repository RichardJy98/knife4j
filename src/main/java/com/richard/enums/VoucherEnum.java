package com.richard.enums;

import lombok.Getter;

/**
 * @author Richard
 * @version 1.0
 * @description 凭证的枚举类
 * @date 2021/6/2 10:35
 */
@Getter
public enum VoucherEnum {

    /**
     * 借
     */
    BORROW(0,"0"),

    /**
     * 贷
     */
    LOAN(1,"1"),

    /**
     * 组织(门店)编码
     */
    ORGCODE(3,"orgcode"),

    /**
     * 科目编码
     */
    ACCASOACODE(4,"accasoacode"),

    /**
     * 会计辅助核算项目编码
     */
    ACCASSITEMCODE(5,"accassitemcode"),

    /**
     * 科目主键
     */
    PK_ACCASOA(6,"pk_accasoa"),

    /**
     * 科目表主键
     */
    PK_ACCCHART(7,"pk_accchart"),

    /**
     * 用户传递json的辅助核算项集合主键
     */
    ACCASSITEMS(8,"accassitems"),

    /**
     * 业务日期
     */
    VERIFYDATE(9,"verifydate"),

    /**
     * 金额
     */
    MONEY(10,"money"),

    /**
     * 币种
     */
    PK_CURRTYPE(11,"pk_currtype"),

    /**
     * 辅助核算主键
     */
    PK_ACCASSITEM(12,"pk_accassitem"),

    /**
     * 会计科目借贷方向
     */
    BALANORIENT(13,"balanorient"),

    /**
     * 科目方向标识,0是借,1是贷
     */
    DIRECTION(14,"direction"),

    /**
     * 凭证主键
     */
    PK_VOUCHER(15,"pk_voucher"),

    /**
     * 摘要
     */
    EXPLANATION(16,"explanation"),

    /**
     * gl_voucher表中的自定义字段,用来存放ERP传递的凭证号
     */
    FREE2(17,"free2"),

    /**
     * 账簿编码
     */
    BOOKCODE(18,"bookcode"),

    /**
     * 组织主键(成本域)
     */
    PK_ORG(19, "pk_org");


    private final Integer id;
    private final String desc;

    VoucherEnum(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
