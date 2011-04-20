/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class BundleMerger {

    private static Logger logger = Logger.getLogger(BundleMerger.class);

    public static void main(String[] args) throws IOException {
        GeneratorConfig c = new GeneratorConfig();
        File folder = new File(c.getAppWebPath(), "src" + File.separator + "java");

        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.getPath().endsWith(".generated");
            }
        };

        File[] listado = folder.listFiles(filter);


        logger.info("Size: " + listado.length);

        File salida = new File(c.getAppWebPath(), "src" + File.separator + "java" + File.separator + "Bundle.txt");


        Arrays.sort(listado, null);

        FileWriter writer = new FileWriter(salida, true);

        for (int i = 0; i < listado.length; i++) {
            File file = listado[i];
            logger.info(file.getPath());

            List texts = FileUtils.readLines(file);

            for (Object text : texts) {
                writer.write(String.valueOf(text)+"\n");
            }


        }

        writer.flush();
        writer.close();


    }
}
