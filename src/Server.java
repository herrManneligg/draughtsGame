import java.net.*;
import java.util.ArrayList;

import java.io.*;

public class Server implements Runnable {

	private ServerSocket server;
	private ArrayList<ServerRunner> clients = new ArrayList<ServerRunner>();

	public Server() {
		try {
			server = new ServerSocket(8765);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		if (clients.size() < 2) {
			while (true) {
				Socket clientSocket = null;
// 			Possible add a limit of 2 clients
				try {
					clientSocket = server.accept();
					System.out.println("New player connected");
					ServerRunner client = new ServerRunner(clientSocket, this);
					clients.add(client);
					new Thread(client).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void transmit(MovementUpdate newMoves) {
		for (ServerRunner c : clients) {
			if (c != null) {
				c.transmitMovement(newMoves);
			}
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Server());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
