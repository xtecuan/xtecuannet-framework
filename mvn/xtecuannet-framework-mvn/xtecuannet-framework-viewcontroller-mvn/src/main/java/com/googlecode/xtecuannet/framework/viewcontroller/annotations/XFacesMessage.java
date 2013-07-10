/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.viewcontroller.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author xtecuan
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface XFacesMessage {

    String message();
}
