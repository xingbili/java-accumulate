/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp 公函
 * 
 * @author yangfan
 * @date 2021/04/13
 */
@Slf4j
public class OkHttpUtils {
    /**
     * 保证OkHttpClient是唯一的
     */
    private static OkHttpClient okHttpClient;

    private static HttpLoggingInterceptor logInterceptor =
        new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                try {
                    message = message.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                    String text = URLDecoder.decode(message, "utf-8");
                    log.info("OKHttp-----{}", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    log.error("OKHttp-----{}", message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

    static {
        if (okHttpClient == null) {
            okHttpClient =
                new OkHttpClient.Builder().addInterceptor(logInterceptor).connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
                    // 配置
                    .sslSocketFactory(work.xingbili.common.utils.SslSocketClient.getSslSocketFactory(), SslSocketClient.getX509TrustManager())
                    .hostnameVerifier(SslSocketClient.getHostnameVerifier()).build();
        }
    }

    /**
     * GET请求
     *
     * @param url
     *            get请求地址
     * @return bodyStr
     * @throws IOException
     *             IO异常
     */
    public static String sendGet(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response == null || !response.isSuccessful()) {
                log.error("GET 请求失败，URL:{}, RESPONSE:{}", url, JSON.toJSON(response));
            }
            return response.body().string();
        } catch (IOException e) {
            log.error("GET 请求失败，URL:{}", url, e);
        }
        return null;
    }

    /**
     * Form请求
     *
     * @param url
     *            请求地址
     * @param formBody
     *            表单参数
     * @return bodyStr
     */
    public static String sendPostForm(String url, FormBody formBody) {
        Request request = new Request.Builder().url(url).post(formBody).build();
        // 创建/Call
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response == null || !response.isSuccessful()) {
                log.error("POST 请求失败，URL:{}, RESPONSE:{}", JSON.toJSON(response));
            }
            return response.body().string();
        } catch (IOException e) {
            log.error("POST请求失败,URL:{}" + url, e);
        }
        return null;
    }

    /**
     * Json请求
     *
     * @param url
     *            请求地址
     * @param json
     *            json请求参数
     * @return 响应数据
     */
    public static Response sendPostJson(String url, String json) {
        // MediaType 设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        // 创建/Call
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response == null || !response.isSuccessful()) {
                log.error("POST JSON 请求失败，URL:{},JSON:{}, RESPONSE:{}", url, json, JSON.toJSON(response));
            }
        } catch (IOException e) {
            log.error("POST JSON 请求失败，URL:{}", url, e);
        }
        return response;
    }

    /**
     * Json请求
     *
     * @param url
     *            请求地址
     * @param json
     *            json请求参数
     * @return bodyStr
     */
    public static String sendPostJsonToStr(String url, String json) {
        // MediaType 设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        // 创建/Call
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response == null || !response.isSuccessful()) {
                log.error("POST JSON 请求失败，URL:{},JSON:{}, RESPONSE:{}", url, json, JSON.toJSON(response));
            }
            return response.body().string();
        } catch (IOException e) {
            log.error("POST JSON 请求失败，URL:{}", url, e);
        }
        return null;
    }
}
