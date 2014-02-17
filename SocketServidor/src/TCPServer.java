import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {
	
	public static void main(String[] args) throws IOException{
		List<Socket> ipsConectados = new ArrayList<Socket>();
		ServerSocket serverSocket = new ServerSocket(5000);
		
		while(true){
			Socket socket = serverSocket.accept();
			ipsConectados.add(socket);
			new Thread(new AnswerClient(socket, ipsConectados)).start();
			
		}
	}
	
}
