@echo off
REM instanceManager.bat
REM This script manages a set of Apache Tomcat 7 instances in CATALINA_HOME\instances directory
REM Author: Julian Rivera Pineda
REM E-mail: xtecuan@gmail.com
REM Date: Jan 12, 2014

set instance=%1
set action=%2


REM Change JAVA_HOME accord to yours settings
set JAVA_HOME=C:\Java\jdk1.7.0_45
REM Change CATALINA_HOME accord to yours settings
set CATALINA_HOME=C:\Java\apache-tomcat-7.0.47
set CATALINA_OPTS=-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms256m -Xmx256m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+CMSClassUnloadingEnabled -XX:+UseParallelGC -Xbatch -Dorg.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER=true
REM Change the name or location of your instances directory if you want
set INSTANCES_DIR=%CATALINA_HOME%\instances
set PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%PATH%


cls
goto managedCommand




:managedCommand
set CATALINA_BASE=%INSTANCES_DIR%\%instance%
echo "Executing action %action% for instance %instance% please wait..........."
if "%instance%"==""		 goto help
if "%action%"==""		 goto help
if "%action%"=="restart" goto restartAction
if "%action%"=="start"   goto startStopAction
if "%action%"=="stop"    goto startStopAction
if "%action%"=="help"    goto help


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
