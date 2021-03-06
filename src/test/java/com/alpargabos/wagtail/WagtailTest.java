package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import static org.mockito.Mockito.*;

public class WagtailTest {
    Wagtail wagtail;
    Twitter twitter;
    private Ui ui;

    @Before
    public void setUp() {
        wagtail = new Wagtail();
        ui = mock(Ui.class);
        twitter = mock(Twitter.class);
        wagtail.setUI(ui);
        wagtail.setTwitter(twitter);
    }

    @Test
    public void welcomeUserAfterSuccessfulLogin() throws Exception {
        //given
        when(twitter.getOAuthRequestToken()).thenReturn(new RequestToken("", ""));
        when(ui.acquirePinCodeFor(anyString())).thenReturn("123");
        when(twitter.getOAuthAccessToken(any(RequestToken.class), anyString())).thenReturn(new AccessToken("", ""));
        //when
        wagtail.login();
        //then
        verify(ui).welcomeUser(anyString());
    }


    @Test
    public void warnUserInCaseOfAFakePinCode() throws Exception {
        //given
        when(twitter.getOAuthRequestToken()).thenReturn(new RequestToken("", ""));
        when(ui.acquirePinCodeFor(anyString())).thenReturn("123");
        when(twitter.getOAuthAccessToken(any(RequestToken.class), anyString())).thenThrow(new TwitterException("", null, 401));
        //when
        wagtail.login();
        //then
        verify(ui).warnUser(anyString());
    }

    @Test
    public void writeStatusSendsTheCorrectStatusToTwitter() throws Exception {
        //given
        when(ui.acquireNewStatus()).thenReturn("hello");
        Status status = mock(Status.class);
        when(twitter.updateStatus("hello")).thenReturn(status);
        //when
        wagtail.writeStatus();
        //then
        verify(twitter).updateStatus("hello");
        verify(ui).showStatus(status);
    }

}
