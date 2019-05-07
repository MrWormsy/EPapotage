package fr.mrwormsy.inf641.epapotage;

public class Bavard implements PapotageListener {
	private int id_B;
	private String name;
	
	public Bavard (String name) {
		this.id_B = 0;
		this.name = name;
	}
	
	public int getId_B() {
		return id_B;
	}

	public void setId_B(int id_B) {
		this.id_B = id_B;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
		}
	
	@Override
	public void sendMessage(String name, String text) {
		System.out.println(this.name + "has received: " + name + " " + text);
	}
}
