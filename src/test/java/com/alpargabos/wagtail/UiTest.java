package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class UiTest {
    Ui ui = new Ui();
    Reader reader = mock(Reader.class);
    PrintWriter printer = mock(PrintWriter.class);


    @Before
    public void setUp() throws Exception {
        ui = new Ui();
        ui.setInput(reader);
        ui.setOutput(printer);
    }

    @Test
    public void acquirePinCodePrintsTheRequestUrlAndReturnsThePinCode() throws Exception {
        //given
        when(reader.getUserInput()).thenReturn("1234567");
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        verify(printer).write("Open the following URL and grant access to your account:\n");
        verify(printer).write("auth_url\n");
        verify(printer).write("Enter the PIN and hit enter.[PIN]:\n");
        verify(reader).getUserInput();
        assertEquals(pin, "1234567");
    }

    @Test
    public void acquirePinCodeUntilAPinCodeWithValidLengthProvided() throws Exception {
        //given
        when(reader.getUserInput()).thenReturn("123").thenReturn("1234567");
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        assertThat(pin, is("1234567"));
        verify(reader, times(2)).getUserInput();
    }
}
