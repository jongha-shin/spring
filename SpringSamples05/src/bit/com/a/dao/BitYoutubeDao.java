package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.YoutubeSave;

public interface BitYoutubeDao {
	
	public boolean writeYoutube(YoutubeSave ys);
	
	public YoutubeSave getYoutube(YoutubeSave ys);
	
	public List<YoutubeSave> getMytube(YoutubeSave ys);

	public boolean deletetube(int seq);
	
}
