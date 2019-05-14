package fr.mrwormsy.inf641.epapotage;
import java.util.ArrayList;

public class Concierge {
	
	//variables
	private String name;
	
	//A list of PapotageListener that will receive the message of one of the PapotageListener has sent
	private ArrayList<PapotageListener> listeners;
	
	//Constructors
	public Concierge (String name) {
		this.setListeners(new ArrayList<PapotageListener>());
		this.name = name;
	}
	
	//Add, remove, getters and setters...
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

	//Send a message to all the listeners
	public void sendMessageToAllListeners(String name, String text) {
		for (PapotageListener listener : listeners) {
			listener.sendMessage(name, text);
		}
		
	}
}
