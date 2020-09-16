package org.mycontrib.tp;

import io.reactivex.rxjava3.core.Flowable;

public class BasicTestRxJavaApp {

	public static void main(String[] args) {
		test1();
	}
	
	public static void test1(){
		Flowable<Integer> flow = Flowable.range(1, 10)
				.map(v -> v * v)
				.filter(v -> v % 2 == 0)
				;
		flow.subscribe((v)->{System.out.println(v);});
	}

}
