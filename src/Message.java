
public class Message {
	private int id_M;
	private int idConciergeCible;
	private String sujet;
	private String content;
	private Long date;
	
	public Message (int idConciergeCible, String sujet, String content) {
		this.id_M = 0;
		this.idConciergeCible = idConciergeCible;
		this.sujet = sujet;
		this.content = content;
	}

	public int getIdConciergeCible() {
		return idConciergeCible;
	}
	public String getSujet() {
		return sujet;
	}
	public String getContent() {
		return content;
	}
	public Long getDate () {
		return date;
	}

}
