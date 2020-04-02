import java.net.*;
import java.io.*;

public class Client {

	private static int PORT = 8765;
	private static String server = "127.0.0.1";
	
	public static void main(String[] args) throws  IOException {
		
		Socket socket = new Socket(server, PORT);
		socket.close();
	}
}
