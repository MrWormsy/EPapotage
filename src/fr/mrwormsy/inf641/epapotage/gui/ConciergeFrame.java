package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.mrwormsy.inf641.epapotage.Concierge;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class ConciergeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	
	private JMenu conciergeMenu;
	private JMenu bavardMenu;
	
	private JMenuItem createBavardItem;
	private JMenuItem deleteBavardItem;
	private JMenuItem listBavardItem;
	private JMenuItem loginToBavard;
		
	private JTextArea chatDisplay;
	
	private JScrollPane displayScrollPanel;

	private Concierge concierge;
	
	public ConciergeFrame() {

		this.setConcierge(EPapotage.getConcierge());
		
		this.setTitle("TEST");
		this.setSize(800, 600);
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
	    
	    this.chatDisplay = new JTextArea("--- Chat initialized ---");
	    this.chatDisplay.setEditable(false);
	    
	    displayScrollPanel = new JScrollPane (this.chatDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.displayScrollPanel.setPreferredSize(new Dimension(594, 500));
	    	    
	    this.add(this.displayScrollPanel, BorderLayout.NORTH);
	    
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
	    
	    this.setVisible(true);;
		
	}
	
	public static String convertToMultiline(String orig) {
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}

	public void writeLogs(String log) {
		this.chatDisplay.setText(this.chatDisplay.getText() + "\n" + "(" + Date.from(Instant.EPOCH) + ") " + log);
	}
	
	public Concierge getConcierge() {
		return concierge;
	}

	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}
}
