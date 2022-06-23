package ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages;

import java.io.DataOutputStream;
import java.io.IOException;

public class Sending {

    public synchronized static void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();

    }



}
