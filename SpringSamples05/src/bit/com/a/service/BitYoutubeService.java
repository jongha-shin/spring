package bit.com.a.service;

import java.util.List;

import bit.com.a.model.YoutubeSave;

public interface BitYoutubeService {
	
	public boolean writeYoutube(YoutubeSave ys);
	public YoutubeSave getYoutube(YoutubeSave ys);
	public List<YoutubeSave> getMytube(YoutubeSave ys);
	public boolean deletetube(int seq);
}
