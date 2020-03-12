package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {
	
	@Autowired
	SqlSession sqlSession;			
	String ns = "Bbs.";
	
	@Override
	public List<BbsDto> getBbsList() {
		List<BbsDto> list = sqlSession.selectList(ns + "getBbsList");
		return list;
	}

	@Override
	public int writeBbs(BbsDto bbs) {
		int count = sqlSession.insert(ns+"writeBbs", bbs);
		return count;
	}

	@Override
	public BbsDto getBbs(int seq) {
		BbsDto bbs = sqlSession.selectOne(ns+"getBbs", seq);
		return bbs;
	}

	@Override
	public int deleteBbs(int seq) {
		int count = sqlSession.update(ns+"deleteBbs", seq);
		return count;
	}

	@Override
	public int updateBbs(BbsDto dto) {
		int count = sqlSession.update(ns+"updateBbs", dto);
		return count;
	}

	@Override
	public void readcount(int seq) {
		sqlSession.update(ns+"readcount", seq);
	}

	@Override
	public int getAllBbs(BbsParam bbsParam) {
		int len = sqlSession.selectOne(ns+"getAllBbs", bbsParam);
		return len;
	}

	@Override
	public List<BbsDto> bbsPagingList(BbsParam bbsParam) {
		List<BbsDto> list = sqlSession.selectList(ns+"bbsPagingList", bbsParam);
		return list;
	}

	
	@Override
	public int answerBbs(BbsDto bbs) {
		sqlSession.update(ns+"bbsStepUp", bbs.getSeq());
		int count = sqlSession.insert(ns+"bbsAnswer", bbs);
		return count;
	}
	
	
}






