package org.mycontrib.tp;

import reactor.core.publisher.Flux;

public class BasicTestReactorApp {

	public static void main(String[] args) {
		test1();
	}
	
	public static void test1(){
		Flux<Integer> flow = Flux.range(1, 10)
				.map(v -> v * v)
				.filter(v -> v % 2 == 0)
				;
		flow.subscribe((v)->{System.out.println(v);});
	}

}
