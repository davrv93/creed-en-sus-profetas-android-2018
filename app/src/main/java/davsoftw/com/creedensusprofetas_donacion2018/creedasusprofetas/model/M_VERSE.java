package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model;

public class M_VERSE {
	private String id;
    private String idbook;
	private String book;
    private String chapter;
    private String verse;
	private String data;
    private String text;
    private String language;
    private String highlight;
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	public String getIdbook() {
		return idbook;
	}
	public void setIdbook(String idbook) {
		this.idbook = idbook;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getVerse() {
		return verse;
	}
	public void setVerse(String verse) {
		this.verse = verse;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}
}
