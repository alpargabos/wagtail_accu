package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaseStepdefs {

    protected String username;
    protected Wagtail wagtail;
    protected TwitterSimulator simulator = new TwitterSimulator();
    protected InputStream input;
    protected OutputStream output;

    protected void initWagtail(MockWebServer server) {
        output = new OutputStream() {
            StringBuilder content = new StringBuilder();

            @Override
            public void write(int i) throws IOException {
                System.out.print((char) i);
                content.append((char) i);
            }
            public String toString() {
                return content.toString();
            }
        };
        wagtail = new Wagtail(server.getUrl("/twitter/"));
        wagtail.setInput(input);
        wagtail.setOutput(output);
    }

    protected InputStream createInputStreamFrom(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

}

