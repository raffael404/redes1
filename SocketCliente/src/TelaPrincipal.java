import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Window.Type;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DropMode;


public class TelaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private TCPClient tcpcliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textArea = new JTextArea();
		textArea.setDropMode(DropMode.ON);
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 210);
		frame.getContentPane().add(textArea);
		
		tcpcliente = new TCPClient(textArea);
		
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
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		textField.setBounds(0, 214, 342, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		
	}

}
