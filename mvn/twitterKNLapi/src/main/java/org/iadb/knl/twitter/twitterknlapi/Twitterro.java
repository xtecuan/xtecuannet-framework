/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iadb.knl.twitter.twitterknlapi;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 *
 * @author julianr
 */
public class Twitterro {

    private static final String domain = "idb";
    private static final String user = "LRNROBOT";
    private static final String password = "IDBlrn#2014";
    private static final String proxyHost = "wwppool.local.iadb.org";
    private static final String proxyPort = "9090";

    public static void main(String[] args) {
//        Config.registerSmbURLHandler();
//        Config.setProperty("jcifs.smb.client.domain", domain);
//        Config.setProperty("jcifs.smb.client.username", user);
//        Config.setProperty("jcifs.smb.client.password", password);

        try {
//            Config.setProperty("jcifs.netbios.hostname",
//                    Config.getProperty("jcifs.netbios.hostname",
//                            InetAddress.getLocalHost().getHostName()));

            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", proxyPort);
            System.setProperty("http.auth.ntlm.domain", domain);
            Authenticator authenticator = new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password.toCharArray());
                }

            };
            Authenticator.setDefault(authenticator);
            Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus("Este arbitro va pitar la final de #bra vrs #uru");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            
//            Query query = new Query();
//            query.setQuery("");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
