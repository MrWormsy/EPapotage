package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

public class Bavard implements PapotageListener {
	private int id_B;
	private ArrayList<Concierge> listConciergeConnected;
	private String name;
	
	public Bavard (String name) {
		this.id_B = 0;
		this.name = name;
		this.listConciergeConnected = null;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
		}
	
	@Override
	public void sendMessage(String name, String text) {
		System.out.println(this.name + "has received: " + name + " " + text);
	}		
}
