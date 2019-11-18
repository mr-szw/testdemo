package com.dawei.test.demo.client;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author by Dawei on 2018/6/7.
 */
public class ThreadTool {

    private static Long index = 1L;
    private static Long index1 = 1L;








    private static String ADDR = "cache01.b2c.srv";
    private static int PORT = 5100;
    private static String AUTH = "cn_b2c-calc_b2cdc_user_attr_PTZKrYbBwC0Ql";
    private static int MAX_ACTIVE = 64;
    private static int MAX_IDLE = 64;
    private static int MAX_WAIT = 1000;
    private static int TIMEOUT = 1000;
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    // 单例模式返回Jedis
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool == null) {
                initRedisPool();
            }

            Jedis resource = jedisPool.getResource();
            return resource;

        } catch (Exception e) {
            return null;
        }
    }


    private static String  get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    // 初始化redis连接池
    public static void initRedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);

    }











    public static void main(String[] args) {

        while (true) {
            Jedis jedis = getJedis();
            try {
                System.out.println(jedis.get("ABC"));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

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

        String boardIdStr = "570969,567423,570974,566732,567470,570964,567605,567610,571019,568861,566822,571014,567400,568786,567460,566827,564619,5866182,5870829,5863753,104724,5870684,5862561,5775852,5598168,566737,568771,5462662,5416771,5942756,6151520,561518,567418,5867107,562681,5866202,568856,567438,5767010,6045860,5863838,566817,571009,5870195,5867517,5425803,5682199,8799343,5673583,567413,5738121,5871219,5757467,5782644,560927,560932,562676,567408,566742,568776,568781,567465,566832,567308,567313,567428,568866,568871,566837,5762998,5870170,5870460,5862566,5871974,5582256,6131747,5429278,5558464,5897770,6074561";
        String boardIdNameStr = "联系人,文件管理器,录音机,桌面,设置,状态栏与通知栏,备份,小米音乐,应用商店,浏览器,日历,邮件,指南针,计算器,便签,天气,云服务,百度输入法（小米版）,搜狗输入法（小米版）,讯飞输入法（小米版）,游戏中心,垃圾清理,超级省电,投屏,多看阅读,全球上网,全局搜索,小米社区,小米社区内测,小米助手,小米画报,相机,悬浮球,应用双开,智能助理（负一屏）,手机分身,扫一扫,快应用,万象息屏,MIUI 万象息屏内测,亲情守护,电话,短信,游戏加速,应用锁,小爱同学圈,声音和振动,AI电话助理,屏幕时间管理,服务与反馈,小米互传,儿童模式,智能出行,小米健康,相册,无障碍,账号,锁屏,NFC,手机管家,时钟,录屏,小米换机,手电筒,官方壁纸,主题壁纸,小米视频,小米钱包,MIUI其他业务,小米移动,地震预警,红包助手,紧急求助,应用包管理组件,三方应用圈,小米客服,超级省电内测,系统桌面内测,Patchwall内测,作息管理内测";
        String[] boardIdSplit = boardIdStr.split(",");
        String[] boardNameSplit = boardIdNameStr.split(",");
        System.out.println("do request : " + index++);
        String urlPath = "http://test.web.vip.miui.com/api/community/board/group/employee?";
        //CloseableHttpClient httpClient = HttpClients.createDefault();

        System.out.println("boardId,boardName,userId,userName");
        int length = boardIdSplit.length;
        for (int i = 0; i < length; i++) {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            List<NameValuePair> nameValuePairList = new ArrayList<>();
            BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("boardId", boardIdSplit[i]);
            BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("limit", "100");
            BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("after", "");
            nameValuePairList.add(basicNameValuePair1);
            nameValuePairList.add(basicNameValuePair2);
            nameValuePairList.add(basicNameValuePair3);
            try {
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
                String params = EntityUtils.toString(encodedFormEntity);
                HttpGet httpGet = new HttpGet(urlPath  + params);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                String content = EntityUtils.toString(entity, "utf-8");
                Map map = JSON.parseObject(content, Map.class);
                Object entity1 = map.get("entity");
                Map map1 = JSON.parseObject(JSON.toJSONString(entity1), Map.class);
                Object records = map1.get("records");
                List<UserVO> userVOS = JSON.parseArray(JSON.toJSONString(records), UserVO.class);
                for (UserVO userVO : userVOS) {
                    System.out.println(boardIdSplit[i] + "," +  boardNameSplit[i] + "," + userVO.getUserId() + "," + userVO.getUserName());
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //System.out.println("index : " + index1++ + "   " + JSON.toJSONString(userVOS));
            } catch (IOException e) {
                e.printStackTrace();}
//            } finally {
//                try {
//                    //httpClient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        }



}
