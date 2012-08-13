/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.tasks.interfaces;

import java.io.File;
import java.util.List;

/**
 *
 * @author xtecuan
 */
public interface TemplaterTask {

    public String getClasspathFrom();

    public String[] getFilenamesToProcess();

    public void doProcess();
//    public void doCopyResources(File source, File )
}
