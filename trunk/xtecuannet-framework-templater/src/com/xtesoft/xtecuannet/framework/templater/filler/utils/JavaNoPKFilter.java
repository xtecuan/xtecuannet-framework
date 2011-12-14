/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler.utils;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author xtecuan
 */
public class JavaNoPKFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return file.getPath().endsWith(".java") && !file.getPath().contains("PK");
    }
}
