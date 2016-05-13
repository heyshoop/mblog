package mblog.wechat.service;

import mblog.wechat.utill.Constants;
import mblog.wechat.utill.HttpClientUtil;
import mblog.wechat.utill.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.text.MessageFormat;

import java.io.IOException;


/**
 * Created by Netuser on 2016-5-9.
 * 微信消息推送服务
 */
public class MsgPushService {
    private static String TokenUrl = Constants.GET_ACCESSTOKEN_URL;
    private static String UserListUrl = Constants.GET_USERLIST_URL;
    private static String UploadTempUrl = Constants.UP_TEMPMEDIA_URL;
    private static Logger logger = LoggerFactory.getLogger(MsgPushService.class);


    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-10 16:14
     * @Desc 获取授权token
     */

    public static String getAccessToken(String appid,String secret) throws IOException {
        String accessToken = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(TokenUrl,appid,secret);
            httpClient = HttpClientUtil.getHttpClient();
            String response = HttpClientUtil.executeHttpGet(url,httpClient);
            accessToken = JsonUtils.read(response,"access_token");
        }catch (Exception e){
            logger.error("获取授权token出错,请检查参数",e);
        }finally {
            httpClient.close();//使用完后关闭httpclient
        }
        return accessToken;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 15:38
     * @Desc 获取关注用户列表数据
     */

    public static String getUserListJson(String accessToken) throws IOException {
        String userListJson = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(UserListUrl,accessToken);
            httpClient = HttpClientUtil.getHttpClient();
            String response = HttpClientUtil.executeHttpGet(url,httpClient);
            userListJson = JsonUtils.read(response,"data");
            userListJson = JsonUtils.read(userListJson,"openid");
        }catch (Exception e){
            logger.error("获取关注用户列表数据出错,请检查参数",e);
        }finally {
            httpClient.close();
        }
        return userListJson;
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 18:02
     * @Desc 获取图片上传后的mediaID
     */
    public static String getMediaId(String accessToken, File file,String type) throws IOException {
        String meidaId = null;
        if(file == null || accessToken == null || type == null){
            return null;
        }
        if(!file.exists()){
            logger.info("上传文件不存在,请检查!");
            return null;
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String response = null;
        try {
            String url = MessageFormat.format(UploadTempUrl,accessToken,type);
            httpClient = HttpClientUtil.getHttpClient();
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();//builder替代过时的MultipartEntity
            builder.addBinaryBody("media",file);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity,"UTF-8");
            System.out.println(response);
        }catch (Exception e){
            logger.error("获取图片上传后的mediaID出错,请检查参数",e);
        }finally {
            httpClient.close();
        }
        return meidaId;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 21:34
     * @Desc 推送文本消息
     */
    public static String seedTextMessage(String accessToken,String seedUrl,String message) throws IOException {
        String response = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(seedUrl,accessToken);
            httpClient = HttpClientUtil.getHttpClient();
            String textMessage = JsonUtils.getTextMessage(message);
            HttpEntity textMessageJson = new StringEntity(textMessage,"utf-8");
            response = HttpClientUtil.executeHttpPost(url,httpClient,textMessageJson);
            String errcode = JsonUtils.read(response,"errcode");
            if(errcode.equals("0")){
                response = "推送成功！";
            }else {
                response = "推送失败，请检查日志";
            }
        }catch (Exception e){
            logger.error("推送文本消息出错,请检查参数",e);
        }finally {
            httpClient.close();
        }
        return response;
    }

}
