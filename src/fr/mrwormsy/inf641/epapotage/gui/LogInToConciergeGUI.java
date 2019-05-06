package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.apache.commons.codec.digest.DigestUtils;

import fr.mrwormsy.inf641.epapotage.EPapotage;

public class LogInToConciergeGUI {

	private JFrame frame;
	private JTextField usernameInput;
	private JPasswordField passInput;
	
	private JLabel usernameLabel;
	
	private JLabel passLabel;
	
	public LogInToConciergeGUI() {

		frame = new JFrame("Log in to Concierge");
		frame.setBounds(100, 100, 300, 185);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		usernameLabel = new JLabel("Username");
		
		passLabel = new JLabel("Password");
		
		usernameInput = new JTextField();
		usernameInput.setColumns(10);
		
		passInput = new JPasswordField();
		passInput.setColumns(10);
		passInput.setEchoChar('•');
		
		JButton logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(usernameLabel)
						.addComponent(passLabel))
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(logInButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(passInput, Alignment.LEADING)
						.addComponent(usernameInput, Alignment.LEADING))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameLabel)
						.addComponent(usernameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passLabel)
						.addComponent(passInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(logInButton)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.getRootPane().setDefaultButton(logInButton);
		
		logInButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// We get the name and if the Concierge already exists we log him in, otherwise we deny him.
				if (EPapotage.conciergeExists(usernameInput.getText())) {
					
					// Check if the password entered matchs to the Bavard password
					if (DigestUtils.md5Hex(passInput.getText()).equalsIgnoreCase(EPapotage.getConciergeFrameFromName(usernameInput.getText()).getPassword())) {
						
						//We display the frame
						EPapotage.getConciergeFrameFromName(usernameInput.getText()).setVisible(true);;						
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "Your password is incorect !");
					}					
				} else {
					JOptionPane.showMessageDialog(frame, "This concierge does not exist !");
				}
				
			}
		});
		
		frame.setVisible(true);
	}
	
}
