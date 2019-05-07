package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

import fr.mrwormsy.inf641.epapotage.Concierge;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class CreateConciergeGUI {
	
	//Variables
	
	private JFrame frame;
	private JPasswordField confirmInput;
	private JTextField usernameInput;
	private JPasswordField passInput;
	
	private JLabel usernameLabel;
	
	private JLabel passLabel;
	
	private JLabel confirmLabel;
	
	public CreateConciergeGUI() {

		frame = new JFrame("Create Concierge");

		frame.setBounds(100, 100, 350, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		usernameLabel = new JLabel("Username");
		
		passLabel = new JLabel("Password");
		
		confirmLabel = new JLabel("Confirm Password");
		
		confirmInput = new JPasswordField();
		confirmInput.setColumns(10);
		confirmInput.setEchoChar('•');
		
		usernameInput = new JTextField();
		usernameInput.setColumns(10);
		
		passInput = new JPasswordField();
		passInput.setColumns(10);
		passInput.setEchoChar('•');
		
		JButton registerButton = new JButton("Register Concierge");
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
				
				if (!EPapotage.conciergeExists(usernameInput.getText())) {
					
					//Check if the two passwords are identical
					String md5HexPass = DigestUtils.md5Hex(passInput.getText());
					String md5HexConfirm = DigestUtils.md5Hex(confirmInput.getText());
					
					if (md5HexConfirm.equals(md5HexPass)) {
						// We create a Bavard and we add it to the list of Bavard in EPapotage
						
						//First we create a new Concierge
						Concierge concierge = new Concierge(usernameInput.getText());
						
						//We add this Concierge to a new ConciergeFrame
						ConciergeFrame conciergeFrame = new ConciergeFrame(concierge);
						
						//We add this ConciergeFrame to the list of ConciergeFrame of the Administrator
						EPapotage.getConciergeFrames().add(conciergeFrame);
						
						//Set the ConciergeFrame password
						conciergeFrame.setPassword(md5HexPass);
						
						//Add this ConciergeFrame to all the BavardFrames that exist
						for (BavardFrame bf : EPapotage.getBavardFrames()) {
							
							JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(concierge.getName());
							
							bf.getConnectToConcierge().add(jCheckBoxMenuItem);
							
							jCheckBoxMenuItem.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									
									if (jCheckBoxMenuItem.getState()) {
										concierge.addListener(bf.getBavard());
										concierge.addListener(bf);
										
										conciergeFrame.writeLogs(bf.getBavard().getName() + " is now following you");
										
									} else {
										concierge.removeListener(bf.getBavard());
										concierge.removeListener(bf);
										
										conciergeFrame.writeLogs(bf.getBavard().getName() + " is no longer following you");
									}
								}
							});
						}
						
						// We need to add this JMenuItem to the list ConciergeFrame to delete of the AdministratorGUI
						JMenuItem conciergeToDelete = new JMenuItem(concierge.getName());
						EPapotage.getAdministratorGUI().getConciergeDelete().add(conciergeToDelete);
						
						//Add to the JMenuItem list of the Admnistrator GUI
						JMenuItem conciergeList = new JMenuItem(concierge.getName());
						EPapotage.getAdministratorGUI().getConciergelist().add(conciergeList);
						
						//Then add the fact that if the Administrator clicks on this JMenuItem we delete the ConciergeFrame
						conciergeToDelete.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								//Remove every JMenuItems related to this Concierge
								for (BavardFrame bf : EPapotage.getBavardFrames()) {
									bf.getConnectToConcierge().remove(bf.getComponentByName(concierge.getName()));
								}
								
								//Remove this Concierge from the list JMenu and the delete menu
								EPapotage.getAdministratorGUI().getConciergeDelete().remove(conciergeToDelete);
								EPapotage.getAdministratorGUI().getConciergelist().remove(conciergeList);
								
								//Close its window
								if (EPapotage.getConciergeFrameFromName(concierge.getName()).isVisible()) {
									EPapotage.getConciergeFrameFromName(concierge.getName()).setVisible(false);
								}
								
								//And them remove the frame
								EPapotage.getConciergeFrames().remove(EPapotage.getConciergeFrameFromName(concierge.getName()));
							}
						});
												
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "The passwords do not match !");
					}					
				} else {
					JOptionPane.showMessageDialog(frame, "This concierge already exists !");
				}				
			}
		});
		
		
		frame.getRootPane().setDefaultButton(registerButton);
	}
}
