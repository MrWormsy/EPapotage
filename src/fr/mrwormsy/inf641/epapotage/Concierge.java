package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

public class Concierge {
	private int id_C;
	private String name;
	private ArrayList<PapotageListener> listeners;
	
	public Concierge (String name) {
		this.id_C = 0;
		this.setListeners(new ArrayList<PapotageListener>());
		this.name = name;
	}

	public void setId_C(int id_C) {
		this.id_C = id_C;
	}

	public int getId_C() {
		return id_C;
	}
	
	public void addListener(PapotageListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(PapotageListener listener) {
		listeners.remove(listener);
	}
	
	public void listenersConnected() {
		for (PapotageListener l : listeners) {
			System.out.println(getName() + " said that the listener " + l.getName() + " is connected");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PapotageListener> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<PapotageListener> listeners) {
		this.listeners = listeners;
	}

	public void sendMessageToAllListeners(String name, String text) {
		for (PapotageListener listener : listeners) {
			listener.sendMessage(name, text);
		}
		
	}
}
