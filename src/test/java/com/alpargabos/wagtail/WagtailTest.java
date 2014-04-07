package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import static org.mockito.Mockito.*;

public class WagtailTest{
    Wagtail wagtail;
    Twitter twitter;
    private Ui ui;

    @Before
    public void setUp(){
        wagtail = new Wagtail();
        ui = mock(Ui.class);
        twitter = mock(Twitter.class);
        wagtail.setUI(ui);
        wagtail.setTwitter(twitter);
    }

    @Test
    public void welcomeUserAfterSuccessfulLogin() throws Exception {
        //given
        when(twitter.getOAuthRequestToken()).thenReturn(new RequestToken("",""));
        when(ui.acquirePinCodeFor(anyString())).thenReturn("123");
        when(twitter.getOAuthAccessToken(any(RequestToken.class), anyString())).thenReturn(new AccessToken("",""));
        //when
        wagtail.login();
        //then
        verify(ui).welcomeUser(anyString());
    }

}
