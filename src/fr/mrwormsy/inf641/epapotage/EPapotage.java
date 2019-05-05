package fr.mrwormsy.inf641.epapotage;

import java.util.ArrayList;

import fr.mrwormsy.inf641.epapotage.gui.BavardFrame;
import fr.mrwormsy.inf641.epapotage.gui.ConciergeFrame;

public class EPapotage {

	private static ArrayList<Bavard> bavards;
	private static ArrayList<BavardFrame> bavardsFrames;
	private static Bavard currentBavard;
	
	private static Concierge concierge;
	private static ConciergeFrame conciergeFrame;
	
	public static void main(String[] args) {
		
		setBavards(new ArrayList<Bavard>());
		setBavardsFrames(new ArrayList<BavardFrame>());
		
		setCurrentBavard(null);
		
		setConcierge(new Concierge());	
		
		setConciergeFrame(new ConciergeFrame());
		
		conciergeFrame.setConcierge(getConcierge());
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

	public static ArrayList<BavardFrame> getBavardsFrames() {
		return bavardsFrames;
	}

	public static void setBavardsFrames(ArrayList<BavardFrame> bavardsFrames) {
		EPapotage.bavardsFrames = bavardsFrames;
	}

	public static void addBavardFrame(BavardFrame bavardFrame) {
		bavardsFrames.add(bavardFrame);
		
	}

	public static BavardFrame getBavardFrameFromName(String bavardFrameName) {
		for (BavardFrame b : bavardsFrames) {
			if (b.getBavard().getName().equalsIgnoreCase(bavardFrameName)) {
				return b;
			}
		}
		return null;
		
	}	
}
