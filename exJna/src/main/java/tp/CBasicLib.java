package tp;

import com.sun.jna.Native;
import com.sun.jna.Library;

public interface CBasicLib extends Library {
	CBasicLib INSTANCE = (CBasicLib) Native.load("basiclib", CBasicLib.class);
	//NB: basiclib.dll (on windows) or basiclib.so (on linux)
	//may be found in root of java project directory (with eclipse) or ....

	void display(String g); //from basiclib
	int addition(int a, int b); //from basiclib
	}
