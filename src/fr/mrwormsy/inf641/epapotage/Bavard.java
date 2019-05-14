package fr.mrwormsy.inf641.epapotage;

public class Bavard implements PapotageListener {
	
	//Variables
	private String name;
	
	//Constructor
	public Bavard (String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//Overridden methods inherited from the PapotageListener Interface
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void sendMessage(String name, String text) {
		System.out.println(this.name + "has received: " + name + " " + text);
	}
}
