package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BbsDao {
	
	public List<BbsDto> getBbsList();
	public List<BbsDto> getBbsList(BbsParam param);
	
	public boolean writeBbs(BbsDto bbs);
	
	public BbsDto getBbs(int seq);
	
	boolean replyBbsUpdate(BbsDto bbs) throws Exception;
	boolean replyBbsInsert(BbsDto bbs) throws Exception;

}
