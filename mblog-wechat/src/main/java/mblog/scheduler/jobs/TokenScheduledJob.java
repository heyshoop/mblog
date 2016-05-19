/**
 * File Name：ScheduledJob.java
 *
 * Copyright Defonds Corporation 2015 
 * All Rights Reserved
 *
 */
package mblog.scheduler.jobs;

import mblog.scheduler.entity.Token;
import mblog.wechat.service.MsgPushService;
import mblog.wechat.utill.Constants;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;

/**
	 * @Author 阁楼麻雀
	 * @Date 2016-5-17 17:51
	 * @Desc 定时任务类，一个小时获取一次accesstoken
	 */

public class TokenScheduledJob extends QuartzJobBean {
	
	private static Token token = Token.getInstance();
	private Constants constants;
	private static Logger logger = LoggerFactory.getLogger(TokenScheduledJob.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		MsgPushService msgPushService = new MsgPushService();
		String accesstoken = "";
		try {
			accesstoken = msgPushService.getAccessToken(this.constants.APPID,this.constants.SECRET);
		} catch (IOException e) {
			logger.error("定时任务类，一个小时获取一次accesstoken出错,请检查参数",e);
		}
		token.setToken(accesstoken);
	}
}
