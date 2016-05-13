package mblog.wechat.utill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import mblog.wechat.entity.message.Article;
import mblog.wechat.entity.message.NewsMessage;

import java.util.List;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-10
 * @Desc JSON处理类
 */
public class JsonUtils {
    public static String read(String response,String key){
        String JsonValue = "";
        JSONObject object = JSON.parseObject(response);
        JsonValue = object.getString(key);
        return JsonValue;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-12 10:31
     * @Desc 拼接群发文本消息JSON
     */
    public static String getTextMessage(String message){
        String messageJson = "{" +
                "   \"filter\":{" +
                "      \"is_to_all\":true," +
                "      \"group_id\":0"+
                "   }," +
                "   \"text\":{" +
                "      \"content\":\""+message+"\"" +
                "   }," +
                "    \"msgtype\":\"text\"" +
                "}";
        return  messageJson;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-13 15:22
     * @Desc 拼接获取群发图文消息mediaID  JSON
     */
    public static String getMpnewsMessageId(NewsMessage newsMessage){
        StringBuffer messageJson = new StringBuffer();
        int articleCount = newsMessage.getArticleCount();
        if(articleCount<=0){
            return null;
        }else{
            List articleList = newsMessage.getArticles();
            if(articleList.size()>0){
                messageJson.append(" { ");
                messageJson.append(" \"articles\": [ ");
                for(int i=0;i<articleList.size();i++){
                    Article article = (Article) articleList.get(i);
                    if(i==0){
                        messageJson.append(" { ");
                        messageJson.append(" \"thumb_media_id\":\""+article.getThumb_media_id()+"\", ");
                        messageJson.append(" \"author\":\""+article.getAuthor()+"\", ");
                        messageJson.append(" \"title\":\""+article.getTitle()+"\", ");
                        messageJson.append(" \"content_source_url\":\""+article.getUrl()+"\", ");
                        messageJson.append(" \"content\":\""+article.getContent()+"\", ");
                        messageJson.append(" \"digest\":\""+article.getDescription()+"\", ");
                        messageJson.append(" \"show_cover_pic\":1 ");
                        messageJson.append(" } ");
                    }else {
                        messageJson.append(" ,{ ");
                        messageJson.append(" \"thumb_media_id\":\""+article.getThumb_media_id()+"\", ");
                        messageJson.append(" \"author\":\""+article.getAuthor()+"\", ");
                        messageJson.append(" \"title\":\""+article.getTitle()+"\", ");
                        messageJson.append(" \"content_source_url\":\""+article.getUrl()+"\", ");
                        messageJson.append(" \"content\":\""+article.getContent()+"\", ");
                        messageJson.append(" \"digest\":\""+article.getDescription()+"\", ");
                        messageJson.append(" \"show_cover_pic\":1 ");
                        messageJson.append(" } ");
                    }
                }
                messageJson.append(" ] ");
                messageJson.append(" } ");

            }else{
                return null;
            }
        }
        return messageJson.toString();
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-13 15:43
     * @Desc 拼接群发图文消息JSON
     */
    public static String getMpNewsMessage(String mediaId){
        String messageJson = "{" +
                "   \"filter\":{" +
                "      \"is_to_all\":true," +
                "      \"group_id\":0" +
                "   }," +
                "   \"mpnews\":{" +
                "      \"media_id\":\""+mediaId+"\"" +
                "   }," +
                "    \"msgtype\":\"mpnews\"" +
                "}";
        return messageJson;
    }
}
