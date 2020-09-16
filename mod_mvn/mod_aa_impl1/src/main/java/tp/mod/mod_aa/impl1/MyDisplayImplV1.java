package tp.mod.mod_aa.impl1;

import tp.mod.mod_aa.itf.MyDisplayApi;

public class MyDisplayImplV1 implements MyDisplayApi{

	@Override
	public void display(String msg) {
		System.out.println(">>>" + msg);
	}

	@Override
	public void displayEx(String msg) {
		System.out.println(":::" + msg);

	}

}
