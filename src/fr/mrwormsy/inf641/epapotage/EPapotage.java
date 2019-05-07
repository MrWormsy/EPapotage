package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.AdministratorGUI;
import fr.mrwormsy.inf641.epapotage.gui.BavardFrame;
import fr.mrwormsy.inf641.epapotage.gui.ConciergeFrame;

public class EPapotage {

	private static AdministratorGUI administratorGUI;
	private static ArrayList<BavardFrame> bavardFrames;

	private static ArrayList<Bavard> bavards;
	private static Concierge concierge;

	private static ConciergeFrame conciergeFrame;

	private static ArrayList<ConciergeFrame> conciergeFrames;
	//private static ArrayList<BavardFrame> bavardsFrames;
	private static Bavard currentBavard;

	public static void main(String[] args) {

		//Wet initialize an empty set of ConciergeFrame and BavardFrame
		setConciergeFrames(new ArrayList<ConciergeFrame>());
		setBavardFrames(new ArrayList<BavardFrame>());

		//We create the administrator menu
		setAdministratorGUI(new AdministratorGUI());
	}

	public static void addBavard(Bavard bavard) {
		bavards.add(bavard);
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

	public static AdministratorGUI getAdministratorGUI() {
		return administratorGUI;
	}

	public static BavardFrame getBavardFrameFromName(String bavard) {
		for (BavardFrame bf : getBavardFrames()) {
			if (bf.getBavard().getName().equalsIgnoreCase(bavard)) {
				return bf;
			}
		}
		return null;
	}

	public static ArrayList<BavardFrame> getBavardFrames() {
		return bavardFrames;
	}

	public static ArrayList<Bavard> getBavards() {
		return bavards;
	}

	public static Concierge getConcierge() {
		return concierge;
	}

	public static ConciergeFrame getConciergeFrame() {
		return conciergeFrame;
	}

	public static ConciergeFrame getConciergeFrameFromName(String bavard) {
		for (ConciergeFrame cf : getConciergeFrames()) {
			if (cf.getConcierge().getName().equalsIgnoreCase(bavard)) {
				return cf;
			}
		}
		return null;
	}

	public static ArrayList<ConciergeFrame> getConciergeFrames() {
		return conciergeFrames;
	}

	public static Bavard getCurrentBavard() {
		return currentBavard;
	}

	public static void setAdministratorGUI(AdministratorGUI administratorGUI) {
		EPapotage.administratorGUI = administratorGUI;
	}

	public static void setBavardFrames(ArrayList<BavardFrame> bavardFrames) {
		EPapotage.bavardFrames = bavardFrames;
	}

	public static void setBavards(ArrayList<Bavard> bavards) {
		EPapotage.bavards = bavards;
	}


	public static void setConcierge(Concierge concierge) {
		EPapotage.concierge = concierge;
	}

	public static void setConciergeFrame(ConciergeFrame conciergeFrame) {
		EPapotage.conciergeFrame = conciergeFrame;
	}

	public static void setConciergeFrames(ArrayList<ConciergeFrame> conciergeFrames) {
		EPapotage.conciergeFrames = conciergeFrames;
	}

	public static void setCurrentBavard(Bavard currentBavard) {
		EPapotage.currentBavard = currentBavard;
	}
}
