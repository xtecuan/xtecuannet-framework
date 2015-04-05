<#assign creationDate = .now>
<#assign aTime = creationDate?time>
package ${javaBeansPackage};

import java.io.Serializable;
<#list fakeentityImports as fakeentityImport>
import ${fakeentityImport.javaImport};
</#list>


/**
 *
 * @author      ${autor}
 * @date        ${creationDate?iso_local}    
 * @description Fake Entity JavaBean:  ${javaName}  for table:  ${javaSQLTable}
 * @generated   ${scriptName} version ${scriptVersion}
 * 
 */
public class ${javaName} implements Serializable, ${javaBeansImplementsClassName} {

    private static final String SCHEMA = "${schemaName}";
    private static final String TABLE = "${tableName}";
    private static final String ID = "${idName}";
    <#list fakeentityProperties as fakeentityProperty>
    private ${fakeentityProperty.javaType} ${fakeentityProperty.columnName};
    </#list>
    
    public ${javaName}() {
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
