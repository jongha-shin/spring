package bit.com.a.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitPollDao;
import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;
import bit.com.a.service.BitPollService;

@Service
public class BitPollServiceImpl implements BitPollService {
	
	@Autowired
	BitPollDao pollDao;

	@Override
	public List<PollDto> getPollAllList(String id) {
		
		// 모든 투표 목록
		List<PollDto> list = pollDao.getPollAllList();
		
		// 편집을 통해 투표가 가능한지 체크, 넘겨줄 목록 생성
		List<PollDto> plist = new ArrayList<PollDto>();
		
		for (PollDto poll : list) {
			int pollid = poll.getPollid();		// 투표번호
			
			int count = pollDao.isVote(new Voter(pollid, -1, id));
			if(count ==1) {		// 투표함
				poll.setVote(true);
			}else {		// 투표안함
				poll.setVote(false);
			}
			plist.add(poll);
			
		}
		return plist;
	}

	@Override
	public void makePoll(PollBean pbean) {
		System.out.println("여기야이ㅏ러ㅣ아러ㅣ아러"+pbean.toString());
		// 투표 질문
		PollDto poll = new PollDto(pbean.getId(), pbean.getQuestion(), pbean.getSdate(),
								   pbean.getEdate(), pbean.getItemcount(), 0);
		pollDao.makePoll(poll);
		
		// 보기들
		String answer[] = pbean.getPollNum();
		for(int i = 0; i < pbean.getItemcount(); i++) {
			PollSubDto pollsub = new PollSubDto();
			pollsub.setAnswer(answer[i]);
			
			pollDao.makePollSub(pollsub);
		}
	}
	
	
}
