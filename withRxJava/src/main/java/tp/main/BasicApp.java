package tp.main;

import io.reactivex.rxjava3.core.Observable;

public class BasicApp {
	//inspiration: https://www.baeldung.com/rx-java à ajuster v1 vers v3
	
	private String result="";

	public static void main(String[] args) {
		helloWorldRxJava();
		BasicApp basicApp = new BasicApp();
		basicApp.withOnNextOnErrorOnCompleteCallbacks();
	}
	
	public static void helloWorldRxJava() {
		Observable<String> observable = Observable.just("Hello world RxJava");
		observable.subscribe(s -> System.out.println(s));
	}
	
	public void withOnNextOnErrorOnCompleteCallbacks() {
		result="";
		String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
		Observable<String> observable = Observable.fromArray(letters);
		observable.subscribe(
		  letter -> result += letter,  //OnNext
		  Throwable::printStackTrace, //OnError
		  () -> result += "_completed" //OnCompleted
		);
		System.out.println("result="+result);//abcdefg_completed
	}

}
