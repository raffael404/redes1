import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class AnswerClient implements Runnable{
	private DataOutputStream toClient;
	private Scanner fromClient;
	InetAddress IP;
	
	public AnswerClient (Socket socket) throws IOException{
		fromClient = new Scanner(socket.getInputStream());
		toClient = new DataOutputStream(socket.getOutputStream());
		IP = socket.getInetAddress();
	}
	
	public void run() {
		String text;
		
		while((text = fromClient.next()) != null){
			System.out.println("From " + IP + ": " + text);
			try {
				toClient.writeBytes(text.toUpperCase());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
