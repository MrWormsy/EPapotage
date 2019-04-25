package fr.mrwormsy.inf641.epapotage;

public class TheListener implements PapotageListener {

	@Override
	public void sendMessage(PapotageEvent message) {
			
		Message messageToGet = new Message(0, "null", "null");
		messageToGet = message.dispatchMessage();
		System.out.println("Date du message : " + messageToGet.getDate());
		System.out.println("Sujet du message : " + messageToGet.getSujet());
		System.out.println("Contenu du message : " + messageToGet.getContent());
	}
}