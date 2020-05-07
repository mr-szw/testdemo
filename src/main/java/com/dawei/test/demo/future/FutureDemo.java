package com.dawei.test.demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureDemo {


    public static void main(String[] args) throws Throwable {


        FutureDemo futureDemo = new FutureDemo();
        futureDemo.testFallBack();



//
//        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
//
//        final int index = 7;
//
//        for (int i = 0; i < 1; i++) {
//            Long aLong = submitTask(() -> futureTaskDemo.taskInteger(index), "", i * 10000);
//            System.out.println("Result " + aLong);
//        }
//

    }
	private int i = 0;
	private String exe() {
		System.out.println("do");
		return "" + 1 / i++;
	}


	private String back() {

		System.out.println("back");
		return "back" +  0;
	}
	public void testFallBack() {
//		ExecutorRetryWithFallBack<String> executorRetryWithFallBack = new ExecutorRetryWithFallBack(
//				() -> exe(), () -> back());
		ExecutorRetryWithFallBack<String> executorRetryWithFallBack = new ExecutorRetryWithFallBack(
				() -> exe(), () ->back());
		try {
			String do_it_ = executorRetryWithFallBack.execute("Do it ");
			System.out.println("do_it_" + do_it_);
		} catch (Throwable throwable) {
            System.out.println("Ree");
			throwable.printStackTrace();

		}

	}

    private static <V> V submitTask(TaskSupplier<V> taskSupplier, final String actionMark, int outTime) throws Throwable {
//        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
//        CompletableFuture<V> completableFuture = CompletableFuture.supplyAsync(taskSupplier::get)
//                .whenComplete(futureTaskDemo::syncCorrelateMission);

        CompletableFuture<V> completableFuture = CompletableFuture.supplyAsync(taskSupplier::execute)
                .whenComplete(FutureDemo::whenSyncCorrelateComplete);


//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        CompletableFuture<V> completableFuture = CompletableFuture.supplyAsync(taskSupplier::get, executorService);

        // V v = completableFuture.get();
        V v = null;
        try {
            v = completableFuture.get(outTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(actionMark + "catch=" + e.getMessage());

        }
        return v;

    }


    private static <R> void whenSyncCorrelateComplete(R result, Throwable throwable) {
        if (throwable != null) {
            System.out.println("whenSyncCorrelateComplete " + throwable.getMessage());
        }
        System.out.println(result);
    }




}
