<#ftl  >
<#setting boolean_format=computer>
<#import "/libs/mylib.ftl" as my>
<#import 
<#assign name1=value1 name2=value2 nameN=valueN>
<#assign name=value in namespacehash>
<#assign name>
capture this
</#assign>
<#assign name in namespacehash>
capture this
</#assign>

<#if condition>
...
<#elseif condition2>
...
<#else>
...
</#if>


<#list seq as x>
${x_index + 1}. ${x}<#if x_has_next>,</#if>
</#list>

<@my.method>

a < b
Romeo & Juliet
</@><#-- short end tag -->
