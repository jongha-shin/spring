package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitYoutubeDao;
import bit.com.a.model.YoutubeSave;

@Repository
public class BitYoutubeDaoImpl implements BitYoutubeDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	String ns = "Youtube.";

	@Override
	public boolean writeYoutube(YoutubeSave ys) {
		
		int count = sqlSession.insert(ns+"writeYoutube", ys);
		
		return count>0?true:false;
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave ys) {
		return sqlSession.selectOne(ns+"getYoutube", ys);
	}

	@Override
	public List<YoutubeSave> getMytube(YoutubeSave ys) {
		return sqlSession.selectList(ns+"getMytube", ys);
	}

	@Override
	public boolean deletetube(int seq) {
		
		int count = sqlSession.delete(ns+"deletetube", seq); 
		return count>0?true:false;
	}
	
	
	
}
