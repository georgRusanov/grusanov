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
public class ClientTest {

    private static final String NL = System.getProperty("line.separator");
    private String systemIn;
    private String input;
    private String output;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"exit", "", String.format("exit%s", NL)},
                {Joiner.on(NL).join("hello", "exit"), "Hey!", Joiner.on(NL).join("hello", "exit" + NL)}
        });
    }

    public ClientTest(String systemIn, String input, String output) {
        this.systemIn = systemIn;
        this.input = input;
        this.output = output;
    }

    @Test
    public void clientTest() {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Socket socket = mock(Socket.class);
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Client client = new Client(socket);
            System.setIn(new ByteArrayInputStream(systemIn.getBytes()));
            client.run();
            assertThat(out.toString(), is(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}