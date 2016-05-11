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
}
