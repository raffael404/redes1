import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ReceiveMessage implements Runnable{
	String sentence;
	BufferedReader inFromServer;
	
	public ReceiveMessage(Socket clientSocket) throws IOException{
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	@Override
	public void run() {
		try {
			while((sentence = inFromServer.readLine())!=null){
				System.out.println(sentence);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
