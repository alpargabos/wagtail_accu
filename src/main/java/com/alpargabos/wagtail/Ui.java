package com.alpargabos.wagtail;

import java.io.PrintWriter;

public class Ui {
    PrintWriter printer = new PrintWriter(System.out);
    Reader reader  = new Reader();

    public String acquirePinCodeFor(String requestUrl) {
        printer.println("Open the following URL and grant access to your account:");
        printer.println(requestUrl);
        String pin= "";
        do{
            printer.println("Enter the PIN and hit enter.[PIN]:");
            printer.flush();
            pin = reader.getUserInput();
        } while(pin.length() != 7);
        return pin;
    }

    public void welcomeUser(String username) {
        printer.println("You are logged in as: " + username);
        printer.flush();
    }

    public void warnUser(String text) {
        printer.println("WARNING: " + text);
        printer.flush();
    }

    public void setOutput(PrintWriter output) {
        printer = output;
    }

    public void setInput(Reader input) {
        reader = input;
    }
}
