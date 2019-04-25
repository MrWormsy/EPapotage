package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.EPapotage;
import fr.mrwormsy.inf641.epapotage.Message;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	
	private JMenu conciergeMenu;
	private JMenu bavardMenu;
	
	private JMenuItem createBavardItem;
	private JMenuItem deleteBavardItem;
	private JMenuItem listBavardItem;
	private JMenuItem loginToBavard;
	
	private JPanel writeAndSendPanel;
	
	private JLabel chatDisplay;
	private JTextField chatWritter;
	
	private JButton sendMessageButton;
	
	public Gui() {

		this.setTitle("TEST");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.menuBar = new JMenuBar();
		
		this.conciergeMenu = new JMenu("Concierges");
		this.bavardMenu = new JMenu("Bavards");
				
	    this.createBavardItem = new JMenuItem("Create Bavard");
	    this.deleteBavardItem = new JMenuItem("Delete Bavard");
	    this.listBavardItem = new JMenuItem("Liste Bavard");
	    this.loginToBavard = new JMenuItem("Log In to Bavard");
	      
	    this.conciergeMenu.add(this.createBavardItem);
	    this.conciergeMenu.add(this.deleteBavardItem);
	    this.conciergeMenu.add(this.listBavardItem);
	    this.bavardMenu.add(this.loginToBavard);
	    
	    this.menuBar.add(this.conciergeMenu);
	    this.menuBar.add(this.bavardMenu);
	    
	    this.setJMenuBar(this.menuBar);
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
	    
	    this.createBavardItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateBavardGUI();				
			}
		});
	    
	    this.loginToBavard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInToBavardGUI();
				
			}
		});
	    
	    this.sendMessageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (chatWritter.getText().length() >= 2 && EPapotage.getCurrentBavard() != null) {
					sendMessage();
				}				
			}
		});
	    
	    
	    this.setVisible(true);;
		
	}
	
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}
	
	public void sendMessage() {
		this.chatDisplay.setText(convertToMultiline(this.chatDisplay.getText().replaceAll("(<html>)", "").replaceAll("(</html>)", "") + "\n" + EPapotage.getCurrentBavard().getName() + " wrote : " + this.chatWritter.getText()));

		EPapotage.getCurrentBavard().sendMessage(new Message(EPapotage.getConcierge().getId_C(),"Sujet",this.chatWritter.getText()));
		
		this.chatWritter.setText("");
	}
}
