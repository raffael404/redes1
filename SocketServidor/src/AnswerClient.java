import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import javax.swing.JTextArea;


public class AnswerClient implements Runnable{
	private List<DataOutputStream> toClient;
	//private Scanner fromClient;
	BufferedReader fromClient;
	private String IP, name;
	JTextArea textArea;
	
	public AnswerClient (Socket socket, List<DataOutputStream> toClients, JTextArea textArea) throws IOException{
		fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		toClient = toClients;
		IP = socket.getInetAddress().getHostAddress();
		DataOutputStream thisClient = new DataOutputStream(socket.getOutputStream());
		thisClient.writeBytes("Digite seu nome: \n");
		this.name = fromClient.readLine();
		for (DataOutputStream toCliente : toClient) {
			toCliente.writeBytes(name + " se conectou." + '\n');
		}
		this.textArea = textArea;
	}
	
	
	public void run() {
		String text;
		
		try {
			
			while((text = fromClient.readLine()) != null){
				textArea.setText(textArea.getText() +"\nFrom " + IP + ": " + text);
				try {
					for (DataOutputStream toCliente : toClient) {
						toCliente.writeBytes(name + ": " + text + '\n');
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
