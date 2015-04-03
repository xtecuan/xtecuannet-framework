<#assign creationDate = .now>
<#assign aTime = creationDate?time>
package ${fakeentitypackage};

import java.io.Serializable;
import ${}
<#list fakeentityProperties as fakeentityProperty>
import ${fakeentityProperty.javaImport};
</#list>


/**
 *
 * @author      ${autor}
 * @date        ${creationDate?iso_local}    
 * @description Fake Entity JavaBean:  ${className}  for table:  ${javaSQLTable}
 * @generated   ${scriptName} version ${scriptVersion}
 * 
 */
public class ${className} implements Serializable, ${javaBeansImplementsClassName} {

    private static final String SCHEMA = "${schemaName}";
    private static final String TABLE = "${tableName}";
    private static final String ID = "${idName}";
    <#list fakeentityProperties as fakeentityProperty>
    private ${fakeentityProperty.javaType} ${fakeentityProperty.columnName};
    </#list>
    
    public ${className}() {
    }

    @Override
    public String getSchema() {
        return SCHEMA;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String getID() {
        return ID;
    }

    <#list fakeentityProperties as fakeentityProperty>
    
    public ${fakeentityProperty.javaType} get${fakeentityProperty.columnName}() {
        return ${fakeentityProperty.columnName};
    }

    public void set${fakeentityProperty.columnName}(${fakeentityProperty.javaType} ${fakeentityProperty.columnName}) {
        this.${fakeentityProperty.columnName} = ${fakeentityProperty.columnName};
    }

    </#list>
    
}
