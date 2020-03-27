package Stuff;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1156547173373301722L;
	static ServerSocket ssckt;
	static Socket sckt;
	static DataInputStream dtinpt;
	static DataOutputStream dtotpt;

	public Server() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
	}

	private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String msgout = "";
			msgout = txtbxfield.getText().trim();
			dtotpt.writeUTF(msgout);
		} catch (Exception e) {
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Server().setVisible(true);
			}
		});
		String msgin = "";
		try {
			ssckt = new ServerSocket(1201);
			sckt = ssckt.accept();
			dtinpt = new DataInputStream(sckt.getInputStream());
			dtotpt = new DataOutputStream(sckt.getOutputStream());
			while (!msgin.equals("exit")) {
				msgin = dtinpt.readUTF();
				txtbxarea.setText(txtbxarea.getText().trim() + "\n Client:" + msgin);
			}
		} catch (Exception e) {
		}
	}

	private javax.swing.JButton btnsend;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JTextArea txtbxarea;
	private javax.swing.JTextField txtbxfield;
}
