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
	private InetAddress IP;
	
	public AnswerClient (Socket socket, List<Socket> listaSockets) throws IOException{
		//fromClient = new Scanner(socket.getInputStream());
		fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		toClient = new DataOutputStream(socket.getOutputStream());
		toClient = new ArrayList<DataOutputStream>();
		for (int i = 0; i < listaSockets.size(); i++) {
			toClient.add(new DataOutputStream(listaSockets.get(i).getOutputStream()));
		}
		IP = socket.getInetAddress();
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
