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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by Dawei on 2018/6/5.
 */
public class HttpClientTest {


    /*public static void main(String[] args) {
        new HttpClientTest().doRequest();
    }



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
    }*/







}
