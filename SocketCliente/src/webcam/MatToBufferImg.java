package webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;


public class MatToBufferImg {
	Mat matriz;
	MatOfByte mob;
	String fileExten;
	
	public MatToBufferImg() {
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
	
	public BufferedImage getBufferedImage(){
		Highgui.imencode(fileExten, matriz, mob);
		byte[] byteArray = mob.toArray();
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
