import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(5000);
		while(true){
			Socket socket = serverSocket.accept();
			new Thread(new AnswerClient(socket)).start();
		}
	}
	
}
