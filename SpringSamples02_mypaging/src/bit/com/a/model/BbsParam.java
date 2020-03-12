package bit.com.a.model;

import java.io.Serializable;

public class BbsParam implements Serializable{
	private String searchWord;
	private String choice;
	private int pageNumber;	// 현재 페이지
	private int start;
	private int end;
	private int recordCountPerPage = 10;	// 표현할 페이지의 글수
	
	public BbsParam() {
	
	}
	
	public BbsParam(String searchWord, String choice) {
		super();
		this.searchWord = searchWord;
		this.choice = choice;
	}

	public BbsParam(String searchWord, String choice, int pageNumber) {
		super();
		this.searchWord = searchWord;
		this.choice = choice;
		this.pageNumber = pageNumber;
	}

	
	
	public BbsParam(String searchWord, String choice, int pageNumber, int start, int end, int recordCountPerPage) {
		super();
		this.searchWord = searchWord;
		this.choice = choice;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
		this.recordCountPerPage = recordCountPerPage;
	}



	@Override
	public String toString() {
		return "BbsParam [searchWord=" + searchWord + ", choice=" + choice + ", pageNumber=" + pageNumber + ", start="
				+ start + ", end=" + end + ", recordCountPerPage=" + recordCountPerPage + "]";
	}

	
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	
}
