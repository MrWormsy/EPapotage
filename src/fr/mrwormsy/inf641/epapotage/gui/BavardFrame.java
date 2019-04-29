package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.EPapotage;
import fr.mrwormsy.inf641.epapotage.Message;

public class BavardFrame extends JFrame {

	
private static final long serialVersionUID = 1L;
		
	private JPanel writeAndSendPanel;
	
	private JLabel chatDisplay;
	private JTextField chatWritter;
	
	private JButton sendMessageButton;
	
	
	public BavardFrame(String name) {

		this.setTitle(name);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	   	    
	    //Content of the frame
	    
	    this.chatDisplay = new JLabel();
	    this.chatDisplay.setText("----- Chat initialized -----");
	    this.chatDisplay.setBounds(25, 0, 550, 350);
	    
	    this.chatWritter = new JTextField("Message");
	    this.chatWritter.setBounds(25, 370, 475, 20);
	    	 
	    this.sendMessageButton = new JButton("Send");
	    this.sendMessageButton.setBounds(525, 370, 50, 20);
	    
	    this.writeAndSendPanel = new JPanel();
	    
	    GroupLayout layout = new GroupLayout(this.writeAndSendPanel);
	    this.writeAndSendPanel.setLayout(layout);
	   
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);


	    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
	    
	    hGroup.addGroup(layout.createParallelGroup().
	    		addComponent(this.chatWritter));
	    hGroup.addGroup(layout.createParallelGroup().
	    		addComponent(this.sendMessageButton));
	    
	    layout.setHorizontalGroup(hGroup);

	    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
	    
	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	    		addComponent(this.chatWritter).addComponent(this.sendMessageButton));
	    
	    layout.setVerticalGroup(vGroup);
	    
	    this.add(this.chatDisplay, BorderLayout.NORTH);
	    this.add(this.writeAndSendPanel, BorderLayout.SOUTH);
	    
	    this.sendMessageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (chatWritter.getText().length() >= 2 && EPapotage.getCurrentBavard() != null) {
					EPapotage.getConcierge().dispatchMessageBetweenBavards(name, chatWritter.getText());
					//sendMessage();
				}				
			}
		});		
	}
	
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}
	
	public void sendMessage(String name, String text) {
		this.chatDisplay.setText(convertToMultiline(this.chatDisplay.getText().replaceAll("(<html>)", "").replaceAll("(</html>)", "") + "\n" + name + " wrote : " + text));

		EPapotage.getCurrentBavard().sendMessage(new Message(EPapotage.getConcierge().getId_C(),"Sujet",this.chatWritter.getText()));
		
		this.chatWritter.setText("");
	}
	
}
