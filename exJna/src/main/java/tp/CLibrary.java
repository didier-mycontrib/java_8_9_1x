package tp;

import com.sun.jna.Native;
import com.sun.jna.Library;
import com.sun.jna.Platform;

public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)	Native.load((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

        void printf(String format, Object... args);
    }
