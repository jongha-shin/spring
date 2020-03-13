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
	public List<BbsDto> getBbsList(BbsParam param) {
		List<BbsDto> list = sqlSession.selectList(ns + "getBbsList", param);
		return list;
	}
	
	@Override
	public int getBbsCount(BbsParam param) {		
		return sqlSession.selectOne(ns + "getBbsCount", param);
	}

	@Override
	public boolean writeBbs(BbsDto bbs) {
		int n = sqlSession.insert(ns+"writeBbs", bbs);		
		return n>0?true:false;
	}
	
	@Override
	public BbsDto getBbs(int seq) {		
		return sqlSession.selectOne(ns+"getBbs", seq);
	}
	
	@Override
	public void updateBbs(BbsDto bbs) {
		sqlSession.update(ns+"updateBbs", bbs);
	}
	
	@Override
	public void deleteBbs(int seq) {
		sqlSession.update(ns+"deleteBbs", seq);
	}
	
	@Override
	public boolean replyBbsUpdate(BbsDto bbs) {		
		sqlSession.update(ns+"replyBbsUpdate", bbs);
		return true; 
	}

	@Override
	public boolean replyBbsInsert(BbsDto bbs) {
		sqlSession.insert(ns+"replyBbsInsert", bbs);
		return true;
	}
}






