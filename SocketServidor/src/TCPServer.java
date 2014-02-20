import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;


public class TCPServer implements Runnable{
	JTextArea textArea;
	List<DataOutputStream> toClients;
	ServerSocket serverSocket;
	
	public TCPServer(JTextArea textArea) throws IOException {
		toClients = new ArrayList<DataOutputStream>();
		serverSocket = new ServerSocket(5000);
		this.textArea = textArea;
	}
	
	public void run(){
		while(true){
			Socket socket;
			try {
				socket = serverSocket.accept();
				toClients.add(new DataOutputStream(socket.getOutputStream()));
				new Thread(new AnswerClient(socket,toClients,textArea)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
