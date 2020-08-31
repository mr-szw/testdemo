package com.dawei.test.demo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dawei.test.demo.future.ExecutorApi;
import com.dawei.test.demo.pojo.BoardMoreConfigVo;
import com.dawei.test.demo.pojo.DemoPojo;
import com.dawei.test.demo.pojo.ResultDto;
import com.dawei.test.demo.utils.GsonUtil;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;

import lombok.SneakyThrows;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain implements Cloneable {

	private ConcurrentHashMap<Thread, Long> concurrentHashMap = new ConcurrentHashMap<>();
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);


	public static String appendNativeH5Path(String h5UrlPath, String nativeUrlPath,
									 @Nonnull Object... params) throws Exception {
		// mio://urlPath&param=18771603&fallback_url=
		//
		// encode(mibrowser://home?web_url=
		//
		// encode(https://urlPath?param=18788534)

		String feJumpBoardIdUrl = URLEncoder.encode(String.format(h5UrlPath, params),
				StandardCharsets.UTF_8.displayName());
		String fallBackUrl = URLEncoder.encode(
				String.format("mibrowser://home?web_url=%s", feJumpBoardIdUrl),
				StandardCharsets.UTF_8.displayName());
		Object[] objects = Arrays.copyOf(params, params.length + 1);
		objects[params.length] = fallBackUrl;
		return String.format(nativeUrlPath, objects);
	}

	// 专题页
	public static final String FE_TOPIC_HOME_URL = "https://web.vip.miui.com/page/info/mio/mio/subject?actid=%s&ref=%s";
	public static final String NATIVE_TOPIC_HOME_URL = "mio://web.vip.miui.com/page/info/mio/mio/subject?actid=%s&ref=%s&fallback_url=%s";

	//MCC 使用的ref
	public static final String MCC_REF = "newhome&preventBack=true";

	public static void main(String[] args)throws Throwable{

		System.out.println(tableSizeFor(4));


		System.out.println(appendNativeH5Path(FE_TOPIC_HOME_URL, NATIVE_TOPIC_HOME_URL, "331975096", MCC_REF));


		boolean addSuccess = true;

		//Assert.assertTrue("Resource must by only, =", !addSuccess );

		System.exit(1);

		System.out.println(System.currentTimeMillis() - 1585646833897L >= 31536000000L);

		System.out.println(Long.parseLong("\\x00\\x00\\x01q\\xEA\\x90\\xE9".replace("\\x", ""), 16));
		System.out.println("\\x00\\x00\\x01q/\\xEA\\x90\\xE9".replace("\\x", ",").split(","));

		String message = "{\"blocks\":[{\"key\":\"fm69u\",\"text\":\"提前声明：这款产品目前在有品众筹，通过前期内测&体验方式获得。本文会以客观展示&主观体验的方式分享这款产品的使用体验。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"ftokm\",\"text\":\"Hello大家好，我是电器拆解达人大脸喵\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bmc5n\",\"text\":\"是不是为了送礼还在发愁？不如看看这条水暖床垫\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3dkp0\",\"text\":\"它送女友很不错，不用担心用电热毯皮肤干燥，不用担心它忽冷忽热睡不好觉。就算你们在床上蹦跶也不用操心触电了。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"756q3\",\"text\":\"它送长辈很不错，再也不用担心他们设的温度太高烫伤自己，再也不用担心忘关电热毯引发事故。而它的价位也很合适，既不会让你感到肉疼，又体现出了足够心意。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"de7l8\",\"text\":\"那么它到底是啥？\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"43t64\",\"text\":\"通过水来为床铺加热来替代电热毯？？水床吗？并不是。要更换床垫吗？不需要。用的水泵？对。那么噪音大吗？没听见。安全吗？比电热毯更安全。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":66,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"afoie\",\"text\":\"如果把电热毯看做是铺在床单下的热得快，那么这款水暖床垫可以看做是铺在床单下不断换热水的暖水袋。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":15,\"length\":3,\"style\":\"BOLD\"},{\"offset\":43,\"length\":3,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"dnki9\",\"text\":\"这么新奇的装备我也是第一次来接触，使用过程中也非常的好奇。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"eo9tc\",\"text\":\"我是一个非常怕热又非常怕冷的人，电风扇刚收起来就要铺下电热毯。但是，电热毯发展这么多年有几个问题依旧没有很好的解决：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7cvjq\",\"text\":\"1、用了皮肤干，在看本文各位我想用过都有感触。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5dvm1\",\"text\":\"2、电热丝加热，劣质电热毯使用还有触电风险。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"diu4v\",\"text\":\"3、万一忘记关了就会有起火风险。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"aughc\",\"text\":\"每年电热毯引发的事故不少了，尤其是老年人对温度不敏感，低温烫伤实有发生。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"88fjs\",\"text\":\"这么大的使用风险，为什么还在用呢？\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5s3kl\",\"text\":\"冷啊！\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3p8er\",\"text\":\"冬天的江南地区取暖全靠抖，空调也管不到被子里，冬天一进被子，那种钻心刺骨的冷啊真是直接驱散了睡意。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8obqd\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dj7jm\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":0}],\"data\":{}},{\"key\":\"asdsa\",\"text\":\"简单开箱与产品细节：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":10,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"f80nm\",\"text\":\"到手时候适逢无锡地区大降温，正好就拆包直接开始使用了。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"fk9ju\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"862oc\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":1}],\"data\":{}},{\"key\":\"60pm4\",\"text\":\"我选的是1.8米的款式，整个床垫还是比较有分量。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"drcf\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6dckj\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":2}],\"data\":{}},{\"key\":\"6ocm7\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"81idj\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":3}],\"data\":{}},{\"key\":\"5hgok\",\"text\":\"床垫和主机分开包装\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1n472\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1lf3c\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":4}],\"data\":{}},{\"key\":\"a6qrk\",\"text\":\"床垫\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1qn89\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7vghu\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":5}],\"data\":{}},{\"key\":\"f8ipk\",\"text\":\"LOGO\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"949pc\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"4qpqf\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":6}],\"data\":{}},{\"key\":\"62juq\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9bok7\",\"text\":\"床垫正面纹路\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6gp93\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"a95i0\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":7}],\"data\":{}},{\"key\":\"d5g9l\",\"text\":\"合格证\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6dnne\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"4rfje\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":8}],\"data\":{}},{\"key\":\"9as8t\",\"text\":\"封边，线比较粗，耐用性提升\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5lbg3\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8cu4\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":9}],\"data\":{}},{\"key\":\"b8j85\",\"text\":\"水暖毯出水口有额外的塑料口固定。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"elgud\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9i3ir\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":10}],\"data\":{}},{\"key\":\"40u0a\",\"text\":\"连接主机的水管，水管上有包裹保温棉\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"enqie\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9ip3u\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":11}],\"data\":{}},{\"key\":\"6giq0\",\"text\":\"不分进出的两条水管\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9h4ui\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5ovdn\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":12}],\"data\":{}},{\"key\":\"2hu5d\",\"text\":\"水暖床垫反面有皮筋固定在床垫或者床板上防止位移\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7792m\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8cspb\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":13}],\"data\":{}},{\"key\":\"e5ggt\",\"text\":\"水洗标。正面反面与填充物都是100%聚酯纤维，循环管是PVC材质。不可水洗、不可熨烫、不可干洗，那就是不能洗咯。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"893tv\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8lqj4\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":14}],\"data\":{}},{\"key\":\"742d1\",\"text\":\"床垫本身还比较厚实\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"c30qv\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7v4b2\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":15}],\"data\":{}},{\"key\":\"9nikk\",\"text\":\"主机\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2bh72\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"f93a0\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":16}],\"data\":{}},{\"key\":\"1mqp7\",\"text\":\"顶盖打开后可以看到加水口\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5n4ut\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dov4v\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":17}],\"data\":{}},{\"key\":\"9348b\",\"text\":\"加水口向内凹陷，起到漏斗作用，加水时候不容易漏。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3gmvn\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6npco\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":18}],\"data\":{}},{\"key\":\"4vj9c\",\"text\":\"显示屏在前端，不启动时候无显示。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"72mk4\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bmga5\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":19}],\"data\":{}},{\"key\":\"cbp5m\",\"text\":\"背部是电缆线与进出水管接口\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"166mp\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"cj0n9\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":20}],\"data\":{}},{\"key\":\"7kkln\",\"text\":\"扫码加入米家APP\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"65ej7\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2mfgt\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":21}],\"data\":{}},{\"key\":\"faugl\",\"text\":\"主机背部\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1sur1\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6csar\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":22}],\"data\":{}},{\"key\":\"cakrh\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8i7ml\",\"text\":\"底部有一个微动按钮，每当提起这个主机时候它会释放，主机就会自动关闭。是个不错的安全措施。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2j0\",\"text\":\"简单自行安装：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"26ei1\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1dprp\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":23}],\"data\":{}},{\"key\":\"55l4e\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"ada0v\",\"text\":\"安装过程比较简单，相对来说铺好这1.8米的床垫反而成了我最大的难点。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3vlf4\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bv5mv\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":24}],\"data\":{}},{\"key\":\"bt7q1\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"andk9\",\"text\":\"好在床垫可以固定，避免了安装和使用中滑动。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3h0cv\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"844qj\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":25}],\"data\":{}},{\"key\":\"788ge\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"59jsm\",\"text\":\"水管的安装和净水器差不多\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1eggh\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"v7f1\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":26}],\"data\":{}},{\"key\":\"345ff\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"4vjs5\",\"text\":\"不过还是记得先取下两个灰色的堵头，取下之后也记得好好收纳，在春天收纳主机的时候还得用到这两个堵头。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"82prq\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"50rsh\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":27}],\"data\":{}},{\"key\":\"8352m\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bl5uk\",\"text\":\"接好水管，排好管子不要弯曲到。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5l4bv\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"eqms7\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":28}],\"data\":{}},{\"key\":\"dv6af\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6dib4\",\"text\":\"接着开始加水。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"88uis\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"96skn\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":29}],\"data\":{}},{\"key\":\"3cpdt\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7jm5q\",\"text\":\"我是满水之后开机，这时候泵的运行会将水循环进床垫。这时候要关注水量的显示，还要再加一次水才能满足它的运行。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2rblp\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1vksq\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":30}],\"data\":{}},{\"key\":\"9kces\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9uneh\",\"text\":\"总共需要大约1.5升的水，为了让主机长久运行防止水垢等问题，最好直接使用净水，如果家里没有净水器那也可以超市买蒸馏水或者纯净水，不要买农夫山泉这类矿泉水。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"c20b5\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7spks\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":31}],\"data\":{}},{\"key\":\"9g7m5\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9pjt3\",\"text\":\"简单对比与使用体验：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":10,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"lnjq\",\"text\":\"到目前为止体验了5天时间，总体感觉比较满意，下面说说具体的一些感受\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bo3j3\",\"text\":\"对比之前使用的电热毯：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":11,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"4jh79\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"aco5n\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":32}],\"data\":{}},{\"key\":\"9togn\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"em043\",\"text\":\"这是我原先使用的电热毯，怎么说呢，这是我用的第3块电热毯，我几乎每2年就要用坏一条电热毯。一个是经不起我存储折叠，内里的金属电热丝会断。另一个就是控制器使用的时间长之后会故障。这也是我使用下来大部分电热毯的问题。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"97hdq\",\"text\":\"从上面的对比图也不难看出这张很“大”的双人电热毯和水暖毯的面积完全无法比的，最多也就铺满1.5米床的样子。这也是我在使用过程中一直比较尴尬的事情，它不能完全覆盖床铺，一个翻身就可能翻出发热区域，然后一哆嗦就冻醒了。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3eo4\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9897m\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":33}],\"data\":{}},{\"key\":\"5agp8\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"5qads\",\"text\":\"实际试用时候发现它的加热范围也是非常大，除了床边容易翻下去的部分基本上都涵盖到了。而且水路安排的比较密集。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bjb77\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dmjka\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":34}],\"data\":{}},{\"key\":\"2dk57\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"86uc0\",\"text\":\"两者在厚度上也完全不在一个等级上，电热毯最多也就夹在铺垫和床单之间来使用。这款水暖床垫的厚度直接当床垫用也ok。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"3eikr\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"cemsr\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":35}],\"data\":{}},{\"key\":\"5vpbt\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"ckf4b\",\"text\":\"在温控上两者差距也比较巨大，小绵羊虽然已经使用很高级的10档控制，但是要和佳尼特水暖床垫精确到1度的恒温控制比起来……呃……没法比。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1uraa\",\"text\":\"要说我之前用的这款电热毯的优势，一个是双人分区温控管理，另一个是价格优势，这条电热毯原价也就200多元。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"4a46l\",\"text\":\"安全：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":3,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"bfjg6\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"av95c\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":36}],\"data\":{}},{\"key\":\"fpbu0\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"45el2\",\"text\":\"也得益于使用的水循环来替代电热丝，水电分离之后将触电风险降到了最低（纯水也不导电），电磁辐射也无从说起。万一，万一碰到尖锐物品刺穿了，大不了也是溅一身水，而不是触电甚至起火。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"diitf\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2pqqh\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":37}],\"data\":{}},{\"key\":\"evr82\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dudgi\",\"text\":\"在温度调节上它可以精确到1度，从这几天的使用体验看来恒温性能相当的不错。也得益这一点，电热毯低温烫伤的风险在水暖毯上降到了极低，只要设定温度不高于人体温，烫伤也就不会发生。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"6lpsi\",\"text\":\"另外，它还有一个自动倒计时关闭的设定，12小时没有操作就自动关机。再也不怕忘记关了。就算忘记关运行12小时，这最高60度的水温也不会像电热毯一样有起火的风险。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"e49cj\",\"text\":\"APP智能操控： \",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":8,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"2vdvi\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bqghg\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":38}],\"data\":{}},{\"key\":\"2vn0j\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"8omsl\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":39}],\"data\":{}},{\"key\":\"echnq\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"cfb71\",\"text\":\"佳尼特的水暖床垫还支持连入米家，可以使用米家APP控制。是WiFi直连哦，不需要网关支持。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"7p4gb\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"25di1\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":40}],\"data\":{}},{\"key\":\"2bu37\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"31cta\",\"text\":\"在APP内可以看到当前水温和设定温度、水位。在操作上支持开关、调节温度、设定倒计时和一键暖被。一键暖被就是直接将温度调到最高。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"e1kv3\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"auuqc\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":41}],\"data\":{}},{\"key\":\"c1c32\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"1vros\",\"text\":\"倒计时是以1小时为步进的，最多设定6小时。不过这在米家APP内完全不是问题，这款水暖床垫在联动操作中支持开机、关机两个动作，设定一个智能定时开、关和联动完全没有难度。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"ei9jn\",\"text\":\"甚至通过iOS的捷径功能还可以将它添加进Siri来操作它的开关甚至定时。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"bs9s\",\"text\":\"要说一点不足，那就只有联动动作少了一些，比如没有开机》指定温度或者启动一键暖被功能。不过开和关也足够进行大部分的联动，比如在晚上9点之后室内温度低于20度启动电暖毯，高于24度关闭等实用设定都可以实现了。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"31qup\",\"text\":\"功耗：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"a1c69\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"e25gf\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":42}],\"data\":{}},{\"key\":\"dl9su\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"c7fvs\",\"text\":\"这么高级的产品功耗一定很大吧？然而并没有。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"b93m5\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dv8q7\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":43}],\"data\":{}},{\"key\":\"8smmj\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9mire\",\"text\":\"主机在加热部分的运行功耗大约为330W左右，到达温度之后就只有循环泵在运行，大约为4W。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"biguq\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9fq7h\",\"text\":\" \",\"type\":\"atomic\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[{\"offset\":0,\"length\":1,\"key\":44}],\"data\":{}},{\"key\":\"brb27\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"583bn\",\"text\":\"我是22：00点开启的水暖毯，在温度爬升耗电较大这个时间内也就0.08度的耗电，去掉固定0.01功耗之后计算水暖毯功耗就只有0.07度电。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9dhtn\",\"text\":\"在平稳保温阶段，每小时耗电为0.03~0.05度电。一晚上的使用下来半度电不到点。相比较我的电热毯大约140W固定功耗，一般只开一半，折算每小时也要大约0.07度电。这样算起来水暖床垫还更省电一些。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"842rq\",\"text\":\"总结：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":3,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"2ijet\",\"text\":\"水暖床垫解决了使用电热毯众多的弊端和风险，又提供了更好的恒温舒适体验。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"c0t5a\",\"text\":\"优点：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"alsmi\",\"text\":\"1、恒温效果好，皮肤不干燥。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9vjch\",\"text\":\"2、无辐射、没有漏电风险、不怕忘关\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"2bvhj\",\"text\":\"3、噪音低，启动不察觉。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"9gfeh\",\"text\":\"4、耗电少，不担心电费增加\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"dbf7q\",\"text\":\"不足：\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[{\"offset\":0,\"length\":3,\"style\":\"BOLD\"}],\"entityRanges\":[],\"data\":{}},{\"key\":\"cs5og\",\"text\":\"出水口仅能在左侧，安装使用时候面对床的左边尽量得有一个床头柜来放置主机。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}},{\"key\":\"4ttpc\",\"text\":\"在这个冬天来临之际拥有这么一款水暖床垫，真是让我对严寒的畏惧感少了不少，一千多的价格实属定价适中，不管是送人还是自用，你都一定会喜欢它的。\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{\"0\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/92b768ac20529a8f558606a9cc05bb15.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"1\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/4391a4e5429d24060a864167bd450da7.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"2\":{\"type\":\"PRODUCT1\",\"mutability\":\"IMMUTABLE\",\"data\":{\"img\":\"https://img.youpin.mi-img.com/shopmain/fc118232c9c2011a44cec16f84474cbd.png?w=800&h=800\",\"name\":\"佳尼特智能恒温水暖床垫\",\"price\":\"1399\",\"daiqi\":true,\"gid\":113814,\"marketPrice\":\"1399\",\"labels\":[]}},\"3\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/a52c1ab76b968fdffc18c76d0f4ce51c.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"4\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/674ba0eb492c4816ab30e3cdd00ccf53.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"5\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/77b8c29f82f7c97a0c9117b9a1281f73.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"6\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/51865d004c5a5062ed7249fc3fae4afd.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"7\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/aa807baa9aa4153f75d2a1821279fc8b.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"8\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/4fed72d72e783b3d43e15111bb2d9bc8.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"9\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/87055c59fad14f0005ede181ebb5ce00.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"10\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/25632cb7995db4af812684448f4f0613.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"11\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/4fdf698c058268c41375eccd9888d2a3.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"12\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/070b0943ea5fe7a4cb0d02b0c110fecd.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"13\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/a962e069dc32f009a3ef9ec8832c1ea4.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"14\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/1894bdbbb892a7b47545d78d66b2d262.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"15\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/8ebe413205e49fca389babc621ef9bee.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"16\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/38acc4749d32405660ab53ef39fd4225.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"17\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/a15a362f383cd45e2ed9b30156b5e7e0.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"18\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/bcc427323af1401bfa2dd29f11dc82f2.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"19\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/e08b069a36bb3bf1cc1a51caab94f760.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"20\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/6cf7d1016eddab9cd74a3ea7fff7edb3.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"21\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/3247420f3b40f9677803c264d161f531.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"22\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/2b3b1079472cffae2afa912d8f26380c.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"23\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/367bd283008e816d3d26383657153e03.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"24\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/56b08c89832e404bbe36dbcb0642017d.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"25\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/fbc03b670814f33e5e3e609a01bd258c.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"26\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/f7d312286569954b54c761e19db70cec.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"27\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/367bd283008e816d3d26383657153e03.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"28\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/e76494b615401c687b4968359f4baf65.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"29\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/38366874916dfcd69c6f1c81b68463a8.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"30\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/cddf98637dcb3628aba6882ce8c10a7d.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"31\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/aa0712209b22d0ae03abae8ec3d46163.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"32\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/5f971a7321d633df59371773426e0d7e.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"33\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/8ce3a9a475a66e21817e7053661057ab.jpeg?w=1440&h=1080\",\"preSrc\":null}},\"34\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/b9d8ba0e6a46bf9437cdf13d0330ca1c.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"35\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/2a05a7c34ce804e86563ac8fd7363055.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"36\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/4bc19ba1c98c582547355d5314674ddb.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"37\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/574cf583ebac8d932d5940d42b6e3fda.jpeg?w=718&h=520\",\"preSrc\":null}},\"38\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/b98d94aab329a7e8ab380e8cfb64965f.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"39\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/794d73d993d933535ab11dfd84c0f844.jpeg?w=1242&h=698\",\"preSrc\":null}},\"40\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/574cf583ebac8d932d5940d42b6e3fda.jpeg?w=718&h=520\",\"preSrc\":null}},\"41\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/4ada628f51aa197a1aff1da62fbac936.jpeg?w=718&h=520\",\"preSrc\":null}},\"42\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/3f9030ddcc1df1bc1fd4121981e6c3bf.jpeg?w=1620&h=1080\",\"preSrc\":null}},\"43\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/c6a1b50279619b4a7dcbea1f001673d8.jpeg?w=1234&h=694\",\"preSrc\":null}},\"44\":{\"type\":\"IMAGE\",\"mutability\":\"IMMUTABLE\",\"data\":{\"src\":\"https://img.youpin.mi-img.com/daren/ba9acc339b60b3fc1c559603ef96df5e.jpeg?w=1236&h=696\",\"preSrc\":null}}}}";
		Map<String, Object> resourceMap = GsonUtil.fromJson(message,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		List<Map<String, String>> testContentList = (List<Map<String, String>>) resourceMap.get("blocks");
		if (!CollectionUtils.isEmpty(testContentList)) {
			message = testContentList.stream().map(text -> text.get("text"))
					.filter(Objects::nonNull).collect(Collectors.joining(""));
		}
		System.out.println(GsonUtil.toJson(message));
	}



//	public static void appendNativeH5Path(String nativeUrlPath, @Nonnull Object... params) throws Exception {
//		String fallBackUrl = "mio://urlPath&param=18771603&fallback_url=";
//
//		Object[] objects = Arrays.copyOf(params, params.length + 1);
//		objects[params.length] = fallBackUrl;
//		System.out.println(Arrays.toString(objects));
//	}

		@Test
	public void testMethod() throws Throwable {

		String test = 	"abc#"	;
		System.out.println(test.substring(test.indexOf("#") + 1));
		System.out.println(test.substring(0, test.indexOf("#")));
		BoardMoreConfigVo boardMoreConfigVo = new BoardMoreConfigVo();
		boardMoreConfigVo.setBelongLevel(1);
		boardMoreConfigVo.setComponentId("1");
		boardMoreConfigVo.setHotProposal(1);
		boardMoreConfigVo.setPackageName("com.xiaomi");
		boardMoreConfigVo.setProjectId(2L);

		System.out.println(GsonUtil.toJson(boardMoreConfigVo));


		ThreadLocal<String> threadLocal = new ThreadLocal<>();

		List<Integer> integers = Lists.newArrayList(1, 2, 3, 4);

		Thread[] threads = new Thread[integers.size()];
		int i = 0;
		for (Integer integer : integers) {
			threads[i++] = new Thread(() -> {
				threadLocal.set(Thread.currentThread().getName());
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "---" + threadLocal.get());
			});
			threads[i - 1].start();
		}

		for (Thread t : threads) {
			t.join();
		}

	}

	private Queue<Thread> workerQueue = new LinkedList<>();

	private void testMethod2() {
		List<Future<String>> futureList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Future<String> submit = fixedThreadPool.submit(() -> testJob());
			futureList.add(submit);
		}

		if (!CollectionUtils.isEmpty(futureList)) {
			for (Future<String> stringFuture : futureList) {
				try {
					String result = stringFuture.get(1, TimeUnit.MILLISECONDS);
					System.out.println(result);
				} catch (Exception e) {
					Enumeration<Thread> threadEnumeration = concurrentHashMap.keys();
					while (threadEnumeration.hasMoreElements()) {
						Thread thread = threadEnumeration.nextElement();
						if (!thread.isDaemon()) {
							Long timeStart = concurrentHashMap.get(thread);
							if (System.currentTimeMillis() - timeStart > 10) {
								try {
									thread.interrupt();
									System.out.println("Try interrupt thread");
								} catch (Exception ex) {
									System.out.println("Try interrupt thread failed");
									ex.printStackTrace();
								}
							}
						}
					}
					e.printStackTrace();
				}
			}
		}

		//#1
		//#2
		//#3
		//#4 A
	}

	private String testJob() {
		String reuslt = "0";
		try {
			Thread currentThread = Thread.currentThread();
			System.out.println("Start " + currentThread.getName());
			concurrentHashMap.put(currentThread, System.currentTimeMillis());

			Random random = new Random(System.currentTimeMillis());
			reuslt = "" + random;
			int nextInt = random.nextInt(1000);
			try {
				Thread.sleep(nextInt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(currentThread.getName());
			System.out.println("End " + currentThread.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reuslt;
	}

	private void work() {
		Thread thread = Thread.currentThread();
		workerQueue.add(thread);

		thread.isDaemon();
	}



	 private ExecutorService fixedThreadPoolTest = Executors.newFixedThreadPool(10);

	private void testFuture() {

//		Map<String, Future<Object>> reusltMap = new HashMap<>();
//		Future<String> submit1 = fixedThreadPoolTest.submit(this::test1);
//		Future<Integer> submit2 = fixedThreadPoolTest.submit(this::test2);
//		Future<List<String>> submit3 = fixedThreadPoolTest.submit(this::test3);
//		reusltMap.put("submit1", submit1);
//		reusltMap.put("submit2", submit2);
//		reusltMap.put("submit3", submit3);

	}

	private static final Executor executorService = new ThreadPoolExecutor(10, 10, 10,
			TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10),
			new ThreadPoolExecutor.CallerRunsPolicy());

	@Test
	public void testThread() {

		// 2019-10-10 15:57:49
		long startTime = 1570694269000L;
		long stopTime = System.currentTimeMillis();

		int threadNum = 10;

		long stepLen = (stopTime - startTime) / threadNum;
		System.out.println(new Date().toString());
		for (int i = 1; i <= threadNum; i++) {
			long stop = startTime + stepLen * i;
			long start = stop - stepLen;


			executorService.execute(() -> cycleTurn(start, stop));


		}

		System.out.println(new Date().toString());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date().toString());
		System.exit(1);
	}



	@Test
	public void testThreadParam() {

		Random random = new Random();
		int times = 2000;
		while (times-- > 0) {
			int randomPage = random.nextInt(20);
			String type = "hot";
			String after;
			if (randomPage % 2 == 0) {
				type = "latest";
			}
			after = String.valueOf(randomPage * 6);
			if (after.equals("0")) {
				after = "";
			}
			System.out.println(type + "," + after);
		}
	}



	public void cycleTurn(long start, long stop) {

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(start + " " + stop);
	}





	static final int MAXIMUM_CAPACITY = 1 << 30;
	static  int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}



	public static HashSet<String> getSplitSet(String element) {
		LinkedHashSet<String> splitSet = null;
		if (!StringUtils.isEmpty(element)) {
			splitSet = new LinkedHashSet<>();
			String[] splitArray = element.split("\\|");
			for (String split : splitArray) {
				splitSet.add(split.trim());
			}
		}

		return splitSet;
	}


	@Test
	public void testMethod12() throws Throwable {
		System.out.println(getSplitSet("558495|18788534|558535|18066617|"));
		System.out.println(getSplitSet("558495|18788534|558535|18066617"));


		Map<String, Object> paramMap = new HashMap<>();
		 Map<String, Double> paramMap2 = new HashMap<>();
		 paramMap.put("a", 1);
		 paramMap2.put("a", 1.50D);


		System.out.println(paramMap.get("a").hashCode());
		System.out.println(paramMap2.get("a").intValue());


		ResultDto<String> resultDtoStr = new ResultDto();
		resultDtoStr.setCode(0);
		resultDtoStr.setCodeMsg("23");
		resultDtoStr.setData("ABC");
		ResultDto resultDto = resultDtoStr;
		System.out.println(GsonUtil.toJson(resultDto));

		ResultDto<DemoPojo> resultDtoPojo = resultDto;
		System.out.println(GsonUtil.toJson(resultDtoPojo));
		System.out.println(GsonUtil.toJson(resultDtoPojo.getData()));

		System.out.println(GsonUtil.toJson(resultDtoPojo.getData().getClass().getName()));
		DemoPojo data = resultDtoPojo.getData();
		System.out.println(GsonUtil.toJson(data));
//		String withPerf = DoWorkSomethingDemo.   ithPerf("", this::test1, 1);
//		Integer integer = DoWorkSomethingDemo.executeWithPerf("", test2, 1);
//		List<String> strings = DoWorkSomethingDemo.executeWithPerf("", this::test3, 1);
	}

	private String test1() {
		return "a";
	}


	public String testThrowable()  throws Throwable {

		throw new Throwable();




	}



	private Integer test2() {
		return 1;
	}
	private List<String> test3() {
		return Lists.newArrayList("1", "4");
	}



	class TryUtil implements ExecutorApi<String> {


		@Override
		public String execute() throws Throwable {
			try {
				return testThrowable();
			} catch (Throwable throwable) {
				throw throwable;
			}
		}
	}






}
