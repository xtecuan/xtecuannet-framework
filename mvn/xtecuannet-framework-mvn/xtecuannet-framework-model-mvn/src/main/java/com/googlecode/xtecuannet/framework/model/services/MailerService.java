/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.model.services;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public interface MailerService extends Serializable {

    public void sendEmail(String email, String subject, String body);

    public void sendEmail(String from, String subject, String to, String bodyText, String filename);

    public void sendEmail(String from, String subject, String to, String bodyText, byte[] data, String filename);
}
