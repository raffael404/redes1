import java.io.*;
import java.net.*;

public class TCPClient {
	
	public static void main(String[] args) throws Exception{
		String sentence;
		Socket clientSocket = new Socket("10.28.14.222", 5000);
		new Thread(new ReceiveMessage(clientSocket)).start();
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		while((sentence = inFromUser.readLine())!=null)
			outToServer.writeBytes(sentence + "\n");
		clientSocket.close();
	}
}
