import java.util.*;
public class Bavard {
	private int id_B;
	private ArrayList<Concierge> listConciergeConnected;
	private String name;
	
	public Bavard (String name) {
		this.id_B = 0;
		this.name = name;
		this.listConciergeConnected = null;
	}
	
	public String getName() {
		return this.name;
		}
	
	
	
	
	public void sendMessage(int idConciergeCible, Message message) {    //This method will send the message to the right Concierge
		//PAPOTAGE EVENT
	}
	
	
}
