package webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
	TCPClient tcpClient;
	
	public MatToBufferImg(TCPClient tcpClient) {
		this.tcpClient = tcpClient;
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
		
//		tcpClient.getOutToServer().write(byteArray);
//		tcpClient.getOutToServerImage().write(byteArray);
		BufferedImage bufImage = null;
		
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bufImage;
	}
	
	public byte[] getByteImage(){
		Highgui.imencode(fileExten, matriz, mob);
		return mob.toArray();
	}
}
