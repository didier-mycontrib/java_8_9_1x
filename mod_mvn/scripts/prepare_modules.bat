cd /d "%~dp0"
REM cd ..
REM mvn package
REM cd /scripts
copy ..\mod_xx\target\mod_xx.jar .\my_modules
copy ..\mod_yy\target\mod_yy.jar .\my_modules
copy ..\mod_zz\target\mod_zz.jar .\my_modules
copy ..\mod_auto_s\target\mod_auto_s.jar .\my_modules
copy ..\mod_aa_itf\target\mod_aa_itf.jar .\my_modules
copy ..\mod_aa_impl1\target\mod_aa_impl1.jar .\my_modules\mod_aa_impl.jar
REM copy ..\mod_aa_impl2\target\mod_aa_impl2.jar .\my_modules\mod_aa_impl.jar
pause