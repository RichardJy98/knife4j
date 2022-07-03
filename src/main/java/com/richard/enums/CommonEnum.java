package com.richard.enums;

import lombok.Getter;

/**
 * @author Richard
 * @version 1.0
 * @description 公共枚举类
 * @date 2021/10/19 9:40
 */
@Getter
public enum CommonEnum {

    /**
     * xml格式调用NC接口返回成功编码
     */
    SUCCESS(1, "1"),

    /**
     * 调用新增接口
     */
    ADD(2, "ADD"),

    /**
     * 调用修改接口
     */
    UPDATE(3, "UPDATE"),

    /**
     * 编码
     */
    CODE(4, "code"),

    /**
     * 子项目
     */
    ITEM(5, "item");

    private final Integer code;

    private final String desc;

    CommonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
