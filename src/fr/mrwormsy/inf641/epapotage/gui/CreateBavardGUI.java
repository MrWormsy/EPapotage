package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mrwormsy.inf641.epapotage.Bavard;
import fr.mrwormsy.inf641.epapotage.EPapotage;

public class CreateBavardGUI {

	public CreateBavardGUI() {

		JFrame f = new JFrame("Create Bavard");

		JButton b = new JButton("Create Bavard");    
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
				
				if (!EPapotage.bavardExists(textfield.getText())) {
					// We create a Bavard and we add it to the list of Bavard in EPapotage
					
					Bavard barvard = new Bavard(textfield.getText());
					
					EPapotage.addBavard(barvard);
					EPapotage.addBavardFrame(new BavardFrame(textfield.getText()));
					
					EPapotage.getConcierge().addBavard(barvard);
					
					EPapotage.getConciergeFrame().writeLogs(textfield.getText() + " has been registered as a new Bavard");
										
				} else {
					JOptionPane.showMessageDialog(f, "This bavard already exists !");
				}
				
				
				
				f.dispose();
			}          
		});
		
		f.getRootPane().setDefaultButton(b);
		
	}
	
}
