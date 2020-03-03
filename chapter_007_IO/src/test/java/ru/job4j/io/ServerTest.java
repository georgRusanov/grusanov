package ru.job4j.io;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ServerTest {
    private static final String NL = System.getProperty("line.separator");
    private String input;
    private String output;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"exit", ""},
                {Joiner.on(NL).join("hello", "exit"), String.format("Hello, dear friend, I'm a oracle.%s%s", NL, NL)},
                {Joiner.on(NL).join("hi", "exit"), String.format("I don't understand%s", NL)}
        });
    }

    public ServerTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Test
    public void serverTest() {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Socket socket = mock(Socket.class);
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Server server = new Server(socket);
            server.run();
            assertThat(out.toString(), is(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}