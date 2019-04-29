package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.Gui;

public class Concierge {
	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	private int id_C;
	private ArrayList<Bavard> listBarvardConnected;
	private ArrayList<String> messages;
	private Gui gui;
	
	public Concierge () {
		this.id_C = 0;
		this.listBarvardConnected = new ArrayList<Bavard>();
		this.messages = new ArrayList<String>();
	}
	
	public ArrayList<Bavard> getListBarvardConnected() {
		return listBarvardConnected;
	}

	public void setListBarvardConnected(ArrayList<Bavard> listBarvardConnected) {
		this.listBarvardConnected = listBarvardConnected;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	public void setId_C(int id_C) {
		this.id_C = id_C;
	}

	public int getId_C() {
		return id_C;
	}
	public void addBavard(Bavard barvard) {
		listBarvardConnected.add(barvard);
	}
	
	public void bavardConnecte() {
		for (Bavard bv : listBarvardConnected) {
			System.out.println("Le bavard " + bv.getName() + " est connecté");
		}
	}

	public void dispatchMessageBetweenBavards(String name, String text) {
		for(Bavard bv : this.listBarvardConnected) {
			bv.sendMessage(name, text);
		}
		
	}
	

}
