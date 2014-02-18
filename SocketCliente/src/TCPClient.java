import java.io.*;
import java.net.*;

public class TCPClient {
	Socket clientSocket;
	DataOutputStream outToServer;
	
	public TCPClient() throws UnknownHostException, IOException {
		clientSocket = new Socket("10.28.14.222", 5000);
		new Thread(new ReceiveMessage(clientSocket)).start();
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	public void enviaMensagem(String mensagem) throws Exception{
		
//		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		outToServer.writeBytes(mensagem + "\n");
	}
}
