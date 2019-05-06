package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	private String password;
	
	private JScrollPane displayScrollPanel;
	
	
	public BavardFrame(Bavard bavard, String name) {

		this.setTitle(name);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent windowEvent) {
				EPapotage.getConciergeFrame().writeLogs(name + " has logged out");
		    }
		});

	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	   	    
	    //Content of the frame
	    
	    this.chatDisplay = new JTextArea();
	    this.chatDisplay.setEditable(false);
	    
	    displayScrollPanel = new JScrollPane (this.chatDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.displayScrollPanel.setPreferredSize(new Dimension(594, 321));
        //this.chatDisplay.setMaximumSize(new Dimension(594, 300));
	    
	    this.chatWritter = new JTextField("Message");
	    this.chatWritter.setBounds(25, 370, 475, 20);
	    	 
	    this.sendMessageButton = new JButton("Send");
	    this.sendMessageButton.setBounds(525, 370, 50, 20);
	    
	    this.writeAndSendPanel = new JPanel();
	    this.writeAndSendPanel.setPreferredSize(new Dimension(10, 10));
	    this.sendPanel = new JPanel();
	    
	    this.bavard = bavard;
	   	            
        writeAndSendPanel.setLayout(new BoxLayout(writeAndSendPanel, BoxLayout.Y_AXIS));
        writeAndSendPanel.add(this.displayScrollPanel);
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
					for (BavardFrame bf : EPapotage.getBavardFrames()) {
						bf.sendMessage(name, chatWritter.getText());
					}
					
					//Write the logs
					EPapotage.getConciergeFrame().writeLogs(name + ": " + chatWritter.getText());
					
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

	public static String convertToMultiline(String orig) {
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}
	
	public void sendMessage(String name, String text) {
		//this.chatDisplay.setText(convertToMultiline(this.chatDisplay.getText().replaceAll("(<html>)", "").replaceAll("(</html>)", "") + "\n" + name + " wrote : " + text));		
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
