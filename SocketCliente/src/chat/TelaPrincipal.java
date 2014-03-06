package chat;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import webcam.FacePanel;
import webcam.WebCam;


public class TelaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private TCPClient tcpcliente;
//	private WebCam webCam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
//					window.webCam.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TelaPrincipal() throws UnknownHostException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 301);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(352, 214, 72, 36);
		frame.getContentPane().add(btnEnviar);
		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensagem = textField.getText();
				textField.setText(null);
				try {
					tcpcliente.enviaMensagem(mensagem);
//					scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					String mensagem = textField.getText();
					textField.setText(null);
					try {
						tcpcliente.enviaMensagem(mensagem);
//						scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		
		textField.setBounds(10, 214, 332, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 192);
		frame.getContentPane().add(scrollPane);
		
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		
		
		
		FacePanel facepanel = new FacePanel();
		facepanel.setBounds(434, 11, 264, 239);
		frame.getContentPane().add(facepanel);
		
		tcpcliente = new TCPClient(textArea, facepanel);
//		webCam = new WebCam(facepanel);
	}
}
