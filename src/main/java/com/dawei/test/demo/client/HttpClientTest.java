package com.dawei.test.demo.client;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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




        // new HttpClientTest().doRequest();
    }


    public void doRequest() {
        //String urlPath = "http://192.168.150.24/lottery/drawn";
        //String urlPath = "http://lottery.dawei.com/lottery/drawn";
        //String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/content";
        //String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/detail";
        String urlPath = "https://jz-csapi.djtest.cn/customer/online/demand/designate";
        CloseableHttpClient httpClient = HttpClients.createDefault();

<<<<<<< HEAD
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



=======
>>>>>>> master
}
