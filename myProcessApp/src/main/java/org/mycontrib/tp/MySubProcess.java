package org.mycontrib.tp;

public class MySubProcess {

	public static void main(String[] args) {
		System.out.println("MySubProcess ...");
        try {
			Thread.sleep(5000); //5000ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.exit(0);
	}

}
