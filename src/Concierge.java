import java.util.*;

public class Concierge {
	private int id_C;
	private ArrayList<Bavard> listBarvardConnected;
	
	public Concierge () {
		this.id_C = 0;
		this.listBarvardConnected = new ArrayList<Bavard>();
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
	

}
