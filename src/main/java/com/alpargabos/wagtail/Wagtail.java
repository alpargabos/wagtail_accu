package com.alpargabos.wagtail;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.PrintWriter;
import java.net.URL;

public class Wagtail {
    private static final String CONSUMER_KEY = "q9Vhuh8DwgnIG99ULDcS3g";
    private static final String CONSUMER_SECRET = "1evGkZGOympVOmoHDvQLxkbnWCU5PgrBU8oFrTAZw";
    private Twitter twitter;
    private ConfigurationBuilder cb = new ConfigurationBuilder();
    private Ui ui = new Ui();

    public Wagtail() {
        Configuration conf = cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .build();
        twitter = new TwitterFactory(conf).getInstance();
    }

    public Wagtail(URL url) {
        cb.setRestBaseURL(url.toString());
        cb.setOAuthRequestTokenURL(url.toString());
        cb.setOAuthAccessTokenURL(url.toString());
        Configuration conf = cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .build();
        twitter = new TwitterFactory(conf).getInstance();
    }

    public void login() throws TwitterException {
        RequestToken requestToken = twitter.getOAuthRequestToken();
        String pin = ui.acquirePinCodeFor(requestToken.getAuthorizationURL());
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            twitter.setOAuthAccessToken(accessToken);
            ui.welcomeUser(twitter.getScreenName());
        } catch (TwitterException ex) {
            if (401 == ex.getStatusCode()) {
                ui.warnUser("The provided pin code is not valid!");
            }
        }

    }

    protected void setOutput(PrintWriter output) {
        ui.setOutput(output);
    }

    protected void setInput(Reader input) {
        ui.setInput(input);
    }

    protected void setUI(Ui ui) {
        this.ui = ui;
    }

    protected void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
