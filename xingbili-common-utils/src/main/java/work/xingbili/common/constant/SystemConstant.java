/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.constant;


import com.google.common.collect.ImmutableSet;
import work.xingbili.common.enums.ChannelCodeEnum;

/**
 * <p>
 * 系统常量
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
public class SystemConstant {

    /**
     * 系统用户
     */
    public static final Long SYSTEM_USER_ID = 0L;
    
    /**
     * TOKEN超时时间,毫秒
     */
    public final static long TOKEN_EXP_TIME = 1 * 3600 * 1000;

    /**
     * INNER TOKEN超时时间,毫秒
     */
    public final static long INNER_TOKEN_EXP_TIME =300 * 1000;
    
    /**
     * TOKEN刷新时间,毫秒
     */
    public final static long TOKEN_REFRESH_TIME = 1800 * 1000;
    
    /**
     * jwt token中的loginName
     */
    public static final String TOKEN_LOGINNAME = "loginName";

    /**
     * jwt token中的userId
     */
    public static final String TOKEN_USERID = "userId";

    /**
     * jwt token中的登录ip
     */
    public static final String TOKEN_IP = "loginIp";

    /**
     * token创建时间
     */
    public static final String TOKEN_CREATEDATE = "createDate";

    /**
     * token的变量名
     */
    public static final String TOKEN_NAME = "authorization";
    
    /**
     * 渠道码
     */
    public static final String TOKEN_CHANNELCODE = "channelCode";

    /**
     * orgId
     */
    public static final String TOKEN_ORG_ID = "orgId";
    
    /**
     * orgCode
     */
    public static final String TOKEN_ORG_CODE = "orgCode";

    /**
     * 默认权限
     */
    public static final String DEFAULT_AUTH = "default";
    
    /**
     * 密码失效天数
     */
    public static String PASSWORD_EXPIRE_DAYS = "PASSWORD_EXPIRE_DAYS";
    
    
    /**
     * 密码失败尝试次数
     */
    public static String PASSWORD_FAIL_TRY_TIMES = "PASSWORD_FAIL_TRY_TIMES";
    
    /**
     * 营业日
     */
    public static String BUSS_DATE = "BUSS_DATE";
    
    /**
     * 客服电话
     */
    public static String TELEPHONE = "TELEPHONE";


    /**
     * 平台内部户
     */
    public static String PLATFORM_INTERIOR = "PLATFORM_INTERIOR";

    /**
     * 平台名称
     */
    public static String PLAT_NAME = "PLAT_NAME";

    /**
     * 盐值byte长度
     */
    public static final int SALT_SIZE = 16;
    
    /**
     * hash次数
     */
    public static final int HASH_INTERATIONS = 1024;
    
    /**
     * 访问bank-auth渠道集合
     */
    public final static ImmutableSet<String> BANK_AUTH_CHANNEL_SET =
        ImmutableSet.of(ChannelCodeEnum.BANK_UMS.getCode(), ChannelCodeEnum.PROTAL_OPERATION.getCode(),
            ChannelCodeEnum.BANK_VOUCHER.getCode(), ChannelCodeEnum.BANK_WORKFLOW.getCode());
    
    /**
     * 访问corp-auth渠道集合
     */
    public final static ImmutableSet<String> CORP_AUTH_CHANNEL_SET =
        ImmutableSet.of(ChannelCodeEnum.PROTAL_CORP.getCode());
    
    /**
     * 是否行内渠道
     * 
     * @param channelCode
     * @return
     */
    public final static boolean isBankChannel(String channelCode) {
       return BANK_AUTH_CHANNEL_SET.contains(channelCode);
    }
    
    /**
     * 是否企业渠道
     * 
     * @param channelCode
     * @return
     */
    public final static boolean isCorpChannel(String channelCode) {
        return CORP_AUTH_CHANNEL_SET.contains(channelCode);
     }
}
