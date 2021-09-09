package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 产品类型
 * 
 * @author TGB
 * @date 2021/05/18
 */
@Getter
@AllArgsConstructor
public enum ProductCodeEnum {

    /**
     * 电子债权凭证
     */
    VOUCHER("060", "橙信"),

    /**
     * 预付款采购订单融资
     */
    POS("061", "商链通"),

    ;

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String info;

    /**
     *  通过编码获取描述
     *
     * @param code
     * @return
     */
    public static String getInfoByCode(String code) {
        for (ProductCodeEnum item : ProductCodeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getInfo();
            }
        }
        return null;
    }

    /**
     * 通过描述获取编码
     *
     * @author liuming1
     * @date 2021/07/26 16:57
     * @param info
     * @return String
     */
    public static String getCodeByName(String info) {
        for (ProductCodeEnum item : ProductCodeEnum.values()) {
            if (item.getInfo().equals(info)) {
                return item.getCode();
            }
        }
        return null;
    }

}
