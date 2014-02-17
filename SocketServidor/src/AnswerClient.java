import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AnswerClient implements Runnable{
	private List<DataOutputStream> toClient;
	//private Scanner fromClient;
	BufferedReader fromClient;
	private String IP;
	
	public AnswerClient (Socket socket, List<DataOutputStream> toClients) throws IOException{
		//fromClient = new Scanner(socket.getInputStream());
		fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		toClient = new DataOutputStream(socket.getOutputStream());
		toClient = toClients;
		IP = socket.getInetAddress().getHostAddress();
	}
	
	public void run() {
		String text;
		
		try {
			while((text = fromClient.readLine()) != null){
				System.out.println("From " + IP + ": " + text);
				try {
					for (DataOutputStream toCliente : toClient) {
						toCliente.writeBytes(text.toUpperCase() + '\n');
					}
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
