/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xtecuan
 */
public interface CommandExecService {

    public int executeCommand(String commandOnly, List<String> arguments, Map<String, Object> argVals);
}
