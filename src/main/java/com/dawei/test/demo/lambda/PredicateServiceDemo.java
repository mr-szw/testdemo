package com.dawei.test.demo.lambda;


import com.dawei.test.demo.pojo.DemoPojo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Sinbad on 2019/12/22.
 */
public class PredicateServiceDemo {

    public List<DemoPojo> findById(Long id, List<DemoPojo> pojoList) {
        return findInterface(demoPojo -> id.equals(demoPojo.getId()), pojoList);
    }


    private List<DemoPojo> findInterface(Predicate<DemoPojo> pojoPredicate, List<DemoPojo> demoPojoList) {
        return demoPojoList.stream().filter(pojoPredicate).collect(Collectors.toList());
    }






}
