import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-11
 * @Desc JSON测试类
 */
public class JsonTest {
    @Test
    public void MyJsonTest(){
        String json = "{\n" +
                "    \"total\": 4, \n" +
                "    \"count\": 4, \n" +
                "    \"data\": {\n" +
                "        \"openid\": [\n" +
                "            \"oF-oMv_xIM8XoAhr5VIO0-Ho1iBs\", \n" +
                "            \"oF-oMv8jXE9wcXhvdZkMq7w3sdQY\", \n" +
                "            \"oF-oMvxumCmq9dl4_7nTT0kMvkhw\", \n" +
                "            \"oF-oMv_pri05bLsPGzgyCfpytrpI\"\n" +
                "        ]\n" +
                "    }, \n" +
                "    \"next_openid\": \"oF-oMv_pri05bLsPGzgyCfpytrpI\"\n" +
                "}";

        JSONObject object = JSON.parseObject(json);
        String a = object.getString("data");
        JSONObject object1 = JSON.parseObject(a);
        String b = object1.getString("openid");
        System.out.println(json);
        System.out.println(a);
        System.out.println(b);
    }
}
