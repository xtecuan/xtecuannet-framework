<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.xtecuan.ejemplos.spring</groupId>
    <artifactId>crud-modelo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>crud-modelo</name>
    <url>http://maven.apache.org</url>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <repositories>
        <repository>
            <url>http://download.eclipse.org/rt/eclipselink/maven.repo/</url>
            <id>eclipselink</id>
            <layout>default</layout>
            <name>Repository for library EclipseLink (JPA 2.0)</name>
        </repository>
        <repository>
            <id>xtecuan.org</id>
            <name>Xtecuan.org repository</name>
            <url>http://xtecuan.org/xtecuannet_framework/repository</url>
        </repository>
    </repositories>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>eclipselink</artifactId>
            <version>2.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>javax.persistence</artifactId>
            <version>2.0.3</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>org.eclipse.persistence.jpa.modelgen.processor</artifactId>
            <version>2.3.2</version>
            <scope>provided</scope>
        </dependency>
         <dependency>
            <groupId>com.xtesoft.xtecuannet.framework</groupId>
            <artifactId>xtecuannet-framework-model-mvn</artifactId>
            <version>1.0.2-SNAPSHOT</version>
        </dependency>
    </dependencies>
    
</project>