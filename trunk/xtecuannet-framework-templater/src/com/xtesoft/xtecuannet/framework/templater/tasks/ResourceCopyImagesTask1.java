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
public class ResourceCopyImagesTask1 implements TemplaterTask {

    private static Logger logger = Logger.getLogger(ResourceCopyImagesTask1.class);
    public static final String CLASSPATH_FROM = "/com/xtesoft/xtecuannet/framework/templater/resources/images/";
    public static final String TARGET_DIR = "images";
    public static final String[] FILENAMES = {"add.png", "bookmark.png", "bottomBack.png", "browse.png", "calendar.png",
        "cancel.png", "chatback.png", "clear.png", "closebar.png", "csv.png", "delete.png", "dialogclose.png", "dialoghd.gif",
        "disk.png", "doc.png", "download.gif", "excel.png", "head_blue.png", "head_green.png", "home.png", "hy_active.png", "hy_hover.png",
        "hy.png", "img01.jpg", "img02.jpg", "img03.jpg", "img04.jpg", "img05.jpg", "img06.jpg", "img07.jpg", "img08.jpg", "keyboardpencil.png",
        "left.png", "loading.gif", "login.png", "map.png", "menu_body.gif", "menuitem_back.png", "minus.png", "mobile.png", "mp3.png", "new-ico.gif",
        "new.png", "next.png", "open.png", "pdf.png", "picture.png", "plus.png", "previous.png", "prime_logo.png", "print.png", "ps3.jpg", "ps3.png",
        "redo.png", "right.png", "save_icon.png", "save.png", "search.png", "select.png", "spacer.gif", "sprite-menu.gif", "terminal.png",
        "toggle_blue.png", "undo.png", "update.png", "upload.png", "xml.png"};

    public void doCopy(File image) {

        try {
            InputStream is = ResourceCopyImagesTask1.class.getResourceAsStream(CLASSPATH_FROM + image.getName());
            FileOutputStream fos = new FileOutputStream(image);
            int result = IOUtils.copy(is, fos);

            logger.info("Image: " + image.getName() + " copied: " + FileUtils.byteCountToDisplaySize(result));

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
