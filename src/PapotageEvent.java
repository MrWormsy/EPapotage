
public class PapotageEvent {
	private Message message;
	
	public PapotageEvent (Message message) {
		this.message = message;
	}
	
	public Message dispatchMessage() {
		return message;
	}
	
	
}
