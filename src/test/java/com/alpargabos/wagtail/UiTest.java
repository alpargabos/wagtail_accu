package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UiTest {
    Ui ui = new Ui();
    OutputStream output;


    @Before
    public void setUp() throws Exception {
        ui = new Ui();
        output = new OutputStream() {
            StringBuilder content = new StringBuilder();
            @Override
            public void write(int i) throws IOException {
                content.append((char) i);
            }
            public String toString() {
                return content.toString();
            }
        };
        ui.setOutput(output);
    }

    @Test
    public void acquirePinCodePrintsTheRequestUrlAndReturnsThePinCode() throws Exception {
        //given
        ui.setInput(createInputStreamFrom("1234567"));
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        assertTrue(output.toString().contains("Open the following URL and grant access to your account:"));
        assertTrue(output.toString().contains("auth_url"));
        assertTrue(output.toString().contains("Enter the PIN and hit enter.[PIN]:"));
        assertEquals(pin, "1234567");
    }

    @Test
    public void acquirePinCodeUntilAPinCodeWithValidLengthProvided() throws Exception {
        //given
        ui.setInput(createInputStreamFrom("123\n1234567\n"));
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        assertThat(pin, is("1234567"));
    }

    private InputStream createInputStreamFrom(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    @Test
    public void welcomeUserWelcomesTheUserWithTheProvidedName() throws Exception {
        //when
        ui.welcomeUser("Alpar");
        //then
        assertTrue(output.toString().contains("Alpar"));
    }

    @Test
    public void warnUserAddsWarningToTheTextProvided() throws Exception {
        //when
        ui.warnUser("no no");
        //then
        assertTrue(output.toString().contains("WARNING: no no"));
    }
}
