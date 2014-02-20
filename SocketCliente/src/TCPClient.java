import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class TCPClient {
	Socket clientSocket;
	DataOutputStream outToServer;
	
	public TCPClient(JTextArea textArea) throws UnknownHostException, IOException {
		clientSocket = new Socket("localhost", 5000);
		new Thread(new ReceiveMessage(clientSocket, textArea)).start();
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	public void enviaMensagem(String mensagem) throws Exception{
		
//		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		outToServer.writeBytes(mensagem + "\n");
	}
}
