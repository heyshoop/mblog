package mblog.wechat.service;

import mblog.wechat.entity.message.Article;
import mblog.wechat.entity.message.NewsMessage;
import mblog.wechat.utill.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 描述：菜单点击事件，处理
 *
 */
public class MenuClickService {

	/**
	 * 
	 * 描述：@param eventKey
	 * 描述：@param fromUserName
	 * 描述：@param toUserName
	 * 描述：@return 接受用户点击事件，通过微信推送给用户消息，跳转页面，发送消息等
	 */
	public static String getClickResponse(String eventKey, String fromUserName,
			String toUserName) {
		String respMessage = "";
		// TODO 判断evetKey事件处理
		if(eventKey.equals("KEY_GOOD"))
		{
			// 创建图文消息(回复用)
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

			List<Article> articleList = new ArrayList<Article>();

			Article article = new Article();
			article.setTitle("推送测试");
			article.setDescription("博客文章推送测试");
			article.setPicUrl("http://sheetanchor.tunnel.qydev.com/store/thumbs/2016/0509/0916082870in.jpg");
			article.setUrl("http://sheetanchor.tunnel.qydev.com/view/1");
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			respMessage = MessageUtil.newsMessageToXml(newsMessage);

		}
		return respMessage;
	}

}
