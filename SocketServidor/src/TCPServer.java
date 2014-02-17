import java.io.*;
import java.net.*;

public class TCPServer {

	/**
	 * Comentario Rony
	 * @param args
	 * @throws Exception
	 * Altera��o no github
	 */
	public static void main(String[] args) throws Exception {
		String clientSentence, capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}
