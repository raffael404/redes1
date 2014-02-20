import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;


public class TCPServer {
	JTextArea textArea;
	
	public TCPServer(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public void run() throws IOException{
		List<DataOutputStream> toClients = new ArrayList<DataOutputStream>();
		ServerSocket serverSocket = new ServerSocket(5000);
		
		while(true){
			Socket socket = serverSocket.accept();
			toClients.add(new DataOutputStream(socket.getOutputStream()));
			new Thread(new AnswerClient(socket,toClients,textArea)).start();	
		}
	}
	
}
