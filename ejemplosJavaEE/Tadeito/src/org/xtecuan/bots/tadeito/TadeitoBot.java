/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.bots.tadeito;

import org.apache.log4j.Logger;
import org.jibble.pircbot.PircBot;

/**
 *
 * @author xtecuan
 */
public class TadeitoBot extends PircBot {

    private static final String HOLA_ACTION = "hola";
    private static final String HELP_ACTION = "help";
    private static final String[] AVAILABLE_COMMANDS = {HOLA_ACTION, HELP_ACTION};
    private static final String[] COMMANDS_USE = {"Tadeito hola | Hola Tadeito",
        "Tadeito Help | help Tadeito"};
    private static StringBuilder commandsHelp;
    private static final Logger logger = Logger.getLogger(TadeitoBot.class);
    private static final String NAME = "Tadeito";
    private static final int PORT = 6667;
    private static final String HOST = "irc.freenode.net";
    private static final String LINUX_ORG_SV_CHANNEL_NAME = "#TadeitoBot";

    public TadeitoBot() {

        this.setName(NAME);


    }

    public static void main(String[] args) {
        TadeitoBot bot = new TadeitoBot();
        bot.setVerbose(true);

        try {
            bot.connect(HOST, PORT);
            bot.joinChannel(LINUX_ORG_SV_CHANNEL_NAME);
        } catch (Exception e) {
            logger.error("Error al conectar el bot al server!!!", e);
        }
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {

        sendMessageDueEvent(message, sender);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {


        if (sender.contains(NAME)) {
            sendMessage(LINUX_ORG_SV_CHANNEL_NAME, "Buenas ya llego el mago!!!!");
        } else {

            sendMessage(LINUX_ORG_SV_CHANNEL_NAME, "Ve ya llego este maje: " + sender);
        }
    }

    @Override
    protected void onDisconnect() {
        sendMessage(LINUX_ORG_SV_CHANNEL_NAME, "Ya me voy culerines!!!");

    }

    private void sendMessageDueEvent(String msg, String sender) {




        if (msg.contains(NAME)) {

            int counter = 0;

            if ((msg.toUpperCase().contains(HELP_ACTION.toUpperCase())
                    || msg.toLowerCase().contains(HELP_ACTION))) {



                for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
                    String cmd = AVAILABLE_COMMANDS[i];


                    logger.info(cmd);

                    if (msg.contains(cmd) || msg.contains(cmd.toUpperCase())) {

                        showHelpWhich(cmd);

                        counter++;
                    }


                }
                if (counter == 0) {
                    showHelpAll();
                    counter++;
                }
            }
            if (counter == 0 && (msg.toUpperCase().contains(HOLA_ACTION.toUpperCase())
                    || msg.toLowerCase().contains(HOLA_ACTION))) {

                sendMessage(LINUX_ORG_SV_CHANNEL_NAME, sender + ": Hola Jovencito!!!");
            }
        }



    }

    private void showHelpAll() {
        String header;
        header = new String("Ayuda TadeitoBot:\n");
        sendMessage(LINUX_ORG_SV_CHANNEL_NAME, header);

        for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
            String cmd = AVAILABLE_COMMANDS[i];
            String help = COMMANDS_USE[i];

            sendMessage(LINUX_ORG_SV_CHANNEL_NAME, cmd.toUpperCase() + " : " + help);
        }
    }

    private void showHelpWhich(String action) {

        int index = findCommandIndex(action);

        if (index >= 0) {

            sendMessage(LINUX_ORG_SV_CHANNEL_NAME, AVAILABLE_COMMANDS[index] + " : " + COMMANDS_USE[index]);
        }
    }

    private int findCommandIndex(String action) {

        int r = -1;

        for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
            String cmd = AVAILABLE_COMMANDS[i];

            if (cmd.equals(action)) {

                r = i;
                break;
            }

        }

        return r;

    }
}
