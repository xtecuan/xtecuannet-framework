/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.resources.img;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author xtecuan
 */
public class ResourceImg {

    static public void writeImage(String name) {
//        ClassLoader loader = ClassLoader.getSystemClassLoader();

        InputStream is = ResourceImg.class.getResourceAsStream(name);

        try {

            File file = new File("/tmp/" + FilenameUtils.getBaseName(name)+"."+FilenameUtils.getExtension(name));

            FileOutputStream fos = new FileOutputStream(file);

            int salida = IOUtils.copy(is, fos);

            System.out.println("salida=" + salida);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        writeImage("/com/xtesoft/xtecuannet/framework/templater/resources/img/ajaxloading.gif");
    }
}
