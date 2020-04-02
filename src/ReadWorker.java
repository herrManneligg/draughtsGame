import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.SwingWorker;

public class ReadWorker extends SwingWorker<Void,Void> {

	 private Socket socket = null;
     private ObjectInputStream inputStream = null;
     private Controller parent;
     
     public ReadWorker(Socket s, Controller parent) {
         this.socket = s;
         this.parent = parent;
         try {
             inputStream = new ObjectInputStream(this.socket.getInputStream());
         }catch(IOException e) {
             e.printStackTrace();
         }
     }
     
     public Void doInBackground() {
     	
         System.out.println("Started read worker");
         
         MovementUpdate move = null;
         
         try {
             while((move = (MovementUpdate)inputStream.readObject())!= null) {
                 System.out.println(move);
                 this.parent.move(move.getPrevMovement(), move.getNextMovement());
             }
         }catch(ClassNotFoundException e) {
             e.printStackTrace();
         }catch(IOException e) {
             e.printStackTrace();
         }finally {
             return null;
         }
     }
}
