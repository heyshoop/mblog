import mblog.wechat.service.MsgPushService;
import mblog.wechat.utill.Constants;
import org.junit.Test;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-11
 * @Desc 微信接口测试类
 */
public class weChatApiHelp {

    @Test
    public void weChatGetTest(){
        String appid = Constants.APPID;
        String secret = Constants.SECRET;
        String accessToken = null;
        String userListJson = null;
        MsgPushService msgPushService = new MsgPushService();
        try {
            accessToken = msgPushService.getAccessToken(appid,secret);
            userListJson = msgPushService.getUserListJson(accessToken);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("accessToken:"+accessToken);
        System.out.println("userListJson:"+userListJson);
    }
}
