
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

	public class Writer implements Runnable {
	    private Socket socket;
	    public Writer(Socket s) {
	        this.socket = s;
	    }
	    public void run() {
	        try {
	            Scanner sc = new Scanner(System.in);
	            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
	            
	            SquareButton prevLoc;
	            SquareButton nextLoc;
	            
//	            while(true) {
//	                os.writeObject(new MovementUpdate(prevLoc,nextLoc));
//	            }
//	            sc.close();
	            os.close();
	        }catch(IOException e) {
	            e.printStackTrace();
	        }
	    }
}
