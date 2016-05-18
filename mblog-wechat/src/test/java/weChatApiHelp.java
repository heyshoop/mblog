import mblog.scheduler.entity.Token;
import mblog.wechat.service.MsgPushService;
import mblog.wechat.utill.Constants;
import org.junit.Test;

import java.io.File;

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
        String url = Constants.UP_MPNEWS_URL;
        String accessToken = null;
        String response = null;
        MsgPushService msgPushService = new MsgPushService();

        try {
            //accessToken = msgPushService.getAccessToken(appid,secret);
            //File file = new File("D:\\data\\thumbs\\2016\\0509\\吾王.jpg");
            //response = msgPushService.getMpNewsMediaId(accessToken,url,"a");
            //response = msgPushService.seedTextMessage(accessToken,Constants.MSG_FZPUSH_URL,"aaaaa");
            //response = msgPushService.seedMpNesMessage(accessToken,Constants.MSG_FZPUSH_URL,"a");
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("response:"+response);
        //System.out.println("response:"+response);
        System.out.println("获取到的accesstoken为："+ Token.getInstance());
    }
}
