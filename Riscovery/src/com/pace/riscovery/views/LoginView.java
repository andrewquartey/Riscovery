package com.pace.riscovery.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginView extends JDialog{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 874725266061579905L;
	private JTextField username;
	private JPasswordField password;
	private JLabel usernameLabel, passwordLabel;
	private JButton login, cancel;
	private boolean succeeded;
	static JFrame frame = new JFrame();
	
	public LoginView(){		
		super(frame, "Login", true);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		usernameLabel = new JLabel("Username: ");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.gridwidth = 1;
		panel.add(usernameLabel, c);
		
		username = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,20,0,10);
		c.gridwidth = 2;
		panel.add(username, c);
		
		passwordLabel = new JLabel("Password: ");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,0);
		c.gridwidth = 1;
		panel.add(passwordLabel, c);
		
		password = new JPasswordField(20);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10,20,0,10);
		c.gridwidth = 2;
		panel.add(password, c);
		
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.add(login);
		lowerPanel.add(cancel);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(lowerPanel, BorderLayout.PAGE_END);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	public static void main(String[] arg){
		
		// Windows Default look and feel. This affects all classes linked to the application.
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}
				
		LoginView login = new LoginView();		
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}