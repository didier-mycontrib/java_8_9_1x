package tp.mod.mod_xx_ext;

import java.util.Iterator;
import java.util.ServiceLoader;

import tp.mod.mod_aa.itf.MyDisplayApi;
import tp.mod.mod_xx_ext.internal.InternalCxExt;
import tp.mod.mod_zz.pub.Cz;

public class CxExt {
	
	private static MyDisplayApi displayService = null;
	
	public static double add(double x,double y) { return InternalCxExt.addition(x, y); }
	
	public static void describeModuleOfJavaClass(Class<?> aClass) {
	      System.out.println(Cz.moduleOfJavaClassDescription(aClass));
	}
	
	public static void displayViaMyDisplayApi(String msg) {
		if(displayService==null) {
		    Iterable<MyDisplayApi> myDisplayServices = ServiceLoader.load(MyDisplayApi.class); //list of found implementations
		    Iterator<MyDisplayApi> myDisplayServicesIterator =  myDisplayServices.iterator();
		    if(myDisplayServicesIterator.hasNext()) {
		         displayService = myDisplayServicesIterator.next(); //first implementation is choosen
		    }
		}
		if(displayService!=null) {
		    displayService.display(msg);
		}else{
			System.out.println("no MyDisplayApi implementation found !!!");
		}
	}
}
