package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BbsDao {
	
	public List<BbsDto> getBbsList();

	public int writeBbs(BbsDto bbs);

	public BbsDto getBbs(int seq);

	public int deleteBbs(int seq);

	public int updateBbs(BbsDto dto);

	public void readcount(int seq);

	public int getAllBbs(BbsParam bbsParam);

	public List<BbsDto> bbsPagingList(BbsParam bbsParam);

	public int answerBbs(BbsDto bbs);
	
}
