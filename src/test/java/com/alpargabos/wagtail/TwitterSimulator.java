package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TwitterSimulator {

    public MockWebServer getServerForLogin(String username) {
        return createServerWithResponses(
                new MockResponse().setBody(getRequestToken()),
                new MockResponse().setBody(getAuthToken()),
                new MockResponse().setBody(getLoginJsonWithUsername(username))
        );
    }

    private MockWebServer createServerWithResponses(MockResponse... responses){
        MockWebServer server = new MockWebServer();
        for(MockResponse resp: responses) {
            server.enqueue(resp);
        }
        try {
            server.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

    private String getLoginJsonWithUsername(String username) {
       return "{\"location\":\"\",\"default_profile\":true,\"profile_background_tile\":false,\"statuses_count\":1,\"lang\":\"en\",\"profile_link_color\":\"0084B4\",\"id\":2405056022,\"following\":false,\"protected\":false,\"favourites_count\":0,\"profile_text_color\":\"333333\",\"description\":\"wagtail, twitter console app, great, useful, made with BDD\",\"verified\":false,\"contributors_enabled\":false,\"profile_sidebar_border_color\":\"C0DEED\",\"name\":\"Wagtail\",\"profile_background_color\":\"C0DEED\",\"created_at\":\"Sat Mar 22 21:18:59 +0000 2014\",\"is_translation_enabled\":false,\"default_profile_image\":false,\"followers_count\":0,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\"geo_enabled\":false,\"status\":{\"contributors\":null,\"text\":\"my very first tweet\",\"geo\":null,\"retweeted\":false,\"in_reply_to_screen_name\":null,\"truncated\":false,\"lang\":\"en\",\"entities\":{\"symbols\":[],\"urls\":[],\"hashtags\":[],\"user_mentions\":[]},\"in_reply_to_status_id_str\":null,\"id\":447486650495827970,\"source\":\"web\",\"in_reply_to_user_id_str\":null,\"favorited\":false,\"in_reply_to_status_id\":null,\"retweet_count\":0,\"created_at\":\"Sat Mar 22 21:35:06 +0000 2014\",\"in_reply_to_user_id\":null,\"favorite_count\":0,\"id_str\":\"447486650495827970\",\"place\":null,\"coordinates\":null},\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"follow_request_sent\":false,\"entities\":{\"description\":{\"urls\":[]}},\"url\":null,\"utc_offset\":7200,\"time_zone\":\"Amsterdam\",\"notifications\":false,\"profile_use_background_image\":true,\"friends_count\":16,\"profile_sidebar_fill_color\":\"DDEEF6\",\"screen_name\":\""+username+"\",\"id_str\":\"2405056022\",\"profile_image_url\":\"http://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\"listed_count\":0,\"is_translator\":false}";
    }

    private String getAuthToken() {
        return "oauth_token=7588892-kagSNqWge8gB1WwE3plnFsJHAZVfxWD7Vb57p0b4&" +
                "oauth_token_secret=PbKfYqSryyeKDWz4ebtY3o5ogNLG11WJuZBc9fQrQo";
    }

    private String getRequestToken() {
        return "oauth_token=NPcudxy0yU5T3tBzho7iCotZ3cnetKwcTIRlX0iwRl0&" +
                "oauth_token_secret=veNRnAWe6inFuo8o2u8SLLZLjolYDmDP7SzL0YfYI&" +
                "oauth_callback_confirmed=true";
    }


    public MockWebServer getServerForInvalidLogin() {
        return createServerWithResponses(
                new MockResponse().setBody(getRequestToken()),
                new MockResponse().setBody("Not authorized!").setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
        );
    }
}
