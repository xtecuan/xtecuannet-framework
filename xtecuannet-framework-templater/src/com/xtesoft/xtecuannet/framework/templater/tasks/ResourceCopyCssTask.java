/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.tasks;

import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import com.xtesoft.xtecuannet.framework.templater.tasks.interfaces.TemplaterTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class ResourceCopyCssTask implements TemplaterTask {

    private static Logger logger = Logger.getLogger(ResourceCopyCssTask.class);
    public static final String CLASSPATH_FROM = "/com/xtesoft/xtecuannet/framework/templater/resources/css/";
    public static final String TARGET_DIR = "css";
    public static final String[] FILENAMES = {"default.css"};

    public void doCopy(File image) {

        try {
            InputStream is = ResourceCopyCssTask.class.getResourceAsStream(CLASSPATH_FROM + image.getName());
            FileOutputStream fos = new FileOutputStream(image);
            int result = IOUtils.copy(is, fos);

            logger.info("Css: " + image.getName() + " copied: " + FileUtils.byteCountToDisplaySize(result));

            is.close();
            fos.close();

        } catch (Exception e) {
            logger.error("Error: Copying file " + image.getName(), e);
        }

    }

    @Override
    public void doProcess() {
        try {

            File webDir = new File(FillerUtils.config.getWebappPath(), FillerUtils.config.getWebFolder());
            File target = new File(webDir, TARGET_DIR);

            if (!target.exists()) {
                boolean k = target.mkdirs();
                if (k) {

                    logger.info("Folder: " + target.getPath() + " created");
                }
            }

            for (int i = 0; i < FILENAMES.length; i++) {
                String fileImage = FILENAMES[i];

                File image = new File(target, fileImage);
                doCopy(image);
            }


        } catch (Exception e) {
            logger.error("Error processing image file copy ", e);
        }
    }

    @Override
    public String[] getFilenamesToProcess() {
        return FILENAMES;
    }

    @Override
    public String getClasspathFrom() {
        return CLASSPATH_FROM;
    }
}
