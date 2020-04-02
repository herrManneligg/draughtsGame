import java.awt.Color;
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
         } catch(IOException e) {
             e.printStackTrace();
         }
     }
     
     public Void doInBackground() {
     	
         System.out.println("Started read worker");
         
         MovementUpdate move = null;
         
         try {
             while((move = (MovementUpdate)inputStream.readObject())!= null) {
            	
            	 SquareButton prev = (SquareButton) this.parent.getView().get_square_button()[move.getPrevSquare()[0]][move.getPrevSquare()[1]];
            	 SquareButton next = (SquareButton) this.parent.getView().get_square_button()[move.getNextSquare()[0]][move.getNextSquare()[1]];
            	 
            	 this.parent.move(prev, next);
                 System.out.println(move);
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
