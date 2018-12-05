package com.socialgx;

import com.socialgx.Brocker.Receiver;
import com.socialgx.GaphEngine.GraphServer;


public class Main {

    private static GraphServer graphServer;
    private static Receiver receiver;

    public static void main(String[] args) {
        receiver = new Receiver();
        receiver.waitVkMessage();

        graphServer = new GraphServer();
    }

    public static GraphServer getGraphServer() {
        return graphServer;
    }

    public static Receiver getReceiver() {
        return receiver;
    }
}
