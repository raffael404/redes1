import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class AnswerClient implements Runnable{
	private DataOutputStream toClient;
	//private Scanner fromClient;
	BufferedReader fromClient;
	private InetAddress IP;
	
	public AnswerClient (Socket socket) throws IOException{
		//fromClient = new Scanner(socket.getInputStream());
		fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		toClient = new DataOutputStream(socket.getOutputStream());
		IP = socket.getInetAddress();
	}
	
	public void run() {
		String text;
		
		try {
			while((text = fromClient.readLine()) != null){
				System.out.println("From " + IP + ": " + text);
				try {
					toClient.writeBytes(text.toUpperCase() + '\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
