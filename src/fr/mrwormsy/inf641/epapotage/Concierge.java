package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

public class Concierge {
	private int id_C;
	private String name;
	private ArrayList<Bavard> listBarvardConnected;
	private ArrayList<String> messages;
	
	public Concierge (String name) {
		this.id_C = 0;
		this.listBarvardConnected = new ArrayList<Bavard>();
		this.messages = new ArrayList<String>();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
