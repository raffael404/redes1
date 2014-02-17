import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class SendMessage implements Runnable{
	String sentence;
	BufferedReader inFromUser;
	DataOutputStream outToServer;
	
	public SendMessage(Socket clientSocket) throws IOException{
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	@Override
	public void run() {
		while(true){
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
			try {
				sentence = inFromUser.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outToServer.writeBytes(sentence + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
