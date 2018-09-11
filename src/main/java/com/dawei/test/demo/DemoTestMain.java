package com.dawei.test.demo;

import com.dawei.test.demo.jms.QueueProducerDemo;
import com.dawei.test.demo.utils.DoubleHelper;
import com.dawei.test.demo.utils.MD5Util;
import com.dawei.test.demo.utils.UniqueIDUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;
import org.springframework.util.StringUtils;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {


    public static void main(String[] args) throws Exception {




        System.out.println(MD5Util.MD5Encode("868144030992496", "UTF-8"));


        Double businessScore = 4.66666D;
        System.out.println(new BigDecimal(businessScore).setScale(1, BigDecimal.ROUND_UP).doubleValue());

        //businessScore = 1D;
        System.out.println(new Double(new BigDecimal(businessScore).setScale(1, BigDecimal.ROUND_CEILING).doubleValue()));
        //businessScore = 0D;

        // businessScore = 1.4444D;
        System.out.println(new Double(new BigDecimal(businessScore).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()));
         //businessScore = 1.9D;
        System.out.println(new Double(new BigDecimal(businessScore).setScale(1, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
        System.out.println(new Double(new BigDecimal(businessScore).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue()));


        System.out.println(judgeAppVersion("5.4.5.0", Template_Version_V5_4_5));

        System.out.println(judgeAppVersion("5.4.2"));
        System.out.println(judgeAppVersion("5.4.2", Template_Version_V5_4_2));
        for(int i = 0; i< 100; i++) {
            Random random  = new Random(System.nanoTime());

            int randomIndex = random.nextInt(10);
            System.out.println(randomIndex);
            Thread.sleep(2);
        }



        System.out.println(judgeAppVersion("5.6.3", "5.5.5"));
        String reduce2 = Stream.of("A", "B", "C", "D").filter(f -> f != null).map(str ->  str + ",").reduce("", String::concat);
        System.out.println(reduce2);

        String str1 = "2324335";
        str1 = str1.substring(0, str1.length() -2);
        System.out.println(str1);

        List<String> list = new ArrayList<>();
        list.add("dddd");
        list.add("dddd");
        list.add("dddd");
        list.add("dddd");
        list.add("dddd");
        list.add("dddd");
        System.out.println(Arrays.toString(list.subList(0, 2).toArray()));
        for(int i =0; i<20; i++) {
            Random random = new Random(System.nanoTime());
            int indexNum = (random.nextInt(10) * 10000) % 15;

            System.out.println(indexNum);
        }        String strin = null;

        strin += "as";
        strin += null;

        System.out.println(strin);

        Double aDouble = DoubleHelper.formatDoublePattern(4D, DoubleHelper.PATTERN_TWO);
        String str = DoubleHelper.formatDoublePatternStr(4D, DoubleHelper.PATTERN_TWO);
        System.out.println(aDouble);
        System.out.println(str);

        String md5Encode = MD5Util.MD5Encode("867694020259166", "UTF-8");
        System.out.println(md5Encode);

        /*
        Date d = new Date(1534504544498L);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        String format = simpleDateFormat.format(d);
        Date parse = simpleDateFormat.parse("1996-06-23");
        System.out.println(parse.getTime());
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(11L);
        longList.add(111L);
        longList.add(1111L);
        longList.add(11111L);
        longList.add(111111L);
        longList.add(11111111L);

        Map<String, Long> longMap = new HashMap<>();
        int count = 6;
        while (count-- > 0) {
            longList.forEach(longLis -> {
                longMap.put(longLis + UUID.randomUUID().toString(), longLis);
                //longMap.add(longLis);
            });

            longMap.forEach((key,value) ->     System.out.print("  Num " + value));
            longList.clear();
            Thread.sleep(1000);
            System.out.println("$####################");
            System.out.println("$####################" + count);

        }*/
        /*Set<Long> longSet = new HashSet<>(longList);
        int count = 10;
        while (count-- > 0) {
            longSet.forEach(argLs -> System.out.print("  Num " + argLs));
            Thread.sleep(1000);
            System.out.println("");
        }*/




       /* Set<Long> idSet = new HashSet<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Integer count = 1000000;
        long timeMillis = System.currentTimeMillis();
        while (count-- > 0) {
            executorService.execute(() -> {
                long uniqueID = UniqueIDUtil.getUniqueID();

                if(!idSet.add(uniqueID)) {
                    System.out.println("#####################");

                }

            });
        }
        System.out.println(" Out " + (System.currentTimeMillis() - timeMillis));

        //QueueProducerDemo.messageProducer();

        while (true) {
            if(((ThreadPoolExecutor)executorService).getActiveCount() == 0) {
                executorService.shutdown();
                System.exit(-1);
            }
            System.out.println("this Main method");
            // System.out.println(execute());
            System.out.println("main method over");
        }*/

    }

    public static String execute() {
        new ThreadT().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "execute over";
    }
    static
    class ThreadT extends Thread {
        @Override
        public void run() {
            System.out.println("to do Run now ");
            try {
                Thread.sleep(1000);
                System.out.println("this run first");
                Thread.sleep(5000);
                System.out.println("this run Second ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static final String Template_Version_V5_4_2 = "5.4.2";
    public static final String Template_Version_V5_4_5 = "5.4.5";

    /**
     * 版本 区分工具
     * @param newVersion 当前版本
     * @param templateVersion 比对版本
     * @return 是否是高版本
     */
    public static boolean judgeAppVersion(String newVersion, String templateVersion) {
        boolean resultFlag = false;
        if (!StringUtils.isEmpty(newVersion) && !StringUtils.isEmpty(templateVersion)) {
            String[] versionNum = newVersion.trim().split("\\.");
            String[] templateNum = templateVersion.split("\\.");
            if (versionNum.length >= 3) {
                if (Integer.parseInt(versionNum[0]) > Integer.parseInt(templateNum[0]) ||
                    Integer.parseInt(versionNum[1]) > Integer.parseInt(templateNum[1]) ||
                    Integer.parseInt(versionNum[2]) > Integer.parseInt(templateNum[2])) {
                    resultFlag = false;
                } else {
                    resultFlag = true;
                }
            }
        }
        return resultFlag;
    }


    public static boolean judgeAppVersion(String version) {

        String[] versionNum = version.trim().split("\\.");
        if (Long.valueOf(versionNum[0]) > 5 || Long.valueOf(versionNum[1]) > 4 || Long.valueOf(versionNum[2]) > 2) {
            return true;
        }
        return false;
    }

}
