package tp.mod.mod_aa.impl2;

import tp.mod.mod_aa.itf.MyDisplayApi;

public class MyDisplayImplV2 implements MyDisplayApi{

	@Override
	public void display(String msg) {
		System.out.println(">>>" + msg + "<<<");
	}

	@Override
	public void displayEx(String msg) {
		System.out.println(":::" + msg + ":::" );

	}

}
