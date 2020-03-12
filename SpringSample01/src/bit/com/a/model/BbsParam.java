package bit.com.a.model;

import java.io.Serializable;

public class BbsParam implements Serializable {

	private String searchWord;
	private String choice;
	
	public BbsParam() {
	}

	public BbsParam(String searchWord, String choice) {
		super();
		this.searchWord = searchWord;
		this.choice = choice;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "BbsParam [searchWord=" + searchWord + ", choice=" + choice + "]";
	}	
	
	
}
