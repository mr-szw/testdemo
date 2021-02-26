## 2021 年2月26日 多云 没出门 不知道有没有风 元宵节

### 面试公司 -- 今日头条


#### 一面：涉及范围较广、比较看细节
   
   
   #####Q1: 自我介绍 及遇到的项目难点及思考
   A1: 巴拉巴拉
   
   
   Q2：一条sql语句执行会经过哪些过程
   A2: 
        mysql的结构 分为server层和引擎层
        ![Image text](https://img-blog.csdnimg.cn/img_convert/9c514ff1d34f3d9e2bcd8b1804458823.png)
        
        1、客户端发送请求，服务端 连接器层建立链接，校验身份
        2、查询缓存，缓存存在直接返回，否则继续
        3、进过分析器 词法分析 语法分析
        4、优化器优化 生成查询计划，选择合适索引
        5、执行器，执行器会校验读写权限、提交执行引擎执行
        6、返回数据
    
   #####Q3：Innodb为啥使用B+树做索引 为啥不用B树
   A3：
       
       B树:
            什么是B树:
                 
       B+树: 
       
       对比:
        
       1、mysql索引在 有单独的索引A、索引B的情况下 优化器匹配使用哪个索引 where A = xx and B = XX
            mysql执行优化器 优先选择影响行数少的，跟书写的先后顺序无关
       
       2、mysql深度查询为啥慢，如何解决
       
       
   #####Q4：Rpc请求经过的流程
   
   A4：回答了Dubbo作为rpc服务使用的调用过程
        
        双侧的代理类，数据序列化、反序列化、网络调用
        
        注册中心挂了会影响服务么
            会，dubbo消费者方本地会缓存可用的服务者信息
        
           
   #####Q5：redis使用
   
        1、string底层实现 这么实现有啥好处
            SDS
            struct sdshdr {
                //记录buf数组中已经使用的字节数量 是字符串保存的长度
                int len;
                //记录buf数组中未使用的字节数量
                int free;
                //字节数组 用于保存字符串
                char buf[];                
            };
            好处:
                1、常数复杂度获取字符串长度
                2、杜绝缓冲去溢出，通过长度控制能够很好的杜绝原本C语言的字符串溢出问题
                3、减少字符串修改带来的内存重分配次数
                4、二进制安全 C语言中的字符串必须符合某种编码，并且除了字符串的末尾位置之外不能包含空字符，可以保存文本和二进制数据
                5、兼容部分C字符串函数
        2、redis锁如何做锁
            set nx ex 
                如何防止被别人删除锁 或如何避免锁失效删除别人的锁
                    使用redis的事务支持
                    try{
                        jedis.watch("key")
                        Transaction tran = jedis.multi(); // 开启事务
                        tran do something
                        List<Object> result = tran.exec();
                        if(result.isEmpty) {
                            return faild;
                        } else {
                            return success;
                        }
                        
                    } finaily {
                        jedis.unWatch("key");
                    }         
        3、redis 击穿 穿透是什么概念如何解决
            击穿：
                概念：热点数据缓存失效
                处理：
                    1、缓存设置长失效时间「不失效」，定时任务定期更新
                    2、双key 探测key失效加锁更新数据，未失效使用 探测key有更小的失效时间
        4、如何查找某些前缀开头的元素 https://www.cnblogs.com/cheyunhua/p/13343429.html
            scan  
                scan用于增量迭代
                
        
   ![Image text](https://img2020.cnblogs.com/other/268224/202007/268224-20200719105137483-1089514405.jpg）
             
            scan有啥问题？
                
                keys和scan的区别
                    不能遍历所有槽位    
        
        
   #####Q6：http状态码499，什么原因、如何解决
   A6：
   
       原因:
            客户端主动与服务器断开链接
            
       解决：
            1、提供服务响应力 降低服务器耗时
            2、客户端使用长链接不断开
       
  
  #####Q7:算法题 二维数组找指定值