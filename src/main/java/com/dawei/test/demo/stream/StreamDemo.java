package com.dawei.test.demo.stream;

import com.dawei.test.demo.pojo.DemoPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author by Dawei on 2018/8/30.
 */
public class StreamDemo {

/**
*  Intermediate：
*   一个流可以后面跟随零个或多个 intermediate 操作。
*     其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，
*     交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
*     map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered

* Terminal：
    一个流只能有一个 terminal 操作，
        当这个操作执行后，流就被使用“光”了，无法再被操作。
        所以这必定是流的最后一个操作。
        Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
        forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator

Short-circuiting：
    anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
    *
 *
 *
 *  PEEK
 * Stream.of("one", "two", "three", "four")
 *  .filter(e -> e.length() > 3)
 *  .peek(e -> System.out.println("Filtered value: " + e))
 *  .map(String::toUpperCase)
 *  .peek(e -> System.out.println("Mapped value: " + e))
 *  .collect(Collectors.toList());
 *
 *  字符串长度
 *
 *  Optional.ofNullable(text).map(String::length).orElse(-1);
 *
 *  Stream 元素组合起来
 *  Integer sum = integers.reduce(0, (a, b) -> a+b); 或
 *
 * Integer sum = integers.reduce(0, Integer::sum);
 *
 * // 字符串连接，concat = "ABCD"
 * String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
 * // 求最小值，minValue = -3.0
 * double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
 * // 求和，sumValue = 10, 有起始值
 * int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
 * // 求和，sumValue = 10, 无起始值
 * sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
 * // 过滤，字符串连接，concat = "ace"
 * concat = Stream.of("a", "B", "c", "D", "e", "F").
 *  filter(x -> x.compareTo("Z") > 0).
 *  reduce("", String::concat);
 *
 *  自建流操作
 *   private class PersonSupplier implements Supplier<DemoPojo> {
 *
 *             @Override
 *             public DemoPojo get() {
 *                 return null;
 *             });
 *
 *         Stream.generate(new PersonSupplier()).limit(100).
 *             collect(Collectors.groupingBy(DemoPojo::getBirthday)
 *
* */
    public static void main(String[] args) {
        /* 左闭右开 */
        IntStream.range(1, 3).forEach(System.out::println);
        /* 双闭合 */
        IntStream.rangeClosed(1, 3).forEach(System.out::println);


        List<Integer> nums = Arrays.asList(1, 2, 3, 4);

        long count = nums.stream().filter( f -> f > 0).count();
        System.out.println(count);
        List<DemoPojo> demoPojoList = new ArrayList<>();

        DemoPojo demoPojo = new DemoPojo();
        DemoPojo demoPojo1 = new DemoPojo();
        demoPojo.setId(111L);
        demoPojo1.setId(12121L);
        demoPojoList.add(demoPojo);
        demoPojoList.add(demoPojo1);

        long sum = demoPojoList.stream()
                .filter(demo -> demo.getId() > 11)
                .mapToLong(d -> d.getId())
                .sum();
        System.out.println(sum);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
        String text = "nnnnn";
        System.out.println(Optional.ofNullable(text).map(String::length).orElse(-1));
        text = "";
        System.out.println(Optional.ofNullable(text).map(String::length).orElse(-1));
        text = null;
        System.out.println(Optional.ofNullable(text).map(String::length).orElse(-1));


        Integer reduce = nums.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        Integer reduce1 = nums.stream().reduce(0, Integer::compareTo);
        System.out.println(reduce1);


        String reduce2 = Stream.of("A", "B", "C", "D").distinct().filter(f -> f != null).map(str -> str = str + ",").reduce("", String::concat);
        System.out.println(reduce2);


    }
}
