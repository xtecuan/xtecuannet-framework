<?xml version='1.0' encoding='utf-8'?>
${r"<!--"}Created on ${aDateTime?iso_local}${r"-->"}
<#assign aDateTime = .now>
<#assign aTime = aDateTime?time>
${r"<!--"} Tomcat Default ports:
	shutdownPort = 8005
	httpPort = 8080
	redirectPort = 8443
	ajpPort = 8009
	
${r"-->"}

<Server port="${shutdownPort}" shutdown="SHUTDOWN">
 
  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
  <Listener className="org.apache.catalina.core.JasperListener" />
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
  <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />
  <GlobalNamingResources>
   
  <Resource name="UserDatabase" auth="Container"
              type="org.apache.catalina.UserDatabase"
              description="User database that can be updated and saved"
              factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
              pathname="conf/tomcat-users.xml" />
  </GlobalNamingResources>
  <Service name="Catalina">
  <Connector port="${httpPort}" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="${redirectPort}" />
   
    <Connector port="${ajpPort}" protocol="AJP/1.3" redirectPort="${redirectPort}" tomcatAuthentication="false"/>

    <Engine name="Catalina" defaultHost="localhost">

      <Realm className="org.apache.catalina.realm.LockOutRealm">
       
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase"/>
      </Realm>

      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_${instanceName}_access_log." suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
		<Context path="" docBase="${sitesPath}/${instanceName}/>
      </Host>
    </Engine>
  </Service>
</Server>
