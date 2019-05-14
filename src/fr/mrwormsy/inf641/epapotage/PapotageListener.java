package fr.mrwormsy.inf641.epapotage;

public interface PapotageListener {	
	
	//The method of the Interface which will be overridden
	public void sendMessage (String name, String text);
	public String getName();
}
	