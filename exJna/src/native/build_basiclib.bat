REM on windows , GCC may be a subpart of codeblock 
REM https://www.codeblocks.org/downloads/binaries/

set GCC_PATH=C:\Program Files\CodeBlocks\MinGW\bin
set PATH=%GCC_PATH%;%PATH%

cd %~dp0

gcc -c basiclib.c
gcc -shared basiclib.o -o basiclib.dll
copy basiclib.dll ..\..\basiclib.dll