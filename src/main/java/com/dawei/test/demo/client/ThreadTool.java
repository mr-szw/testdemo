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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by Dawei on 2018/6/7.
 */
public class ThreadTool {

    private static Long index = 1L;
    private static Long index1 = 1L;

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService caCheThreadPool = Executors.newCachedThreadPool();
        for(int i =0; i < 80 ; i ++) {
            caCheThreadPool.submit(() -> {
                doRequest();
            });
            /*fixedThreadPool.submit(() -> {
                doRequest();
            });*/
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //fixedThreadPool.shutdownNow();
        caCheThreadPool.shutdownNow();


/*


        Long currentTme = System.currentTimeMillis();
        Long nowTime = currentTme;
        for(int i = 0; i < 3000; i++) {
            nowTime = System.currentTimeMillis();
            if(nowTime - currentTme > 1) {
                break;
            }

            fixedThreadPool.submit(() -> {
                doRequest();
            });
            fixedThreadPool.shutdownNow();
        }*/
        System.out.println("end");
    }
    //@Transactional(propagation = Propagation.REQUIRED)
    public static void doRequest() {
        System.out.println("do request : " + index++);
        //String urlPath = "http://192.168.150.24/lottery/drawn";
        String urlPath = "localhost/lottery/drawn";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        Long passportId = UniqueIDUtil.getUniqueID();
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("activityId", "371835816935488");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("passportId", passportId.toString());
        nameValuePairList.add(basicNameValuePair1);
        nameValuePairList.add(basicNameValuePair2);
        try {
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
            String params = EntityUtils.toString(encodedFormEntity);
            HttpGet httpGet = new HttpGet(urlPath + "?" + params);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println("index : " + index1++ + "   " +  content);
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


}
