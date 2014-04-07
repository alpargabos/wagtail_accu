package com.alpargabos.wagtail;

import java.io.PrintWriter;

public class Ui {
    PrintWriter printer = new PrintWriter(System.out);
    Reader reader  = new Reader();

    public String acquirePinCodeFor(String requestUrl) {
        printer.write("Open the following URL and grant access to your account:\n");
        printer.write(requestUrl+"\n");
        String pin= "";
        do{
            printer.write("Enter the PIN and hit enter.[PIN]:\n");
            pin = reader.getUserInput();
        } while(pin.length() != 7);
        return pin;
    }

    public void welcomeUser(String username) {

    }

    public void warnUser(String text) {

    }

    public void setOutput(PrintWriter output) {
        printer = output;
    }

    public void setInput(Reader input) {
        reader = input;
    }
}
