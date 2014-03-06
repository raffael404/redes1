package chat;
import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

import webcam.FacePanel;
import webcam.WebCam;

public class TCPClient {
	Socket clientSocket;
	Socket webCamSocket;
	DataOutputStream outToServer;
	DataOutputStream outToServerImage;
	
	public TCPClient(JTextArea textArea, FacePanel facePanel) throws UnknownHostException, IOException {
		clientSocket = new Socket("localhost", 5000);
		webCamSocket = new Socket("localhost", 5000);
		new Thread(new WebCam(facePanel,this)).start();
		new Thread(new ReceiveMessage(clientSocket, textArea)).start();
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServerImage = new DataOutputStream(webCamSocket.getOutputStream());
	}
	
	public void enviaMensagem(String mensagem) throws Exception{
		outToServer.writeBytes(mensagem + "\n");
		
	}
	
	public DataOutputStream getOutToServer() {
		return outToServer;
	}
	
	public DataOutputStream getOutToServerImage() {
		return outToServerImage;
	}
	
	
}
