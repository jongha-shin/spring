package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitPollDao;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;

@Repository
public class BItPollDaoImpl implements BitPollDao {

	@Autowired
	SqlSessionTemplate sqlSession;	// dependency injection
	
	String ns = "Poll.";

	@Override
	public List<PollDto> getPollAllList() {
		return sqlSession.selectList(ns+"getPollAllList");
	}

	@Override
	public int isVote(Voter voter) {
		return sqlSession.selectOne(ns+"isVote", voter);
	}

	@Override
	public void makePoll(PollDto poll) {	// 질문
		sqlSession.insert(ns+"makePoll", poll);
	}

	@Override
	public void makePollSub(PollSubDto pollsub) {	// 보기
		sqlSession.insert(ns+"makePollSub", pollsub);
	}

	@Override
	public PollDto getPoll(PollDto poll) {
		return sqlSession.selectOne(ns+"getPoll", poll);
		
	}

	@Override
	public List<PollSubDto> getPollSubList(PollDto poll) {
		return sqlSession.selectList(ns+"getPollSubList", poll);
	}

	@Override
	public void pollingVoter(Voter voter) {
		sqlSession.insert(ns+"pollingVote", voter);
		
	}

	@Override
	public void pollingPoll(Voter voter) {
		sqlSession.update(ns+"pollingPoll", voter);
		
	}

	@Override
	public void pollingSub(Voter voter) {
		sqlSession.update(ns+"pollingSub", voter);
		
	}
	
	
	
}
