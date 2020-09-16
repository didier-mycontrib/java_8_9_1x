package tp.mod.mod_zz.pub;

public class Cz {
	public static String moduleOfJavaClassDescription(Class<?> aClass) {
		  Module module = aClass.getModule();
	      String description = "class=" + aClass.getName() 
	      +" is a part of module " + module.getName()
	      +" with descriptor=" + module.getDescriptor();
	      return description;
	}
}
