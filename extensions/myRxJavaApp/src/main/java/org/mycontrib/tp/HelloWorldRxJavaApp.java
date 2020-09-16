package org.mycontrib.tp;

import io.reactivex.rxjava3.core.Flowable;

public class HelloWorldRxJavaApp {

	public static void main(String[] args) {
		Flowable.just("Hello world").subscribe(System.out::println);
	}

}
