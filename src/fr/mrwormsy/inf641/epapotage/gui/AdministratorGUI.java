package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class AdministratorGUI extends JFrame {
	
	
	
	private static final long serialVersionUID = 1L;
	
	private JMenu bavardDelete;
	private JMenu bavardlist;
	private JMenu conciergeDelete;
	private JMenu conciergelist;

	public AdministratorGUI() {
		
		new JFrame("Administrator GUI");
		this.setTitle("Administrator GUI");
		this.setBounds(100, 100, 550, 440);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JLabel welcomeLabel = new JLabel("Welcome to EPapotage");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel logoPanel = new JPanel();

		JLabel imgLabel = new JLabel(new ImageIcon("logo.png"));
		logoPanel.add(imgLabel);
		
		
		JPanel polytechPanel = new JPanel();
		JLabel imgLabel2 = new JLabel(new ImageIcon("polytech.png"));
		polytechPanel.add(imgLabel2);
		
		JLabel creditsPanel = new JLabel("By HENAFF Th\u00E9o & ROSA--MARTIN Antonin");
		creditsPanel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(logoPanel, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(creditsPanel, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addComponent(polytechPanel, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(logoPanel, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(creditsPanel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(polytechPanel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		this.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu conciergesMenu = new JMenu("Concierges");
		menuBar.add(conciergesMenu);
		
		JMenuItem conciergeAdd = new JMenuItem("Add");
		conciergesMenu.add(conciergeAdd);
		
		conciergeDelete = new JMenu("Delete");
		conciergesMenu.add(conciergeDelete);
		
		conciergelist = new JMenu("List");
		conciergesMenu.add(conciergelist);
		
		JMenuItem conciergeLogin = new JMenuItem("Log In");
		conciergesMenu.add(conciergeLogin);
		
		JMenu bavardMenu = new JMenu("Bavards");
		menuBar.add(bavardMenu);
		
		JMenuItem bavardAdd = new JMenuItem("Add");
		bavardMenu.add(bavardAdd);
		
		bavardDelete = new JMenu("Delete");
		bavardMenu.add(bavardDelete);
		
		bavardlist = new JMenu("List");
		bavardMenu.add(bavardlist);
		
		JMenuItem bavardLogin = new JMenuItem("Log In");
		bavardMenu.add(bavardLogin);
		
		//When the Administrator wants to add a concierge
		conciergeAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//Open a CreateConciergeGUI...
				new CreateConciergeGUI();
				
			}
		});
		
		//When the administrator wants to log to an existing concierge
		conciergeLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//Open a LogInToConciergeGUI
				new LogInToConciergeGUI();
				
			}
		});
		
		//When the Administrator wants to add a bavard
		bavardAdd.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {

				//Open a CreateConciergeGUI...
				new CreateBavardGUI();
						
			}
		});
				
		//When the administrator wants to log to an existing bavard
		bavardLogin.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {

				//Open a LogInToConciergeGUI
				new LogInToBavardGUI();
						
			}
		});
		
		
		this.setVisible(true);
	}	
	
	public JMenuItem getBavardDelete() {
		return bavardDelete;
	}

	public void setBavardDelete(JMenu bavardDelete) {
		this.bavardDelete = bavardDelete;
	}

	public JMenu getConciergeDelete() {
		return conciergeDelete;
	}

	public void setConciergeDelete(JMenu conciergeDelete) {
		this.conciergeDelete = conciergeDelete;
	}
	
	public JMenu getBavardlist() {
		return bavardlist;
	}

	public void setBavardlist(JMenu bavardlist) {
		this.bavardlist = bavardlist;
	}

	public JMenu getConciergelist() {
		return conciergelist;
	}

	public void setConciergelist(JMenu conciergelist) {
		this.conciergelist = conciergelist;
	}
}
