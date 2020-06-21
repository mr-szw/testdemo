package com.dawei.test.demo.executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.json.JSON;
import com.dawei.test.demo.utils.GsonUtil;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.var;

/**
 * Callable demo
 *
 * @author Dawei on 2019/12/2
 */
public class CallableDemo {

	public static void main(String[] args) throws Exception {

		CallableDemo callableDemo = new CallableDemo();
		//callableDemo.test1();
		callableDemo.test2();

	}

	private void test2() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long time = simpleDateFormat.parse("2019-11-01").getTime();
		var calendar = Calendar.getInstance();
		calendar.setTime(new Date(time));
		var yy = calendar.get(Calendar.YEAR);
		System.out.println(yy);


		//calendar.setTime(new Date(System.currentTimeMillis() + 1000 * 30 * 24 * 60 * 60L));

		calendar.add(Calendar.DATE, -1);
		var MM = calendar.get(Calendar.MONTH) + 1;
		var dd = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println(MM);
		System.out.println(dd);
	}

	private void test1() throws IOException {

		String extension = "dsv asdfs&dssdf&";
		String[] split = extension.split("&");
		Set<String> boardIdSet = Sets.newHashSet(split);
		String[] strings = boardIdSet.toArray(new String[]{});
		System.out.println(new Gson().toJson(strings));
		System.out.println(Arrays.toString(split));
		for (String entry : split) {
			boolean itemDataList = entry.contains("itemDataList");
			if(itemDataList) {
				System.out.println(entry);
				String substring = entry.substring(entry.indexOf(":") + 1);
				List<Map<String, String>> mapList = new Gson()
						.fromJson(substring, new TypeToken<List<Map<String, String>>>() {
						}.getType());
				if (!CollectionUtils.isEmpty(mapList)) {
					List<String> itemId = mapList.stream().map(item -> item.get("itemId"))
							.collect(Collectors.toList());
					System.out.println(itemId);
				}
			}

		}
		System.out.println("extension:android_version=7.1.2&miui_version=V11.0.1.0.NAMCNXM&oaid=47f8e95bef9ec0f&version=stable.1146&ob_version_backup=dev.1146&miui_vip_ph=drh5wusgkIFIXJ6X4Zu/4A==&miui_vip_w_ph=mbFYBIaWv+B3MFIUG1ZLZA==&miui_vip_slh=klr9BibuJ1EbpXjEaY/9mvYf5m0=&value=[{\"app_name\":\"com.xiaomi.vipaccount\",\"miui_version\":\"V11.0.1.0.NAMCNXM\",\"app_version\":\"dev.1146\",\"ref\":\"miaccount\",\"page\":\"mioHome\",\"itemDataList\":[{\"itemName\":\"mioHome_exposure_item\",\"itemId\":17883825,\"name\":3}]}]&flights=PUSH_SCHEDULE_HOURS_CONTROL".contains(""));

		Path path = Paths.get("/home/sinbad/work/workspace/temp/vip_user_board_group.txt");
		List<String> list = Files.readAllLines(path);
		System.out.println(GsonUtil.toJson(list));
	}

}




class TestClass implements Callable<String> {

	/**
	 * Computes a result, or throws an exception if unable to do so.
	 *
	 * @return computed result
	 * @throws Exception if unable to compute a result
	 */
	@Override public String call() throws Exception {

		String name = Thread.currentThread().getName();
		String result = name + UUID.randomUUID().toString();
		System.out.println(result);

		return result;
	}
}