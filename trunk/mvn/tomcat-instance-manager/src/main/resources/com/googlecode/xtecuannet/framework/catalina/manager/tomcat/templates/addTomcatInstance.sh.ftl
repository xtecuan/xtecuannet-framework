#!/bin/sh
## This script creates a New Apache Tomcat 7 instance 
## Author: Julian Rivera Pineda
## E-mail: julianr@iadb.org
## Date: Dec 30, 2013

## Edit the follow two lines for Tomcat Base Instalation Directory and IIS/Apache Sites/VirtualHost
export catalinaHome=/D/Work/IDB/Java/apache-tomcat-7.0.47
export sitesPath=/D/Work/Sites

## Do not Edit from here to the Last
export instancesPath=$catalinaHome/instances


export instanceName=$1

if [ -n "$instanceName" ]
then
	echo "Creating Tomcat instance $instanceName"
	cd $instacesPath
	echo "Creating instance $instanceName directory"
	mkdir $instancesPath/$instanceName
	export instancePath=$instancesPath/$instanceName
	echo "Creating bin directory"
	mkdir $instancePath/bin
	echo "Creating conf directory"
	mkdir $instancePath/conf
	echo "Creating lib directory"
	mkdir $instancePath/lib
	echo "Creating logs directory"
	mkdir $instancePath/logs
	echo "Creating temp directory"
	mkdir $instancePath/temp
	echo "Creating webapps directory"
	mkdir $instancePath/webapps
	echo "Creating work directory"
	mkdir $instancePath/work
	echo "Copying servet.xml and web.xml files"
	cp -v $instancesPath/config/*.xml $instancePath/conf
	echo "Copying runInstance.bat start script"
	cp -v $instancesPath/config/*.bat $instancePath/bin
	echo "Creating Site Directory for Instance: $instanceName"
	mkdir -v $sitesPath/$instanceName
	echo "Copying SampleApp.war to webapps directory"
	cp -v $instancesPath/config/SampleApp.war $instancePath/webapps/SampleApp.war
else
	echo "Usage: addTomcatInstance.sh instanceName"
fi