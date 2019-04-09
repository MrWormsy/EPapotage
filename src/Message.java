
public class Message {
	private int id_M;
	private int idConciergeCible;
	private String sujet;
	private String content;
	private Long date;
	
	public Message (int idConciergeCible, String sujet, String content, Long date) {
		this.id_M = 0;
		this.idConciergeCible = idConciergeCible;
		this.sujet = sujet;
		this.content = content;
		this.date = date;
	}
	
	
}
