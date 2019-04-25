;
public class Main {

	public static void main(String[] args) {
		Bavard theo = new Bavard("Theo H");
		Bavard jesus = new Bavard("Antonin Rosa Martin");
		
		Concierge chichi = new Concierge();
		
		chichi.addBavard(theo);
		chichi.addBavard(jesus);
		
		TheListener listener1 = new TheListener();
		
		chichi.bavardConnecte();
		
		Message message = new Message(chichi.getId_C(),"Sujet","Content");
		theo.sendMessage(message);
	}

}
