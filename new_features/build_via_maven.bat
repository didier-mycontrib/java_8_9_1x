cd /d "%-dp0"
set JAVA_HOME=C:\Prog\java\open-jdk\openjdk-17_windows-x64_bin\jdk-17
set MAVEN_HOME=C:\Prog\apache-maven-3.9.4
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
REM java -version
mvn clean package -DskipTests