package webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

import chat.TCPClient;


public class MatToBufferImg {
	Mat matriz;
	MatOfByte mob;
	String fileExten;
	private TCPClient tcpClient;
	public MatToBufferImg(TCPClient tcpClient) {
		this.tcpClient = tcpClient;
		// TODO Auto-generated constructor stub
	}
	
	public MatToBufferImg(Mat matriz, String fileExt){
		this.matriz = matriz;
		this.fileExten = fileExt;
	}
	
	public void setMatriz(Mat matriz, String fileExt) {
		this.matriz = matriz;
		this.fileExten = fileExt;
		this.mob = new MatOfByte();
	}
	
	public BufferedImage getBufferedImage() throws Exception{
		Highgui.imencode(fileExten, matriz, mob);
		byte[] byteArray = mob.toArray();
		
		tcpClient.getOutToServer().write(byteArray);
		BufferedImage bufImage = null;
		
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bufImage;
	}
}
