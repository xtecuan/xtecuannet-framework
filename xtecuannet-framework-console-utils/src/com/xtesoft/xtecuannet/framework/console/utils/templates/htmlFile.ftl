<html>
<head>
   <title>${title}</title>
</head>

<body>

    <ul>


      <#list columnNames as i>

        <#if i.name != "serialVersionUID">



          <li>${i.name},${i.type.name}</li>
        </#if>

      </#list>

    </ul>




</body>




</html>