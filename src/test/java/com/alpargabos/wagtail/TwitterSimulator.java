package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;

public class TwitterSimulator {

    public TwitterSimulator() {
    }

    public MockWebServer getServerForLogin(String username) {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(getRequestToken()));
        server.enqueue(new MockResponse().setBody(getAuthToken()));
        server.enqueue(new MockResponse().setBody(getLoginJsonWithUsername(username)));
        try {
            server.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

    private String getLoginJsonWithUsername(String username) {
        return  "{\n" +
                "  \"id\": 2405056022,\n" +
                "  \"id_str\": \"2405056022\",\n" +
                "  \"name\": \"Wagtail\",\n" +
                "  \"screen_name\": \""+username+"\",\n" +
                "  \"location\": \"\",\n" +
                "  \"description\": \"wagtail, twitter console app, great, useful, made with BDD\",\n" +
                "  \"url\": null,\n" +
                "  \"entities\":  {\n" +
                "    \"description\":  {\n" +
                "      \"urls\":  []\n" +
                "    }\n" +
                "  },\n" +
                "  \"protected\": false,\n" +
                "  \"followers_count\": 0,\n" +
                "  \"friends_count\": 16,\n" +
                "  \"listed_count\": 0,\n" +
                "  \"created_at\": \"Sat Mar 22 21:18:59 +0000 2014\",\n" +
                "  \"favourites_count\": 0,\n" +
                "  \"utc_offset\": 7200,\n" +
                "  \"time_zone\": \"Amsterdam\",\n" +
                "  \"geo_enabled\": false,\n" +
                "  \"verified\": false,\n" +
                "  \"statuses_count\": 2,\n" +
                "  \"lang\": \"en\",\n" +
                "  \"status\":  {\n" +
                "    \"created_at\": \"Sat Apr 05 01:30:59 +0000 2014\",\n" +
                "    \"id\": 452257058373767200,\n" +
                "    \"id_str\": \"452257058373767168\",\n" +
                "    \"text\": \"Posting from @apigee's API test console. It's like a command line for the Twitter API! #apitools\",\n" +
                "    \"source\": \"<a href=\"https://apigee.com/console/twitter\" rel=\"nofollow\">Apigee's API Console</a>\",\n" +
                "    \"truncated\": false,\n" +
                "    \"in_reply_to_status_id\": null,\n" +
                "    \"in_reply_to_status_id_str\": null,\n" +
                "    \"in_reply_to_user_id\": null,\n" +
                "    \"in_reply_to_user_id_str\": null,\n" +
                "    \"in_reply_to_screen_name\": null,\n" +
                "    \"geo\": null,\n" +
                "    \"coordinates\": null,\n" +
                "    \"place\": null,\n" +
                "    \"contributors\": null,\n" +
                "    \"retweet_count\": 0,\n" +
                "    \"favorite_count\": 0,\n" +
                "    \"entities\":  {\n" +
                "      \"hashtags\":  [\n" +
                "         {\n" +
                "          \"text\": \"apitools\",\n" +
                "          \"indices\":  [\n" +
                "            87,\n" +
                "            96\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      \"symbols\":  [],\n" +
                "      \"urls\":  [],\n" +
                "      \"user_mentions\":  [\n" +
                "         {\n" +
                "          \"screen_name\": \"Apigee\",\n" +
                "          \"name\": \"Apigee\",\n" +
                "          \"id\": 26094126,\n" +
                "          \"id_str\": \"26094126\",\n" +
                "          \"indices\":  [\n" +
                "            13,\n" +
                "            20\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"favorited\": false,\n" +
                "    \"retweeted\": false,\n" +
                "    \"lang\": \"en\"\n" +
                "  },\n" +
                "  \"contributors_enabled\": false,\n" +
                "  \"is_translator\": false,\n" +
                "  \"is_translation_enabled\": false,\n" +
                "  \"profile_background_color\": \"C0DEED\",\n" +
                "  \"profile_background_image_url\": \"http://abs.twimg.com/images/themes/theme1/bg.png\",\n" +
                "  \"profile_background_image_url_https\": \"https://abs.twimg.com/images/themes/theme1/bg.png\",\n" +
                "  \"profile_background_tile\": false,\n" +
                "  \"profile_image_url\": \"http://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\n" +
                "  \"profile_image_url_https\": \"https://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\n" +
                "  \"profile_link_color\": \"0084B4\",\n" +
                "  \"profile_sidebar_border_color\": \"C0DEED\",\n" +
                "  \"profile_sidebar_fill_color\": \"DDEEF6\",\n" +
                "  \"profile_text_color\": \"333333\",\n" +
                "  \"profile_use_background_image\": true,\n" +
                "  \"default_profile\": true,\n" +
                "  \"default_profile_image\": false,\n" +
                "  \"following\": false,\n" +
                "  \"follow_request_sent\": false,\n" +
                "  \"notifications\": false\n" +
                "}";
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

}
