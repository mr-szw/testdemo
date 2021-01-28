package com.dawei.test.demo.client;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by Dawei on 2018/6/5.
 */
@Slf4j
public class HttpClientTest {

	// 设置客户端从连接池获取链接的超时时间
	private static final int GET_CONN_TIMEOUT = 200;
	// 设置客户端发起TCP连接请求的超时时间
	private static final int CONN_SERVER_TIMEOUT = 100;
	// 设置客户端等待服务端返回数据的超时时间

	public static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

	public static void main(String[] args) throws IOException {


		CloseableHttpClient httpClient = getHttpClient("", 8888);
		CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet("http://.com"));


		//new HttpClientTest().doRequest();
		Map<String, Object> paramterMap = new HashMap<>();
		//paramterMap.put("demandId"	, "3");
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


	//设置客户端从连接池获取链接的超时时间
	private static final int GET_CONN_TIMEOUT = 20;
	//设置客户端发起TCP连接请求的超时时间
	private static final int CONN_SERVER_TIMEOUT = 20;
	//设置客户端等待服务端返回数据的超时时间
>>>>>>> 9cf97d6ee4da096d56b47b50816855941a826b73
	private static final int WAIT_SERVER_TIMEOUT = 100;

	// 总的大池子的连接数
	private static final int MAX_CONN_POOL_TOTAL = 10000;
	// 默认每个路由的池子大小
	private static final int MAX_DEFAULT_PER_ROUTE = 10;
	// 单个路由池的最大容量
	private static final int MAX_ROUTE_POOL = 6000;

	// 对应服务的端号
	public static final int PORT = 8888;

	public static void configEveryClient(HttpRequestBase httpRequestBase) {
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(GET_CONN_TIMEOUT)
				.setConnectTimeout(CONN_SERVER_TIMEOUT).setSocketTimeout(WAIT_SERVER_TIMEOUT)
				.build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 初始化并获取链接池
	 *
	 * @param hostname 基于hostname and port构建链接
	 * @param port 基于hostname and port构建链接
	 */
	public static CloseableHttpClient getHttpClient(String hostname, int port) {
		return createHttpClient(MAX_CONN_POOL_TOTAL, MAX_DEFAULT_PER_ROUTE, MAX_ROUTE_POOL,
				hostname, port);
	}

	/**
	 * 获取client链接
	 *
	 * @param maxTotal 整个池子的最大连接数
	 * @param maxDefaultPerRoute 默认的最大的路由的连接数 （池中池的默认最大连接数）
	 * @param maxRoute 指定hostname:port 下的最大连接数
	 * @param hostname 由hostname和port组成route
	 * @param port 由hostname和port组成route
	 * @return httpclient
	 */
	private static CloseableHttpClient createHttpClient(int maxTotal, int maxDefaultPerRoute,
			int maxRoute, String hostname, int port) {
		ConnectionSocketFactory plainConnSocketFactory = PlainConnectionSocketFactory
				.getSocketFactory();
		SSLContext sslContext;
		try {
			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, new TrustManager[] { createX509ExtendedTrustManagerImpl() },
					null);
		} catch (Exception var2) {
			log.error("create SSLContext error", var2);
			return null;
		}

		// 目前没有 https 不搞证书这一套
		// SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		LayeredConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
				sslContext);
		Registry<ConnectionSocketFactory> registry = RegistryBuilder
				.<ConnectionSocketFactory> create().register("http", plainConnSocketFactory)
				.register("https", sslConnectionSocketFactory).build();

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
				registry);
		// 设置将最大连接数
		poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
		// 设置将每个路由基础的连接数
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxDefaultPerRoute);
		HttpHost httpHost = new HttpHost(hostname, port);
		// 将目标主机路由的最大连接数增加
		poolingHttpClientConnectionManager.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
			if (executionCount >= 2) {// 如果已经重试了5次，就放弃
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
				return false;
			}
			if (exception instanceof UnknownHostException) {// 目标服务器不可达
				return false;
			}
			if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
				return false;
			}
			if (exception instanceof SSLException) {// SSL握手异常
				return false;
			}
			if (exception instanceof InterruptedIOException) {// 超时
				return false;
			}
			if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
				return true;
			}

			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			// 如果请求不是幂等的，就再次尝试
			return !(request instanceof HttpEntityEnclosingRequest);
		};

		return HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
				.setRetryHandler(httpRequestRetryHandler).build();
	}

	public static TrustManager createX509ExtendedTrustManagerImpl() {
		return new X509ExtendedTrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
					throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
					throws CertificateException {

			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType,
					SSLEngine engine) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType,
					SSLEngine engine) throws CertificateException {

			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
	}


	public static void main(String[] args) {
		String requestPath = "https://api-pre.vip.mi.com/";
		CloseableHttpClient httpClient = HttpClientTest.getHttpClient(requestPath, 8888);
		HttpGet httpGet = new HttpGet(requestPath);
		HttpClientTest.configEveryClient(httpGet);
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = httpResponse.getEntity();
		int statusCode = httpResponse.getStatusLine().getStatusCode();
	}



}
