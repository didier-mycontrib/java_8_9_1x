package tp.mod.mod_yy.app;

import java.lang.reflect.Field;

import javax.swing.JOptionPane;

//import tp.mod.mod_auto_s.MaClasse;
import tp.mod.mod_xx.pub.Cx;
import tp.mod.mod_xx_ext.CxExt;
import tp.mod.mod_yy.internal.InternalCy;

public class MyApp {
	
	private static void testOpenReflection(Cx objX) {
		Class classX = objX.getClass();
		for(Field f : classX.getDeclaredFields()) {
			f.setAccessible(true);
			String sVal="?";
			try {
				sVal = f.get(objX).toString();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				//Exception in thread "main" java.lang.reflect.InaccessibleObjectException: 
				//Unable to make field private java.lang.String tp.mod.mod_xx.pub.Cx.secret 
				//accessible: module tp.module.modxx does not "opens tp.mod.mod_xx.pub" to module tp.module.modyy
			}
			System.out.println( f.getType().getSimpleName()+ " " + f.getName() + "=" + sVal);
		}
	}

	public static void main(String[] args) {
		InternalCy icy = new InternalCy();
		icy.f2i("java");
		icy.f1i("java");
		
		CxExt.displayViaMyDisplayApi("my top secret message");
		
		//nb : tp.module.modyy --> tp.module.modxx --transitive--> tp.module.modzz
		CxExt.describeModuleOfJavaClass(CxExt.class);
		CxExt.describeModuleOfJavaClass(java.sql.DriverManager.class);
		
		/*
		//pour avec requires mod.auto.s; dans module-info.java et 
		//mod.auto.s = module automatique chargé explicitement via --module-path
		System.out.println(MaClasse.message1());
		CxExt.describeModuleOfJavaClass(MaClasse.class);
		//Cependant par parfaitement géré par eclipse/maven lors d'un run as / java app
		*/
		
		Cx objX = new Cx();
		objX.f1("abc");
		testOpenReflection(objX);
		
		JOptionPane.showMessageDialog(null, "ok with requires java.desktop");
		
		
	}

}
