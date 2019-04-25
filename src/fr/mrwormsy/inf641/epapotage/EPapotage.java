package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.Gui;

public class EPapotage {

	private static ArrayList<Bavard> bavards;
	private static Bavard currentBavard;
	
	private static Concierge concierge;
	private static TheListener theListener;
	
	public static void main(String[] args) {
		
		setBavards(new ArrayList<Bavard>());
		setCurrentBavard(null);
		
		setConcierge(new Concierge());
					
		setTheListener(new TheListener());				
		
		new Gui();
	}

	public static void addBavard(Bavard bavard) {
		bavards.add(bavard);
	}

	public static ArrayList<Bavard> getBavards() {
		return bavards;
	}

	public static void setBavards(ArrayList<Bavard> bavards) {
		EPapotage.bavards = bavards;
	}
	
	public static boolean bavardExists(String bavard) {
		for (Bavard b : bavards) {
			if (b.getName().equalsIgnoreCase(bavard)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Bavard getBavardFromName(String bavard) {
		for (Bavard b : bavards) {
			if (b.getName().equalsIgnoreCase(bavard)) {
				return b;
			}
		}
		return null;
	}

	public static Bavard getCurrentBavard() {
		return currentBavard;
	}

	public static void setCurrentBavard(Bavard currentBavard) {
		EPapotage.currentBavard = currentBavard;
	}

	public static Concierge getConcierge() {
		return concierge;
	}

	public static void setConcierge(Concierge concierge) {
		EPapotage.concierge = concierge;
	}

	public static TheListener getTheListener() {
		return theListener;
	}

	public static void setTheListener(TheListener theListener) {
		EPapotage.theListener = theListener;
	}

	
	
}
