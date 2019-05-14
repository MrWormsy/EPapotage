package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

import fr.mrwormsy.inf641.epapotage.Bavard;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class CreateBavardGUI {

	// Variables
	private JFrame frame;
	private JPasswordField confirmInput;
	private JTextField usernameInput;
	private JPasswordField passInput;
	private JLabel usernameLabel;
	private JLabel passLabel;
	private JLabel confirmLabel;

	// This Gui is used to create a new Bavard with a username and two fields for
	// the password
	public CreateBavardGUI() {

		frame = new JFrame("Create Bavard");
		frame.setBounds(100, 100, 300, 250);
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

		// The group layout

		JButton registerButton = new JButton("Register Bavard");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(confirmLabel)
								.addComponent(usernameLabel).addComponent(passLabel))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(confirmInput).addComponent(passInput).addComponent(usernameInput))
						.addContainerGap(78, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(28)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(usernameLabel).addComponent(usernameInput,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(passLabel).addComponent(
						passInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(confirmLabel).addComponent(
						confirmInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(32).addComponent(registerButton).addContainerGap(33, Short.MAX_VALUE)));

		frame.getContentPane().setLayout(groupLayout);

		frame.setVisible(true);

		// We add an ActionListener when the register button is clicked
		registerButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				// We check that all the fields are not empty
				if (usernameInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Incorect username !");
					return;
				}

				if (passInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Password username !");
					return;
				}

				// We check that the Barvard does not exist yet
				if (!EPapotage.bavardExists(usernameInput.getText())) {

					// Check if the two passwords are the same (we are using md5 as the encryption
					// method)
					String md5HexPass = DigestUtils.md5Hex(passInput.getText());
					String md5HexConfirm = DigestUtils.md5Hex(confirmInput.getText());

					if (md5HexConfirm.equals(md5HexPass)) {
						// We create a Bavard and we add it to the list of Bavard in EPapotage

						// First we create a new Bavard
						Bavard bavard = new Bavard(usernameInput.getText());

						// We add this Bavard to a new BavardFrame
						BavardFrame bavardFrame = new BavardFrame(bavard, usernameInput.getText());

						// We add this BavardFrame to the list of BavardFrame of the Administrator
						EPapotage.getBavardFrames().add(bavardFrame);

						// Set the BavardFrame password
						bavardFrame.setPassword(md5HexPass);

						// Administration part

						// We need to add this JMenuItem to the list of BavardFrame to delete of the
						// AdministratorGUI
						JMenuItem bavardToDelete = new JMenuItem(bavard.getName());
						EPapotage.getAdministratorGUI().getBavardDelete().add(bavardToDelete);

						// Add to the JMenuItem list of the Admnistrator GUI
						JMenuItem bavardList = new JMenuItem(bavard.getName());
						EPapotage.getAdministratorGUI().getBavardlist().add(bavardList);

						// Then add the fact that if the Administrator clicks on this JMenuItem we
						// delete the BavardFrame
						bavardToDelete.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								// Remove this Bavard from every Concierges
								for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {
									cf.getConcierge().removeListener(bavard);
									cf.getConcierge().removeListener(bavardFrame);
								}

								// Remove this Concierge from the list JMenu and the delete menu
								EPapotage.getAdministratorGUI().getBavardDelete().remove(bavardToDelete);
								EPapotage.getAdministratorGUI().getBavardlist().remove(bavardList);

								// Close its window
								if (EPapotage.getBavardFrameFromName(bavard.getName()).isVisible()) {
									EPapotage.getBavardFrameFromName(bavard.getName()).setVisible(false);
								}

								// And them remove the frame
								EPapotage.getBavardFrames().remove(EPapotage.getBavardFrameFromName(bavard.getName()));
							}
						});

						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "The passwords do not match !");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "This bavard already exists !");
				}
			}
		});

		// Set the default button
		frame.getRootPane().setDefaultButton(registerButton);
	}
}
