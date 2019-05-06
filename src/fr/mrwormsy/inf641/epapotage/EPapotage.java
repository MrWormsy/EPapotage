package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.AdministratorGUI;
import fr.mrwormsy.inf641.epapotage.gui.BavardFrame;
import fr.mrwormsy.inf641.epapotage.gui.ConciergeFrame;

public class EPapotage {

	private static ArrayList<Bavard> bavards;
	//private static ArrayList<BavardFrame> bavardsFrames;
	private static Bavard currentBavard;
	
	private static Concierge concierge;
	private static ConciergeFrame conciergeFrame;
	
	
	
	private static ArrayList<ConciergeFrame> conciergeFrames;
	private static ArrayList<BavardFrame> bavardFrames;
	
	
	
	public static void main(String[] args) {
		
		//We create the administrator menu
		new AdministratorGUI();
		
		//Wet initialize an empty set of ConciergeFrame and BavardFrame
		setConciergeFrames(new ArrayList<ConciergeFrame>());
		setBavardFrames(new ArrayList<BavardFrame>());
		
		
		
		
		
		
		
		
		
		
		/*
		
		setBavards(new ArrayList<Bavard>());
		setBavardsFrames(new ArrayList<BavardFrame>());
		
		setCurrentBavard(null);
		
		setConcierge(new Concierge());	
		
		setConciergeFrame(new ConciergeFrame());
		
		conciergeFrame.setConcierge(getConcierge());
		
		*/
	}

	public static ConciergeFrame getConciergeFrame() {
		return conciergeFrame;
	}

	public static void setConciergeFrame(ConciergeFrame conciergeFrame) {
		EPapotage.conciergeFrame = conciergeFrame;
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

	public static ArrayList<ConciergeFrame> getConciergeFrames() {
		return conciergeFrames;
	}

	public static void setConciergeFrames(ArrayList<ConciergeFrame> conciergeFrames) {
		EPapotage.conciergeFrames = conciergeFrames;
	}

	public static ArrayList<BavardFrame> getBavardFrames() {
		return bavardFrames;
	}

	public static void setBavardFrames(ArrayList<BavardFrame> bavardFrames) {
		EPapotage.bavardFrames = bavardFrames;
	}
	
	public static boolean bavardExists(String bavard) {
		for (BavardFrame bf : getBavardFrames()) {
			if (bf.getBavard().getName().equalsIgnoreCase(bavard)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static BavardFrame getBavardFrameFromName(String bavard) {
		for (BavardFrame bf : getBavardFrames()) {
			if (bf.getBavard().getName().equalsIgnoreCase(bavard)) {
				return bf;
			}
		}
		return null;
	}
	
	
	public static boolean conciergeExists(String concierge) {
		for (ConciergeFrame cf : getConciergeFrames()) {
			if (cf.getConcierge().getName().equalsIgnoreCase(concierge)) {
				return true;
			}
		}
		return false;
	}
	
	public static ConciergeFrame getConciergeFrameFromName(String bavard) {
		for (ConciergeFrame cf : getConciergeFrames()) {
			if (cf.getConcierge().getName().equalsIgnoreCase(bavard)) {
				return cf;
			}
		}
		return null;
	}
}
