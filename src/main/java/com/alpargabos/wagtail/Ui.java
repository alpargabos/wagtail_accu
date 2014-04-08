package com.alpargabos.wagtail;

import twitter4j.Status;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    PrintWriter printer = new PrintWriter(System.out);
    Scanner reader  = new Scanner(System.in);

    public String acquirePinCodeFor(String requestUrl) {
        printer.println("Open the following URL and grant access to your account:");
        printer.println(requestUrl);
        String pin;
        do{
            println("Enter the PIN and hit enter.[PIN]:");
            pin = reader.nextLine();
        } while(pin.length() != 7);
        return pin;
    }

    public void welcomeUser(String username) {
        println("You are logged in as: " + username);
    }

    public void warnUser(String text) {
        println("WARNING: " + text);
    }

    public void setOutput(OutputStream output) {
        printer = new PrintWriter(output);
    }

    public void setInput(InputStream input) {
        reader = new Scanner(input);
    }

    public String acquireNewStatus() {
        String tweet;
        do{
            println("Please write your tweet(in max. 140 characters):");
            tweet = reader.nextLine();
        } while(tweet.length() > 140);
        return tweet;
    }

    public void showStatus(Status status) {
        println("id:"+status.getId()+" "+status.getUser().getScreenName()+":"+status.getText());
    }

    private void println(String line){
        printer.println(line);
        printer.flush();
    }
}
