import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;


public class ReceiveMessage implements Runnable{
	String sentence;
	BufferedReader inFromServer;
	JTextArea textArea;
	
	public ReceiveMessage(Socket clientSocket, JTextArea textArea) throws IOException{
		this.textArea = textArea;
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	@Override
	public void run() {
		try {
			while((sentence = inFromServer.readLine())!=null){
				textArea.setText(sentence + "\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
