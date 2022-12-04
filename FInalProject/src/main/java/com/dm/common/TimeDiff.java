package com.dm.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDiff {
	public String getTimeDiff(Long edit_timeStamp_Time) {
		
		//String fm = "MM월dd일yyyy년 HH시mm분ss초";
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd");
		
		Date currentTime = new Date(); //현재시간
		long editTime = edit_timeStamp_Time - 3600 * 9 *  1000;//글쓴시간ms
		
		
		String e = sdformat.format(editTime); //포멧형식
		
		
		long timeDiffHour = (currentTime.getTime() - editTime) / (1000 * 60 * 60);
		if(timeDiffHour < 24){
			if(timeDiffHour < 1){
				return (currentTime.getTime() - editTime) / (1000 * 60) + "분 전";
			}
				return timeDiffHour+ "시간 전";
			
		}
		
		return e;
	}
}
