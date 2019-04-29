package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	
	private JMenu conciergeMenu;
	private JMenu bavardMenu;
	
	private JMenuItem createBavardItem;
	private JMenuItem deleteBavardItem;
	private JMenuItem listBavardItem;
	private JMenuItem loginToBavard;
		
	private JLabel chatDisplay;
	
	public Gui() {

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
	    
	    this.chatDisplay = new JLabel();
	    this.chatDisplay.setText("----- Chat initialized -----");
	    this.chatDisplay.setBounds(25, 0, 550, 350);
	    	    
	    this.add(this.chatDisplay, BorderLayout.NORTH);
	    
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
}
