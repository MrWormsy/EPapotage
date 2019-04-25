package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.Gui;

public class EPapotage {

	private static ArrayList<String> bavards;
	private static String currentBavard;
	
	public static void main(String[] args) {
		
		setBavards(new ArrayList<String>());
		setCurrentBavard(null);
		
		new Gui();
	}

	public static void addBavard(String bavard) {
		bavards.add(bavard);
	}

	public static ArrayList<String> getBavards() {
		return bavards;
	}

	public static void setBavards(ArrayList<String> bavards) {
		EPapotage.bavards = bavards;
	}
	
	public static boolean bavardExists(String bavard) {
		for (String string : bavards) {
			if (string.equalsIgnoreCase(bavard)) {
				return true;
			}
		}
		
		return false;
	}

	public static String getCurrentBavard() {
		return currentBavard;
	}

	public static void setCurrentBavard(String currentBavard) {
		EPapotage.currentBavard = currentBavard;
	}
	
}
