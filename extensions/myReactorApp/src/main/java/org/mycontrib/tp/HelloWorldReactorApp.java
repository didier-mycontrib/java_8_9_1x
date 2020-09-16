package org.mycontrib.tp;

import reactor.core.publisher.Flux;

public class HelloWorldReactorApp {

	public static void main(String[] args) {
		Flux.just("Hello world").subscribe(System.out::println);
	}

}
