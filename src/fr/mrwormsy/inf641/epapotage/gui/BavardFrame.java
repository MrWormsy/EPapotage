package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.Bavard;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class BavardFrame extends JFrame {

	
private static final long serialVersionUID = 1L;
		
	private JPanel writeAndSendPanel;
	
	private JTextArea chatDisplay;
	private JTextField chatWritter;
	
	private JButton sendMessageButton;
	
	private JPanel sendPanel;
	
	private Bavard bavard;
	
	
	public BavardFrame(String name) {

		this.setTitle(name);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	   	    
	    //Content of the frame
	    
	    this.chatDisplay = new JTextArea();
	    this.chatDisplay.setEditable(false);
	    this.chatDisplay.setText("----- Chat initialized -----");
	    this.chatDisplay.setBounds(25, 0, 550, 350);
	    
	    this.chatWritter = new JTextField("Message");
	    this.chatWritter.setBounds(25, 370, 475, 20);
	    	 
	    this.sendMessageButton = new JButton("Send");
	    this.sendMessageButton.setBounds(525, 370, 50, 20);
	    
	    this.writeAndSendPanel = new JPanel();
	    this.sendPanel = new JPanel();
	    
	    this.bavard = EPapotage.getBavardFromName(name);
	    
	    /*
	    
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
	    	
	   	*/
	            
        writeAndSendPanel.setLayout(new BoxLayout(writeAndSendPanel, BoxLayout.Y_AXIS));
        writeAndSendPanel.add(this.chatDisplay);
        writeAndSendPanel.add(sendPanel);
        
		GroupLayout groupLayout = new GroupLayout(sendPanel); 
		groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
        sendPanel.setLayout(groupLayout);
        

        groupLayout.setHorizontalGroup(  
                    groupLayout.createSequentialGroup()  
                                .addComponent(this.chatWritter)  
                                .addComponent(this.sendMessageButton));  
        groupLayout.setVerticalGroup(  
                     groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)  
                                .addComponent(this.chatWritter)  
                                .addComponent(this.sendMessageButton));
		
        this.setContentPane(writeAndSendPanel);
	            
	    this.sendMessageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chatWritter.getText().length() >= 2 && EPapotage.getCurrentBavard() != null) {
					
					// DEBUG
					
					//EPapotage.getConcierge().dispatchMessageBetweenBavards(name, chatWritter.getText());
					
					for (BavardFrame bf : EPapotage.getBavardsFrames()) {
						bf.sendMessage(name, chatWritter.getText());
					}	
					
					chatWritter.setText("");
				}				
			}
		});		
	    
	    this.writeAndSendPanel.getRootPane().setDefaultButton(this.sendMessageButton);
	}
	
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}
	
	public void sendMessage(String name, String text) {
		//this.chatDisplay.setText(convertToMultiline(this.chatDisplay.getText().replaceAll("(<html>)", "").replaceAll("(</html>)", "") + "\n" + name + " wrote : " + text));		
		this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + name + " wrote : " + text);
	}

	public Bavard getBavard() {
		return bavard;
	}

	public void setBavard(Bavard bavard) {
		this.bavard = bavard;
	}
	
}
