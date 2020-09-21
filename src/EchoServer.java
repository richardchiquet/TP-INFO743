import org.junit.*;
import org.junit.jupiter.api.Test;
import java.net.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServer {

    private GreetClient client;

    @Before
    public void setup() throws IOException {
        EchoClient client = new EchoClient();
        client.startConnection("127.0.0.1", 4444);
    }
    
    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String inputLine;

        while ((inputLine =in.readLine()) != null) {
            if (".".equals(inputLine)) {
                out.println("good bye");
                break;
            }
            
            out.println(inputLine);
        }
    }

    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void
    givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("!");
        String resp4 = client.sendMessage(".");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }
}