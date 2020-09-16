package tp.mod.mod_xx.pub;

import tp.mod.mod_xx.internal.InternalCx;

public class Cx {
	
	private String secret="007";
	
	public void f1(String s) { 
		InternalCx icx = new InternalCx();
		icx.f1i("**" + s);
	}
}
