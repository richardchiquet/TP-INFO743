import org.junit.jupiter.api.Test;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String greeting = in.readLine();
        GregorianCalendar calendar = new GregorianCalendar();
        if(greeting.equals("DATE?")){
            out.println("Server: "+calendar.get(Calendar.DATE));
        }
        else if(greeting.equals("HOUR?")){
            out.println("Server: "+calendar.get(Calendar.HOUR));
        }
        else if(greeting.equals("MOON?")) {
            out.println("Server: I DON'T KNOW  ");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(1600);
    }

    @Test
    public void
    givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 1600);
        String response = client.sendMessage("hello server");
    }

}
