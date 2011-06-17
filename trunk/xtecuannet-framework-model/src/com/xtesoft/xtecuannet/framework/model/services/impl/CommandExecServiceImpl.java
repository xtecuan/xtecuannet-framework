/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;

import com.xtesoft.xtecuannet.framework.model.services.CommandExecService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class CommandExecServiceImpl implements CommandExecService {

    public static final Logger logger = Logger.getLogger(CommandExecServiceImpl.class);

    /**
     * 
     * @param commandOnly
     * @param arguments
     * @param argVals
     * @return 
     */
    public int executeCommand(String commandOnly, List<String> arguments, Map<String, Object> argVals) {
        int result = -1;

        CommandLine cmdLine = new CommandLine(commandOnly);

        for (String arg : arguments) {

            cmdLine.addArgument(arg);
        }


        cmdLine.setSubstitutionMap(argVals);

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        Executor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.setWatchdog(watchdog);

        try {
            // some time later the result handler callback was invoked so we
            // can safely request the exit value
            logger.info("Executing command: " + commandOnly);
            executor.execute(cmdLine, resultHandler);
            resultHandler.waitFor();
            result = resultHandler.getExitValue();

        } catch (ExecuteException ex) {
            logger.error("Error ejecutando comando: ", ex);
        } catch (IOException ex) {
            logger.error("Error ejecutando comando: ", ex);
        } catch (InterruptedException ex) {
            logger.error("Error ejecutando comando: ", ex);
        }


        return result;
    }

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {

        CommandExecServiceImpl impl = new CommandExecServiceImpl();

        String command = "/usr/bin/pg_dump";
        
        List<String> arguments = new ArrayList<String>(0);
        
        arguments.add("--host");
        arguments.add("${host}");
        arguments.add("--port");
        arguments.add("${port}");
        arguments.add("--username");
        arguments.add("${username}");
        arguments.add("--format");
        arguments.add("custom");
        arguments.add("--blobs");
        arguments.add("--verbose");
        arguments.add("--file");
        arguments.add("${file}");
        arguments.add("${base}");
        
        Map<String,Object> params = new HashMap<String,Object>(0);
        
        params.put("host", "localhost");
        params.put("port", "5432");
        params.put("username", "postgres");
        params.put("file", "/home/xtecuan/backups/siaarafia.backup");
        params.put("PGPASSWORD", "x2tecuan");
        params.put("base", "siaarafia");
        
        int result = impl.executeCommand(command, arguments, params);
        

        logger.info("resultado="+result);

    }
}
