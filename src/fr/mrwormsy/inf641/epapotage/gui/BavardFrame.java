package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.Bavard;
import fr.mrwormsy.inf641.epapotage.EPapotage;
import fr.mrwormsy.inf641.epapotage.PapotageListener;

//We are implementing PapotageListener, because we want to register it to a Concierge
public class BavardFrame extends JFrame implements PapotageListener {

	// This is not necessary because we don't want to serialize this class, but we
	// let it not to get the warning
	private static final long serialVersionUID = 1L;

	// All the variables

	private JPanel writeAndSendPanel;
	private JTextArea chatDisplay;
	private JTextField chatWritter;
	private JButton sendMessageButton;
	private JPanel sendPanel;
	private JScrollPane displayScrollPanel;
	private JMenu connectToConcierge;

	private Bavard bavard;
	private BavardFrame bavarFrame;

	// The password which is hashed with md5
	private String password;

	// The constructor needs a bavard and a name
	public BavardFrame(Bavard bavard, String name) {

		// This is used to get the instance of the object inside the Listeners
		bavarFrame = this;

		// Basic things
		this.setTitle(name);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// We don't want the program to finish, just the window to close, this is why we
		// are using DISPOSE_ON_CLOSE instead of EXIT_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// If the person closes the window we inform the Concierges who are listening to
		// the bavard, that his window has been closed
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent windowEvent) {
				for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {
					if (cf.getConcierge().getListeners().contains(bavarFrame)) {
						cf.writeLogs(name + " logged out");
					}
				}
			}
		});

		// Content of the frame, no need to explain

		this.chatDisplay = new JTextArea();
		this.chatDisplay.setEditable(false);

		displayScrollPanel = new JScrollPane(this.chatDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.displayScrollPanel.setPreferredSize(new Dimension(594, 321));

		this.chatWritter = new JTextField("Message");
		this.chatWritter.setBounds(25, 370, 475, 20);

		this.sendMessageButton = new JButton("Send");
		this.sendMessageButton.setBounds(525, 370, 50, 20);

		this.writeAndSendPanel = new JPanel();
		this.writeAndSendPanel.setPreferredSize(new Dimension(10, 10));
		this.sendPanel = new JPanel();

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnBavard = new JMenu("Concierge");
		menuBar.add(mnBavard);

		connectToConcierge = new JMenu("Connect to");
		mnBavard.add(connectToConcierge);

		// Add all the available Concierges to him and then he will be able to select
		// the bavard he wants to get in touch with
		for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {
			JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(cf.getConcierge().getName());
			connectToConcierge.add(jCheckBoxMenuItem);

			// We add an ActionListener to know if the Bavard wants to connect or to
			// disconnect from the Concierge
			jCheckBoxMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// Here we choose to connect or not
					if (jCheckBoxMenuItem.getState()) {
						cf.getConcierge().addListener(bavard);
						cf.getConcierge().addListener(bavarFrame);

						cf.writeLogs(name + " is now following you");

					} else {
						cf.getConcierge().removeListener(bavard);
						cf.getConcierge().removeListener(bavarFrame);

						cf.writeLogs(name + " is no longer following you");
					}
				}
			});

		}

		// Set set the Bavard of the BavardFrame
		this.bavard = bavard;

		// We are now using a GroupLayout which is pretty hard to explain and to deal
		// with, but we finally succeed :D

		writeAndSendPanel.setLayout(new BoxLayout(writeAndSendPanel, BoxLayout.Y_AXIS));
		writeAndSendPanel.add(this.displayScrollPanel);
		writeAndSendPanel.add(sendPanel);

		GroupLayout groupLayout = new GroupLayout(sendPanel);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		sendPanel.setLayout(groupLayout);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(this.chatWritter)
				.addComponent(this.sendMessageButton));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(this.chatWritter).addComponent(this.sendMessageButton));

		this.setContentPane(writeAndSendPanel);

		// We add an ActionListener to the button used to send messages
		this.sendMessageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// If the message contains something
				if (!chatWritter.getText().isEmpty()) {

					// We loop through all the Concierge and "tell" to the them which this bavard is
					// connected to send message to their listeners and then write logs (to keep a
					// trace)
					for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {

						if (cf.getConcierge().getListeners().contains(bavarFrame)) {
							cf.getConcierge().sendMessageToAllListeners(name, chatWritter.getText());
							cf.writeLogs(name + ": " + chatWritter.getText());
						}
					}

					// We reset the text of the chat writter
					chatWritter.setText("");
				}

			}
		});

		// We set a default button, thanks to this we only need to press enter and the
		// message will be sent
		this.writeAndSendPanel.getRootPane().setDefaultButton(this.sendMessageButton);
	}

	// Notify the connection of a Bavard
	public void bavardConnection(String name) {
		if (this.chatDisplay.getText().isEmpty()) {
			this.chatDisplay.setText("Hello " + name);
		} else {
			this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + "Hello " + name);
		}

	}

	// Get the jCheckBoxMenuItem with a corresponding name (with is the name of a
	// Concierge)
	public Component getjCheckBoxMenuItemByName(String name) {
		for (Component item : getConnectToConcierge().getComponents()) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	// Write a message received into the display area
	public void sendMessage(String name, String text) {
		this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + name + ": " + text);
	}

	// Getters and Setters...

	
	/**
	 * Returns an Image object that can then be painted on the screen. 
	 * The url argument must specify an absolute {@link URL}. The name
	 * argument is a specifier that is relative to the url argument. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 *
	 * @param  url  an absolute URL giving the base location of the image
	 * @param  name the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */
	public JPanel getWriteAndSendPanel() {
		return writeAndSendPanel;
	}

	public void setWriteAndSendPanel(JPanel writeAndSendPanel) {
		this.writeAndSendPanel = writeAndSendPanel;
	}

	public JTextArea getChatDisplay() {
		return chatDisplay;
	}

	public void setChatDisplay(JTextArea chatDisplay) {
		this.chatDisplay = chatDisplay;
	}

	public JTextField getChatWritter() {
		return chatWritter;
	}

	public void setChatWritter(JTextField chatWritter) {
		this.chatWritter = chatWritter;
	}

	public JButton getSendMessageButton() {
		return sendMessageButton;
	}

	public void setSendMessageButton(JButton sendMessageButton) {
		this.sendMessageButton = sendMessageButton;
	}

	public JPanel getSendPanel() {
		return sendPanel;
	}

	public void setSendPanel(JPanel sendPanel) {
		this.sendPanel = sendPanel;
	}

	public JScrollPane getDisplayScrollPanel() {
		return displayScrollPanel;
	}

	public void setDisplayScrollPanel(JScrollPane displayScrollPanel) {
		this.displayScrollPanel = displayScrollPanel;
	}

	public JMenu getConnectToConcierge() {
		return connectToConcierge;
	}

	public void setConnectToConcierge(JMenu connectToConcierge) {
		this.connectToConcierge = connectToConcierge;
	}

	public Bavard getBavard() {
		return bavard;
	}

	public void setBavard(Bavard bavard) {
		this.bavard = bavard;
	}

	public BavardFrame getBavarFrame() {
		return bavarFrame;
	}

	public void setBavarFrame(BavardFrame bavarFrame) {
		this.bavarFrame = bavarFrame;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
