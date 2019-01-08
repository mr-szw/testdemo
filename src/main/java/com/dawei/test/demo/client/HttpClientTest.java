package com.dawei.test.demo.client;

import com.alibaba.fastjson.JSON;
import com.daojia.gunpowder.feature.httpclient.HttpClientExecutor;
import com.daojia.gunpowder.feature.httpclient.HttpConstant;
import com.daojia.gunpowder.feature.httpclient.builder.HttpClientConfig;
import com.daojia.gunpowder.feature.httpclient.model.HttpRequestBody;
import com.daojia.gunpowder.feature.httpclient.model.HttpRequestEntity;
import com.daojia.gunpowder.feature.httpclient.model.HttpResponseEntity;
import com.google.common.base.Charsets;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by Dawei on 2018/6/5.
 */
public class HttpClientTest {


    public static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    public static void main(String[] args) {



       String get = new HttpClientTest().doRequestFunction("GET", null, "https://jz-csapi.djtest.cn/djwechat/clue/demand/56");

        System.out.println(get);

        Map<String, String> stringStringMap = JSON.parseObject(get, Map.class);
        System.out.println(stringStringMap);
        Map<String, String> stringStringMap1 = JSON.parseObject(stringStringMap.get("data"), Map.class);
        Map<String, String> stringStringMap2 = JSON.parseObject(stringStringMap1.get("content"), Map.class);
        Map<String, String> stringStringMap3 = JSON.parseObject(stringStringMap2.get("content"), Map.class);


        // new HttpClientTest().doRequest();
    }


    //http 客户端配置
    private final HttpClientConfig httpClientConfig = new HttpClientConfig();
    //http 客户端连接池
    private final HttpClientExecutor httpClientExecutor = HttpClientExecutor.getInstance();


    public void doRequest() {
        //String urlPath = "http://192.168.150.24/lottery/drawn";
        String urlPath = "http://lottery.dawei.com/lottery/drawn";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> nameValuePairList = new ArrayList<>();
        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("activityId", "12314");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("passportId", "12314");
        nameValuePairList.add(basicNameValuePair1);
        nameValuePairList.add(basicNameValuePair2);
        try {
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
            String params = EntityUtils.toString(encodedFormEntity);
            HttpGet httpGet = new HttpGet(urlPath + "?" + params);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public  String doRequestFunction(String methodType, Object parameter, String requestUrl) {

        T matchingResultDtoList = null;
        HttpRequestEntity request = new HttpRequestEntity();

        if(methodType.equalsIgnoreCase("GET")) {
            request.setMethod(HttpConstant.Method.GET);
        } else if(methodType.equalsIgnoreCase("POST")) {
            request.setMethod(HttpConstant.Method.POST);
        }
        request.setUrl(requestUrl);
        try {
            request.setRequestBody(HttpRequestBody.json(JSON.toJSONString(parameter), Charsets.UTF_8.name()));
            request.addCookie("dj_pstoken", "MEKdAHwH8Cmx8vkz6XicttAqHDuQgL1XPA9yrPSV57Qsw3hiswDVLootchTTRCiXXAYkoImkN6Sw1NBQo2JMeBjUU%252B3tGUkzJ0%252Bkm%252F6ggg2t6ZQXXceAdY1LVC2%252BH95TLsZQtyG%252BXND1q3Qo5wFDgJ5Fru51Mn3vp7bfpyBLWSY%253D");
            request.addCookie("dj_psuid", "-3439624116067433777_1040512509859209216");
            request.addCookie("dj_pstokenexp", "2592000");

        } catch (UnsupportedEncodingException e) {
            logger.error("参数转化异常， e=", e);
        }
        httpClientConfig.setSocketTimeout(20000);
        HttpResponseEntity responseEntity = httpClientExecutor.execute(request, httpClientConfig);
        if (responseEntity != null && responseEntity.isSuccess() && responseEntity.getStatus() == HttpConstant.StatusCode.CODE_200) {
            System.out.println(responseEntity.toString());
            System.out.println(responseEntity.getRawText());
        }
        return responseEntity.getRawText();
    }







}
