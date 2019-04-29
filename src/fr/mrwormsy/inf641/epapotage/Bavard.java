package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.BavardFrame;
public class Bavard {
	private int id_B;
	private ArrayList<Concierge> listConciergeConnected;
	private String name;
	private BavardFrame frame;
	
	public Bavard (String name) {
		this.id_B = 0;
		this.name = name;
		this.listConciergeConnected = null;
		this.frame = new BavardFrame(name);
	}
	
	public int getId_B() {
		return id_B;
	}

	public void setId_B(int id_B) {
		this.id_B = id_B;
	}

	public ArrayList<Concierge> getListConciergeConnected() {
		return listConciergeConnected;
	}

	public void setListConciergeConnected(ArrayList<Concierge> listConciergeConnected) {
		this.listConciergeConnected = listConciergeConnected;
	}

	public BavardFrame getFrame() {
		return frame;
	}

	public void setFrame(BavardFrame frame) {
		this.frame = frame;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
		}
	
	public void sendMessage(Message message) {    //This method will send the message to the right Concierge
		PapotageEvent toSend = new PapotageEvent(message);
	}
	
	public void lookForMessage() {
		
	}

	public void sendMessage(String name, String text) {
		this.frame.sendMessage(name, text);
		
	}
	
}
