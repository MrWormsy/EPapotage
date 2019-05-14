package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.AdministratorGUI;
import fr.mrwormsy.inf641.epapotage.gui.BavardFrame;
import fr.mrwormsy.inf641.epapotage.gui.ConciergeFrame;

public class EPapotage {

	//The admin GUI which is the first menu we encounter
	private static AdministratorGUI administratorGUI;
	
	//A list of BavardFrame
	private static ArrayList<BavardFrame> bavardFrames;

	//A list of ConciergeFrame
	private static ArrayList<ConciergeFrame> conciergeFrames;

	//The main method
	public static void main(String[] args) {

		//We initialize an empty set of ConciergeFrame and BavardFrame
		setConciergeFrames(new ArrayList<ConciergeFrame>());
		setBavardFrames(new ArrayList<BavardFrame>());

		//We create the administrator menu
		setAdministratorGUI(new AdministratorGUI());
	}

	//Getter, Setter and several method to get objects with their (unique) name
	
	public static AdministratorGUI getAdministratorGUI() {
		return administratorGUI;
	}

	public static void setAdministratorGUI(AdministratorGUI administratorGUI) {
		EPapotage.administratorGUI = administratorGUI;
	}

	public static ArrayList<BavardFrame> getBavardFrames() {
		return bavardFrames;
	}

	public static void setBavardFrames(ArrayList<BavardFrame> bavardFrames) {
		EPapotage.bavardFrames = bavardFrames;
	}

	public static ArrayList<ConciergeFrame> getConciergeFrames() {
		return conciergeFrames;
	}

	public static void setConciergeFrames(ArrayList<ConciergeFrame> conciergeFrames) {
		EPapotage.conciergeFrames = conciergeFrames;
	}

	public static boolean bavardExists(String bavard) {
		for (BavardFrame bf : getBavardFrames()) {
			if (bf.getBavard().getName().equalsIgnoreCase(bavard)) {
				return true;
			}
		}
		return false;
	}

	public static boolean conciergeExists(String concierge) {
		for (ConciergeFrame cf : getConciergeFrames()) {
			if (cf.getConcierge().getName().equalsIgnoreCase(concierge)) {
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

	public static ConciergeFrame getConciergeFrameFromName(String bavard) {
		for (ConciergeFrame cf : getConciergeFrames()) {
			if (cf.getConcierge().getName().equalsIgnoreCase(bavard)) {
				return cf;
			}
		}
		return null;
	}
}
