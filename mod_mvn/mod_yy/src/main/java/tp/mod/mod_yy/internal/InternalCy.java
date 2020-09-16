package tp.mod.mod_yy.internal;

//import tp.mod.mod_xx.internal.InternalCx; //pas accessible car package pas exporté
//import tp.mod.mod_xx_ext.internal.InternalCxExt; //pas accessible car package pas exporté

import tp.mod.mod_xx.pub.Cx;
import tp.mod.mod_xx_ext.CxExt; 

public class InternalCy {
	public void f2i(String s) { System.out.println("f2:"+s); }
	public void f1i(String s) { Cx cx =new Cx(); cx.f1(s); }
	public static double ajouter(double a,double b) { return CxExt.add(a, b); }
	//code impossible (basé sur classe InternalCx pas accessible / pas exportée ):
	//public void forbidden_f1(String s) { InternalCx icx =new InternalCx(); icx.f1i(s); }
}
