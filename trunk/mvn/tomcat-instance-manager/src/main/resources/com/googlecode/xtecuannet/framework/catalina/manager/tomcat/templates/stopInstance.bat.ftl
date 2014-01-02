@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_45
set CATALINA_HOME=D:\Work\IDB\Java\apache-tomcat-7.0.47
set CATALINA_OPTS=-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+CMSClassUnloadingEnabled -XX:+UseParallelGC -Xbatch -Dorg.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER=true
set PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%PATH%
set CATALINA_BASE=%CATALINA_HOME%\instances\${instanceName}
echo Stopping ${instanceName} please wait...........
catalina.bat stop
echo ${instanceName} ...............................[done]