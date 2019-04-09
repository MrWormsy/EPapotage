import java.util.*;

public class Concierge {
	private int id_C;
	private ArrayList<Bavard> listBarvardConnected;
	
	public Concierge () {
		this.id_C = 0;
		this.listBarvardConnected = null;
	}
	
	
	public void addBavard(Bavard barvard) {
		listBarvardConnected.add(barvard);
	}
	
	public void bavardConnection(Bavard bavard) {
		System.out.println("Le bavard " + bavard.getName() + "est connecté");
	}
	
}
