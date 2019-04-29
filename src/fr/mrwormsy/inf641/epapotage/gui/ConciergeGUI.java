package fr.mrwormsy.inf641.epapotage.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;  


public class ConciergeGUI extends JFrame{
	

	
	public ConciergeGUI() {
		
		initUI();
	}
	
	private void initUI() {
        

		
        setTitle("Simple example");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        

        
    	JTextField textfield = new JTextField(16);
    	textfield.setText(" ");
        
        JButton button = new JButton ("test");
        button.addActionListener(new ActionListener() {
        	
        	@Override
        	 public void actionPerformed(ActionEvent e){  
                 new ConciergeGUI().setVisible(true); 
        	 }          
        });
        
        JTextArea textarea = new JTextArea("Welcome to javatpoint");
        textarea.setEditable(false);

        JPanel panel = new JPanel();
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(textarea);
        mainPanel.add(panel);
        
		GroupLayout groupLayout = new GroupLayout(panel); 
		groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
		panel.setLayout(groupLayout);
        

        groupLayout.setHorizontalGroup(  
                    groupLayout.createSequentialGroup()  
                                .addComponent(textfield)  
                                .addComponent(button));  
        groupLayout.setVerticalGroup(  
                     groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)  
                                .addComponent(textfield)  
                                .addComponent(button));
		
        this.setContentPane(mainPanel);

    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ConciergeGUI ex = new ConciergeGUI();
            ex.setVisible(true);
        });
    }
}
