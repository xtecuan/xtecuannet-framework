/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function setIndexInCombo(formName,comboName,index){     
    document.forms[formName].elements[comboName].selectedIndex = index;    
}

function populateGrid(){
    
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
        xmlhttp.open("GET","manttoAlumnos.mantto?id=" + Math.random()+"&action=list",true);
        xmlhttp.send();
    }
}
