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

public class BavardFrame extends JFrame implements PapotageListener {

	private static final long serialVersionUID = 1L;

	private JPanel writeAndSendPanel;

	private JTextArea chatDisplay;
	private JTextField chatWritter;

	private JButton sendMessageButton;

	private JPanel sendPanel;

	private Bavard bavard;

	private String password;

	private JScrollPane displayScrollPanel;

	private JMenu connectToConcierge;

	private BavardFrame bavarFrame;

	public JMenu getConnectToConcierge() {
		return connectToConcierge;
	}

	public void setConnectToConcierge(JMenu connectToConcierge) {
		this.connectToConcierge = connectToConcierge;
	}

	public BavardFrame(Bavard bavard, String name) {

		bavarFrame = this;

		this.setTitle(name);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Content of the frame

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
		// the bavard he wants to get
		for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {
			JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(cf.getConcierge().getName());
			connectToConcierge.add(jCheckBoxMenuItem);

			jCheckBoxMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

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

		this.bavard = bavard;

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

		this.sendMessageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chatWritter.getText().isEmpty()) {

					for (ConciergeFrame cf : EPapotage.getConciergeFrames()) {

						if (cf.getConcierge().getListeners().contains(bavarFrame)) {
							cf.getConcierge().sendMessageToAllListeners(name, chatWritter.getText());
							cf.writeLogs(name + ": " + chatWritter.getText());
						}
					}

					chatWritter.setText("");
				}

			}
		});

		this.writeAndSendPanel.getRootPane().setDefaultButton(this.sendMessageButton);
	}

	public void bavardConnection(String name) {
		if (this.chatDisplay.getText().isEmpty()) {
			this.chatDisplay.setText("Hello " + name);
		} else {
			this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + "Hello " + name);
		}

	}
	
	public Component getComponentByName(String name) {
		for(Component item : getConnectToConcierge().getComponents()) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		
		return null;
	}

	public static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}

	public void sendMessage(String name, String text) {
		this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + name + ": " + text);
	}

	public Bavard getBavard() {
		return bavard;
	}

	public void setBavard(Bavard bavard) {
		this.bavard = bavard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
