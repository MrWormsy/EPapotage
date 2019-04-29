package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.EPapotage;

public class LogInToBavardGUI {
	
	public LogInToBavardGUI() {

		JFrame f = new JFrame("Log in to Bavard");

		JButton b = new JButton("Log In");    
		b.setBounds(40, 80, 150, 30);    

		JLabel label = new JLabel();		
		label.setText("Enter Bavard Name :");
		
		label.setBounds(40, 10, 150, 30);
		
		JTextField textfield= new JTextField();
		textfield.setBounds(40, 40, 150, 30);
		
		//add to frame
		f.add(textfield);
		f.add(label);
		f.add(b);    
		f.setSize(250,180);    
		f.setLayout(null);    
		f.setVisible(true);    
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// We get the name and if the Bavard already exists we log to him, otherwise we deny the log in
				if (EPapotage.bavardExists(textfield.getText())) {
					
					EPapotage.setCurrentBavard(EPapotage.getBavardFromName(textfield.getText()));
										
					EPapotage.getBavardFrameFromName(textfield.getText()).setVisible(true);;
								
					f.dispose();
					
				} else {
					JOptionPane.showMessageDialog(f, "This bavard does not exist !");
				}
				
			}          
		});
		
		
		f.getRootPane().setDefaultButton(b);
	}
		
	
}
