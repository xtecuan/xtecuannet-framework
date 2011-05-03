/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils.single;

import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class CrudGen {
    private static Logger logger = Logger.getLogger(CrudGen.class);
    
    public static void main(String[] args) {
        
        if(args.length==1){
        
            
        
        }else{
        
            logger.info("Usage: java -jar xtecuannet-framework-console-utils.jar entityName");
        }
        
    }
    
    
}
