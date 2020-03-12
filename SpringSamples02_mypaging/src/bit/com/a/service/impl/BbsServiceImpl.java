package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao bbsDao; // = new BbsDaoImpl();

	@Override
	public List<BbsDto> getBbsList() {		
		return bbsDao.getBbsList();
	}

	@Override
	public int writeBbs(BbsDto bbs) {
		return bbsDao.writeBbs(bbs);
	}

	@Override
	public BbsDto getBbs(int seq) {
		return bbsDao.getBbs(seq);
	}

	@Override
	public int deleteBbs(int seq) {
		return bbsDao.deleteBbs(seq);
	}

	@Override
	public int updateBbs(BbsDto dto) {
		return bbsDao.updateBbs(dto);
	}

	@Override
	public void readcount(int seq) {
		bbsDao.readcount(seq);
	}

	@Override
	public int getAllBbs(BbsParam bbsParam) {
		return bbsDao.getAllBbs(bbsParam);
	}

	@Override
	public List<BbsDto> bbsPagingList(BbsParam bbsParam) {
		return bbsDao.bbsPagingList(bbsParam);
	}

	@Override
	public int answerBbs(BbsDto bbs) {
		
		return bbsDao.answerBbs(bbs);
	}

	

	
	
}








