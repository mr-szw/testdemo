1、sql 语句 表t_table中有字段user_id，查询表中user_id列存在重复值的记录（表主键为id）
		答：select * from t_table where id in (select  id  from  t_table  group  by user_id having  count(user_id) > 1);
	sql 语句的执行语句

2、值传递与引用传递
	public static void main(String[] args) {
		int num = 0;
		String numStr = "" + num;
		StringBuffer numStrBuffer = new StringBuffer(numStr);
		num++;

		method(num, numStr, numStrBuffer);

		System.out.println(num);
		System.out.println(numStr);
		System.out.println(numStrBuffer.toString());
	}

	public static void method(int num, String numStr, StringBuffer numStrBuffer) {
		num++;
		numStr = numStr + "-" + num;
		numStrBuffer.append(numStr);
	}
	答：1，0，00-2
		值传递
		String的常量池
		引用传递

3、线程池
	jdk自带的线程池，
	原生线程池构造器几个参数讲解

	ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,  TimeUnit unit,  BlockingQueue<Runnable> workQueue)
	1、corePoolSize 核心线程数 线程池 中稳定存在的线程数,并不会一开始就创建 （当设置了allowCoreThreadTimeOut参数其也会失效）
	2、maximumPoolSize 最大线程数 线程池可创建的最大线程数；队列满了再创建
	线程从corePoolSize --> maximumPoolSize过程 当大量请求过来的的时候 会阻塞一些等待任务再任务队列，当队列满再创建线程后处理任务
	当对线程池扩充到最大，仍继续增加任务，不执行其再触发降级逻辑（rejectHandler ）
	3、keepAliveTime 保持存活时间 即当线程数 大于 corePoolSize时 ， 那些大于线程数的那些线程将会被关闭，关闭时间有keepAliveTime 参数来控制
	4、unit  时间单位 keepAliveTime的时间单位
	5、workQueue 仅保存由execute提交的任务队列
	LinkedBlockingQueue 无界队列 Int.Max
	SynchronousQueue 同步移交
	ArrayBlockingQueue  有界队列 Int.Max
	6、threadFactory 新线程创建方式
	7、RejectedExecutionHandler  失效策略
	ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
	ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
	ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
	ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

	任务不断提交线程数量变化，过程

		new ThreadPoolExecutor(2, 5, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10),  new ThreadPoolExecutor.AbortPolicy());
		0 - 2 - 队列满 - 5 慢慢消失   循环取任务的时候 判断是否能够保存

	Executors.newFixedThreadPool(n); 固定线程数 线程异常创建再补充，线程创建达到最大不在创建也不释放，队列
	corePoolSize = maximumPoolSize = n
	keepAliveTime = 0
	LinkedBlockingQueue 无界队列（Int.Max）
	Executors.newSingleThreadExecutor(); 固定一个线程执行任务，若线程异常结束则创建新的线程补充执行
	corePoolSize = maximumPoolSize = 1
	keepAliveTime = 0
	LinkedBlockingQueue 无界队列（Int.Max）
	Executors.newCachedThreadPool();
	corePoolSize = 0
	maximumPoolSize = Int.Max
	keepAliveTime = 60s
	SynchronousQueue 同步队列，不存储，添加任务就执行
	Executors.newScheduledThreadPool(n);
	corePoolSize = n
	maximumPoolSize = Int.Max
	keepAliveTime = 0
	DelayedWorkQueue 无界队列（Int.Max）
	Executors.newWorkStealingPool();
	ForkJoinPool

4、【思路扩展】有两个用户ID的列表文件，每个文件约有10亿条用户id，有重复的ID，每个用户ID不超过10个字节，设计一个算法找出两个文件中共同的uid列表，允许有错误率【内存限制为1G】
答：
	1]bitmap hash（uid） 第二个文件查是否有存在
	2]布隆过滤器


	public class Singleton {
	private volatile static Singleton singleton;

	private Singleton(){}

	public static Singleton getInstance(){
		if (singleton == null){
			synchronized(Singleton.class){
				if(singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}





		请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用0 来代替。

		例如，给定一个列表temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]






public int[] solution(int[] arr){
		int len = arr.length;
		int[] res = new int[len];
		if(len<1) return res;
		List<Integer> stack = new LinkedList<>();
		Map<Integer,Integer> map = new HashMap<>();
		Integer peek = null;
		for(int i= len-1;i>=0;i--){
		while(!stack.isEmpty() && stack.peek() <= arr[i]){
		stack.pollFirst();
		}
		if((peek = stack.peek()) !=null){
		res[i] = map.get(peek)- i;
		}else{
		res[i] = 0;
		}
		stack.push(arr[i]);
		map.put(arr[i],i);
		}
		return res;

		}

private static Main instance;
public static Main getInstance(){
		if (instance == null) {
synchronized {
		instance = new Main();
		}
		}
		return instance;
		}


#########################################################

		张基 下午点
		dcl
		netty：半包 粘包问题如何解决

		黄豪栋 周三上午10:30
		1、dcl
		2、Thread.sleep 和wait的区别
		3、锁 java锁 synchronized和lock锁有啥区别
		https://www.cnblogs.com/twoheads/p/10678748.html
		4、为啥wait和notify要放到sychronized中
		5、redis持久化 重写
		6、异步线程 单点登录
		7、单词搜索	



		interceptor 和 filter的区别

		一：方案：异步消息到达发送 接到消息处理，但是库里没有 如何处理

		二：基本数据类型的值传递引用传递


		三：aop 动态代理  Spring AOP就是基于动态代理的 cglib
		动态代理：https://segmentfault.com/a/1190000011291179
		jdk：动态生成字节码

		cglib特点
		JDK的动态代理有一个限制，就是使用动态代理的对象必须实现一个或多个接口。
		如果想代理没有实现接口的类，就可以使用CGLIB实现。
		CGLIB是一个强大的高性能的代码生成包，它可以在运行期扩展Java类与实现Java接口。
		它广泛的被许多AOP的框架使用，例如Spring AOP和dynaop，为他们提供方法的interception（拦截）。
		CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类。
		不鼓励直接使用ASM，因为它需要你对JVM内部结构包括class文件的格式和指令集都很熟悉。
		cglib与动态代理最大的区别就是

		使用动态代理的对象必须实现一个或多个接口
		使用cglib代理的对象则无需实现接口，达到代理类无侵入。

		spring15常见问题：https://juejin.im/post/6844903860658503693
		总结
		1、静态代理实现较简单，只要代理对象对目标对象进行包装，即可实现增强功能，但静态代理只能为一个目标对象服务，如果目标对象过多，则会产生很多代理类。
		2、JDK动态代理需要目标对象实现业务接口，代理类只需实现InvocationHandler接口。
		3、动态代理生成的类为 lass com.sun.proxy.\Proxy4，cglib代理生成的类为class com.cglib.UserDao\\EnhancerByCGLIB\\$552188b6。
		4、静态代理在编译时产生class字节码文件，可以直接使用，效率高。
		5、动态代理必须实现InvocationHandler接口，通过反射代理方法，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。
		6、cglib代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但cglib会继承目标对象，需要重写方法，所以目标对象不能为final类。









		dcl
public class Singleton {

	//线程见可见性和指令有序性。
	private volatile Singleton singleton;

	// 构造函数是private，防止外部实例化
	private Singleton() {}

	public static Singleton getInstance() {
		if (singleton == null) { // 第一次检查
			synchronized (Singleton.class) {
				if (singleton == null) { // 第二次检查，"double check"的由来
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}