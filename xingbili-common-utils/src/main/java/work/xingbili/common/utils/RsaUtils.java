/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * RSA加签验签公函
 * 
 * @author yangfan
 * @date 2021/04/13
 */
public class RsaUtils {

    public static final String RSA_ALGORITHM = "RSA";

    public static final String PUBLIC_KEY = "publicKey";

    public static final String PRIVATE_KEY = "privateKey";

    public static final String CHARSET = "UTF-8";

    /**
     * 生成公钥私钥密钥对
     * 
     * @param keySize
     * @return
     */
    public static Map<String, String> createKeys(int keySize) {
        int mapInitSize = 10;
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        System.out.println("publicKeyStr: " + publicKeyStr);
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        System.out.println("privateKeyStr: " + privateKeyStr);
        Map<String, String> keyPairMap = new HashMap<String, String>(mapInitSize);
        keyPairMap.put(PUBLIC_KEY, publicKeyStr);
        keyPairMap.put(PRIVATE_KEY, privateKeyStr);
        return keyPairMap;
    }

    /**
     * 获取公钥
     * 
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static RSAPublicKey getPublicKey(String publicKey)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey)keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 获取私钥
     * 
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static RSAPrivateKey getPrivateKey(String privateKey)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey)keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * 
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                rsaPublicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * 
     * @param data
     * @param privateKeyStr
     * @return
     */
    public static String privateDecrypt(String data, String privateKeyStr) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKeyStr);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
                rsaPrivateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * 
     * @param data
     * @param privateKeyStr
     * @return
     */
    public static String privateEncrypt(String data, String privateKeyStr) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKeyStr);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                rsaPrivateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * 
     * @param data
     * @param publicKeyStr
     * @return
     */
    public static String publicDecrypt(String data, String publicKeyStr) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            RSAPublicKey rsaPublicKey = getPublicKey(publicKeyStr);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
                rsaPublicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 加解密方法
     * 
     * @param cipher
     * @param opmode
     * @param datas
     * @param keySize
     * @return
     */
    @SuppressWarnings("deprecation")
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }

        int offSet = 0;
        byte[] buff;
        int i = 0;
        byte[] resultDatas = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            resultDatas = out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        return resultDatas;
    }

    /**
     * 加签
     * 
     * @param content
     * @param privateKey
     * @param charset
     * @return
     */
    public static String rsaSign(String content, String privateKey, String charset) {
        try {
            RSAPrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (InvalidKeySpecException ie) {
            throw new RuntimeException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的密钥", ie);
        } catch (Exception e) {
            throw new RuntimeException("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    /**
     * 验签
     * 
     * @param content
     * @param sign
     * @param publicKey
     * @param charset
     * @return
     */
    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) {
        try {
            RSAPublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 功 能： 按照加签规则生成加签的字符串 加签规则如下： 1. 字段按照英文字母排序 2. 报文字段采用 key1=val1&key2=val2方式拼接 3. 字段值为空或者空白字符不参与加签 4.
     * 存在子节点的报文也按照以上规则处理,子节点生成的拼接串如下key1=val1&key2={subkey1=subval1&subkey2=subval2}&key3=val3
     * 
     * @param map
     * @return
     */
    public static String buildSignContent(Map<String, Object> map) {
        // 排序处理规则
        Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        // 使用TreeMap根据排序规则排序
        Map<String, Object> sortMap = new TreeMap<String, Object>(cmp);
        sortMap.putAll(map);

        // 字符串拼接
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            // 跳过验签
            if (val == null) {
                continue;
            }
            // 复杂对象跳过，目前只有以下两种，后续若有其他的可以在此添加
            if (val instanceof Map || val instanceof List) {
                continue;
            } else {
                if (StringUtils.isNotBlank(String.valueOf(val))) {
                    sb.append(key).append("=").append(String.valueOf(val)).append("&");
                }
            }
        }

        String content = sb.toString();
        // 拼接字符串处理
        return content.substring(0, content.length() - 1);
    }

    // public static void main(String[] args) {
    //
    // //生成1024位的公私钥对， 实际使用只需生成一次, 外部渠道需将公钥提供到聚钧
    // Map<String, String> map = createKeys(1024);
    // //构建请求报文
    // Map<String, Object> reqmap = new HashMap<String, Object>();
    // reqmap.put("channelCode", "c001");
    // reqmap.put("channelReqSeq", "F20190802001");
    // reqmap.put("txnCode", "Q0000001");
    // reqmap.put("txnTime", "20190802120000");
    // reqmap.put("oriChannelSeqNo", "F20190801001");
    //
    // //签名内容
    // String signContent = buildSignContent(reqmap);
    // System.out.println("签名内容为:"+signContent);
    //
    // //公钥
    // String pubKey = map.get(PUBLIC_KEY);
    // //私钥
    // String priKey = map.get(PRIVATE_KEY);
    //
    // System.out.println("私钥为:"+ priKey);
    // System.out.println("公钥为:"+ pubKey);
    // //对签名内容加密
    // String sign = rsaSign(signContent, priKey,"utf-8");
    // System.out.println("签名内容加签后为:"+sign);
    // System.out.println("验签结果为:"+rsaCheckContent(signContent, sign, pubKey, "utf-8"));
    // }
}
