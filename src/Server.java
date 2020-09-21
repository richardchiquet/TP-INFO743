import org.junit.jupiter.api.Test;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        Boolean test = false;
        Random r = new Random();
        int result= r.nextInt(100);
        while(test){

        }
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String greeting = in.readLine();
        if(greeting.equals(result)) test = true;
        else if(int(greeting)<result) out.println();

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
