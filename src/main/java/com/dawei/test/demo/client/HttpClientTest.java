package com.dawei.test.demo.client;

import com.alibaba.fastjson.JSON;
import com.daojia.gunpowder.feature.httpclient.HttpClientExecutor;
import com.daojia.gunpowder.feature.httpclient.HttpConstant;
import com.daojia.gunpowder.feature.httpclient.builder.HttpClientConfig;
import com.daojia.gunpowder.feature.httpclient.model.HttpRequestBody;
import com.daojia.gunpowder.feature.httpclient.model.HttpRequestEntity;
import com.daojia.gunpowder.feature.httpclient.model.HttpResponseEntity;
import com.dawei.test.demo.pojo.CsClueHttpRequestDto;
import com.google.common.base.Charsets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author by Dawei on 2018/6/5.
 */
public class HttpClientTest {


    public static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    public static void main(String[] args) {
        //new HttpClientTest().doRequest();
        Map<String, Object> paramterMap = new HashMap<>();
        //paramterMap.put("demandId", "3");
        paramterMap.put("category", "212");
        //paramterMap.put("hmsr", "JzApp");
        paramterMap.put("cpid", "357647970730583");
       // String rawText = new HttpClientTest().doRequestFunction("POST", paramterMap, "https://jz-csapi.djtest.cn/customer/online/demand/content");
       // String rawText = new HttpClientTest().doRequestFunction("POST", paramterMap, "https://jz-csapi.djtest.cn/customer/online/demand/broadcast");
        String rawText = new HttpClientTest().doRequestFunction("POST", paramterMap, "https://jz-csapi.djtest.cn/customer/online/demand/designate");

        Map map = JSON.parseObject(rawText, Map.class);



        // new HttpClientTest().doRequest();
    }


    //http 客户端配置
    private final HttpClientConfig httpClientConfig = new HttpClientConfig();
    //http 客户端连接池
    private final HttpClientExecutor httpClientExecutor = HttpClientExecutor.getInstance();


    public void doRequest() {
        //String urlPath = "http://192.168.150.24/lottery/drawn";
        //String urlPath = "http://lottery.dawei.com/lottery/drawn";
        //String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/content";
        //String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/detail";
        String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/designate";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> nameValuePairList = new ArrayList<>();
//        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("activityId", "12314");
//        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("passportId", "12314");
//        nameValuePairList.add(basicNameValuePair1);
//        nameValuePairList.add(basicNameValuePair2);

       // BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("demandId", "3");
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("cpid", "357647970730583");
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("category", "212");
        nameValuePairList.add(basicNameValuePair3);
        nameValuePairList.add(basicNameValuePair4);
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


    public String doRequestFunction(String methodType, Map<String, Object> parameter, String requestUrl) {

        HttpRequestEntity request = new HttpRequestEntity();

        if (methodType.equalsIgnoreCase("GET")) {
            request.setMethod(HttpConstant.Method.GET);
        } else if (methodType.equalsIgnoreCase("POST")) {
            request.setMethod(HttpConstant.Method.POST);
        }

        request.setUrl(requestUrl);
        try {
            //request.setRequestBody(HttpRequestBody.json(JSON.toJSONString(parameter), Charsets.UTF_8.name()));
            request.setRequestBody(HttpRequestBody.form(parameter, Charsets.UTF_8.name()));

         //   request.addHeader("Content-Type", "application/x-www-form-urlencoded");
//            request.addHeader("Cookie", "dj_pstoken=FjsGKkC1vSdArxKlPHaj07XM3GIBKLKwLy2P451JETYk%252BQ5%252BZgsImviXFqniAaS%252BH9zpqjAm651REqsAwbKk2osvmCNG9KpIM9spdiiKpd05yImMoPVlfg%253D%253D");
//            request.addHeader("Cookie", "dj_psuid=6360208644320278992_985721051373772800");
            request.addHeader("Cookie", "dj_pstokenexp=2592000");
            //request.addCookie("dj_pstoken",                "FjsGKkC1vSdArxKlPHaj07XM3GIBKLKwLy2P451JETYk%252BQ5%252BZgsImviXFqniAaS%252BH9zpqjAm651REqsAwbKk2osvmCNG9KpIM9spdiiKpd05yImMoPVlfg%253D%253D");
            request.addCookie("dj_pstoken",                "ZTJanHIdsPFks%252BaTr0GSvWgCsbCn4hhE1tMx7ur0EP1Akai6QW%252BRNjMgnN2gxuiPrnzCW304m0b8ZUIHCuKZfelJE7%252FuodKX2YqcmwjWB5fV5hvMYYFRUw%253D%253D");
           // request.addCookie("dj_psuid", "6360208644320278992_985721051373772800");
            request.addCookie("dj_psuid", "4054365564807946370_949480579827638272");
          // request.addCookie("dj_pstokenexp", "2592000");

        } catch (UnsupportedEncodingException e) {
            logger.error("参数转化异常， e=", e);
        }
        httpClientConfig.setSocketTimeout(20000);
        String rawText = null;
        HttpResponseEntity responseEntity = httpClientExecutor.execute(request, httpClientConfig);
        if (responseEntity != null && responseEntity.isSuccess() && responseEntity.getStatus() == HttpConstant.StatusCode.CODE_200) {
            System.out.println(responseEntity.toString());
            System.out.println(responseEntity.getRawText());
            rawText = responseEntity.getRawText();
        }
        return rawText;
    }


}
