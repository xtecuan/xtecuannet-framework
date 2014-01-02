REM @echo on
REM This script creates a New Apache Tomcat 7 instance 
REM Author: Julian Rivera Pineda
REM E-mail: julianr@iadb.org
REM Date: Dec 30, 2013

set instance=%1
set action=%2


set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_45
set CATALINA_HOME=D:\Work\IDB\Java\apache-tomcat-7.0.47
set CATALINA_OPTS=-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+CMSClassUnloadingEnabled -XX:+UseParallelGC -Xbatch -Dorg.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER=true
set INSTANCES_DIR=%CATALINA_HOME%\instances
set PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%PATH%


if "%instance%"=="" (

	echo "Error: Tomcat instanceName required to execute start/stop action"
	goto end
)

if "%action%"=="" (
	echo "Error: Action to execute required start,stop,restart"
	goto end
)

if %1a==a goto help
if %2a==a goto help
cls
goto managedCommand




:managedCommand
set CATALINA_BASE=%INSTANCES_DIR%\%instance%
echo "Executing action %action% for instance %instance% please wait..........."
if "%action%"=="restart" goto restartAction
if "%action%"=="start"   goto startStopAction
if "%action%"=="stop"    goto startStopAction
if "%action%"=="help"    goto help
if "%action%"==""		 goto end

:help
cls
echo Usage: instanceManager.bat instanceName action
echo Where instanceName es the Tomcat instance Name to execute some action
echo And action is three of the follow commands: start,stop or restart
goto end

catalina.bat start
echo "Action %action% for instance %instance% ...............................[done]"

:startStopAction
call %CATALINA_HOME%\bin\catalina.bat %action%
goto end

:restartAction
call %CATALINA_HOME%\bin\catalina.bat stop
call %CATALINA_HOME%\bin\catalina.bat start
goto end



:end
::@endlocal
if "%OS%"=="Windows_NT" @endlocal
if "%OS%"=="WINNT" @endlocal