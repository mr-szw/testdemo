package com.dawei.test.demo.future;

import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public  class FutureTaskDemo<U> {






    public <U> void whenSyncCorrelateComplete(U u,Throwable e){

        e.printStackTrace();
        System.out.println(u.toString());
    }

    public String taskString(String param) {
        return Strings.repeat(param, 2);
    }

    public Long taskInteger(Integer random) {
        int i = new Random().nextInt(10) / (random - 2);
        if (random.equals(7)) {
            return Long.parseLong("xxBx");
        }
        return (long) i;
    }


    public List<String> taskListString(Integer random) {
        return Lists.newArrayList("", "1", "3");
    }

    public <U> void syncCorrelateMission(U u, Throwable throwable) {
    }

    public Object syncCorrelateMission() {
        return null;
    }

    public  <U> U syncCorrelateMission(Supplier<U> supplier) {
        return supplier.get();
    }
}
