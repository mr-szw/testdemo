package com.dawei.test.demo.sort;

import com.dawei.test.demo.pojo.DemoPojo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * @author by Dawei on 2018/7/18.
 */
public class SortMethod {

    /* Function 的 排序方式 */
    public static void functionSortMethod(List<DemoPojo> demoPojoList) {
        Function<DemoPojo, Date> function = DemoPojo::getBirthday;
        Comparator<DemoPojo> comparator = Comparator.comparing(function).reversed();
        demoPojoList.sort(comparator);
    }

    /* Stream 流式排序 */
    public static List<DemoPojo> streamSortMethod(List<DemoPojo> demoPojoList) {
        return demoPojoList.stream().filter(demoPojo ->
            demoPojo.getId() != null).sorted(Comparator.comparing(DemoPojo::getBirthday).reversed()).collect(Collectors.toList());
    }

    /* Stream 流式排序 */
    public static void collectionsSortMethod(List<DemoPojo> demoPojoList) {
        demoPojoList.sort(Comparator.comparing(DemoPojo::getBirthday));
    }



    /*  冒泡排序 思想是冒出最大的*/
    public static void bubbleSort(List<Integer> numList) {
        if(!CollectionUtils.isEmpty(numList)) {
            int size = numList.size();
            int numIndex = 0;
            for( ; numIndex < size - 1; numIndex++) {
                for(int index = 0; index < size - numIndex -1; index++) {
                    if(numList.get(index) > numList.get(index + 1)) {
                        Integer numA = numList.get(index + 1);
                        numList.set(index + 1, numList.get(index));
                        numList.set(index, numA);
                    }
                }
            }
        }
    }

    /* 快速排序 位置交换 */
    public static void quickSort(List<Integer> numList) {
        numList.toArray();
    }


}
