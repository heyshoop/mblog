package mblog.wechat.utill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
}
