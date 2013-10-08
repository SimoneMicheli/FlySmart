package gui;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginView extends View {
	/**
	 */
	private static final long serialVersionUID = 1202625258583639175L;
	private JPanel contentPane;
	protected JTextField ip;
	protected JTextField port;
	public JButton Connect;
	

	public LoginView() {
		lookAndFeel();
		setResizable(false);
		int l=282;
		int h=130;
		setSize(l, h);
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - l) / 2;
		int yFrame = (dimensioniSchermo.height - h) / 2;
		setLocation(xFrame, yFrame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setTitle("Login");
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Connect = new JButton("Connettiti");
		Connect.setBounds(10, 73, 254, 23);
		contentPane.add(Connect);
		
		
		ip = new JTextField();
		ip.setText("localhost");
		ip.setBounds(82, 11, 184, 20);
		contentPane.add(ip);
		ip.setColumns(10);
		
		port = new JTextField();
		port.setText("1099");
		port.setBounds(82, 42, 184, 20);
		contentPane.add(port);
		port.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 14, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 45, 46, 14);
		contentPane.add(lblPort);

	}
}
