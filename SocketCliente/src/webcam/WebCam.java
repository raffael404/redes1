package webcam;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import chat.TCPClient;

/**
 * 
 * @author Ronyerison
 *
 */
public class WebCam implements Runnable {
	FacePanel facepanel;
	TCPClient tcpCliente;
	
	public WebCam(FacePanel facePanel, TCPClient tcpClient) {
		this.facepanel = facePanel;
		this.tcpCliente = tcpClient;
	}
	
	public void run(){
		MatToBufferImg imageConverter = new MatToBufferImg(tcpCliente);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture webcam = new VideoCapture(0);
		if(!webcam.isOpened()){
			System.out.println("Não foi possivel conectar a camera!!!");
		}else{
			System.out.println("camera encontrada " + webcam.toString());
		}
		
		Mat webcam_image = new Mat();
		
		if( webcam.isOpened()){
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
