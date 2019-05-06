package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

import fr.mrwormsy.inf641.epapotage.Bavard;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class CreateBavardGUI {

	private JFrame frame;
	private JPasswordField confirmInput;
	private JTextField usernameInput;
	private JPasswordField passInput;
	
	private JLabel usernameLabel;
	
	private JLabel passLabel;
	
	private JLabel confirmLabel;
	
	public CreateBavardGUI() {

		frame = new JFrame("Create Bavard");
		
		frame.setBounds(100, 100, 300, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		usernameLabel = new JLabel("Username");
		
		passLabel = new JLabel("Password");
		
		confirmLabel = new JLabel("Confirm Password");
		
		confirmInput = new JPasswordField();
		confirmInput.setColumns(10);
		confirmInput.setEchoChar('�');
		
		usernameInput = new JTextField();
		usernameInput.setColumns(10);
		
		passInput = new JPasswordField();
		passInput.setColumns(10);
		passInput.setEchoChar('�');
		
		JButton registerButton = new JButton("Register Bavard");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(confirmLabel)
						.addComponent(usernameLabel)
						.addComponent(passLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(confirmInput)
						.addComponent(passInput)
						.addComponent(usernameInput))
					.addContainerGap(78, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmLabel)
						.addComponent(confirmInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(registerButton)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
		
		registerButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (usernameInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Incorect username !");
					return;
				}
				
				if (!EPapotage.bavardExists(usernameInput.getText())) {
					
					//Check if the two passwords are identical
					String md5HexPass = DigestUtils.md5Hex(passInput.getText());
					String md5HexConfirm = DigestUtils.md5Hex(confirmInput.getText());
					
					if (md5HexConfirm.equals(md5HexPass)) {
						// We create a Bavard and we add it to the list of Bavard in EPapotage
						
						//First we create a new Bavard
						Bavard bavard = new Bavard(usernameInput.getText());
						
						//We add this Bavard to a new BavardFrame
						BavardFrame bavardFrame = new BavardFrame(bavard, usernameInput.getText());
						
						//We add this BavardFrame to the list of BavardFrame of the Administrator
						EPapotage.getBavardFrames().add(bavardFrame);
						
						//Set the BavardFrame password
						bavardFrame.setPassword(md5HexPass);
						
						/*
						
						Bavard barvard = new Bavard(usernameInput.getText());
						
						EPapotage.addBavard(barvard);
						EPapotage.addBavardFrame(new BavardFrame(usernameInput.getText()));
						
						EPapotage.getConcierge().addBavard(barvard);
						
						EPapotage.getConciergeFrame().writeLogs(usernameInput.getText() + " has been registered as a new Bavard");
						
						EPapotage.getBavardFrameFromName(usernameInput.getText()).setPassword(md5HexPass);
						
						*/
						
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "The passwords do not match !");
					}					
				} else {
					JOptionPane.showMessageDialog(frame, "This bavard already exists !");
				}				
			}
		});
		
		
		frame.getRootPane().setDefaultButton(registerButton);
	}	
}
