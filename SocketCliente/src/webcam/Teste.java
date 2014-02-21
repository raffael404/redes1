package webcam;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.opencv.contrib.FaceRecognizer;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 * 
 * @author Ronyerison
 *
 */
public class Teste {
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Capturar imagem webcam");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		FacePanel facepanel = new FacePanel();
		frame.setContentPane(facepanel);
		
		MatToBufferImg imageConverter = new MatToBufferImg();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture webcam = new VideoCapture(0);
		if(!webcam.isOpened()){
			System.out.println("Não foi possivel conectar a camera!!!");
		}else{
			System.out.println("camera encontrada " + webcam.toString());
		}
		
		frame.setVisible(true);
		Mat webcam_image = new Mat();
		
		if( webcam.isOpened()){
			Thread.sleep(500);
			while(true){
				webcam.read(webcam_image);
				if(!webcam_image.empty()){
					Thread.sleep(200);
					imageConverter.setMatriz(webcam_image, ".jpg");
					BufferedImage bufimg = imageConverter.getBufferedImage();
					
					facepanel.setFace(bufimg);
					facepanel.repaint();
				}else{
					System.out.println("Nada capturado da webcam");
					break;
				}
			}
			webcam.release();
		}
	}
}
