package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitYoutubeDao;
import bit.com.a.model.YoutubeSave;
import bit.com.a.service.BitYoutubeService;

@Service
public class BitYoutubeServiceImpl implements BitYoutubeService {

	@Autowired
	BitYoutubeDao youtubeDao;
	
	public boolean writeYoutube(YoutubeSave ys) {
		
		return youtubeDao.writeYoutube(ys);
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave ys) {
		return youtubeDao.getYoutube(ys);
	}

	@Override
	public List<YoutubeSave> getMytube(YoutubeSave ys) {
		return youtubeDao.getMytube(ys);
	}

	@Override
	public boolean deletetube(int seq) {
		return youtubeDao.deletetube(seq);
	}
	
}
