package bit.com.a.model;

import java.io.Serializable;

public class PdsDto implements Serializable {

	private int seq;
	private String id;
	private String title;
	private String content;
	
	private String filename;	// private String oldfilename;
	private String dbfilename;
	private int readcount;
	private int downcount;
	
	private String regdate;
	
	public PdsDto() {
	}

	public PdsDto(int seq, String id, String title, String content, String filename, String dbfilename, int readcount,
			int downcount, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.dbfilename = dbfilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}



	public PdsDto(String id, String title, String content, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDbfilename() {
		return dbfilename;
	}

	public void setDbfilename(String dbfilename) {
		this.dbfilename = dbfilename;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", dbfilename=" + dbfilename + ", readcount=" + readcount + ", downcount=" + downcount
				+ ", regdate=" + regdate + "]";
	}


}
