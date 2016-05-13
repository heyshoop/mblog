package mblog.wechat.service;

import mblog.wechat.entity.message.NewsMessage;
import mblog.wechat.utill.Constants;
import mblog.wechat.utill.HttpClientUtil;
import mblog.wechat.utill.JsonUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
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
     * @Desc 获取素材上传后的mediaID
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
            builder.setMode(HttpMultipartMode.RFC6532);//设置浏览器兼容模式
            builder.addBinaryBody("media", file, ContentType.create("application/octet-stream", Consts.UTF_8), file.getName());
            HttpEntity httpEntity  = builder.build();
            response = HttpClientUtil.executeHttpPost(url,httpClient,httpEntity);
            meidaId = JsonUtils.read(response,"media_id");
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
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-13 15:33
     * @Desc 获取图文消息mediaid
     */

    public static String getMpNewsMediaId(String accessToken,String seedUrl,NewsMessage newsMessage) throws IOException {
        String mediaId = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(seedUrl,accessToken);
            httpClient = HttpClientUtil.getHttpClient();
            String mpNewsMessage = JsonUtils.getMpnewsMessageId(newsMessage);
            HttpEntity mpNewsMessageJson = new StringEntity(mpNewsMessage,"utf-8");
            String response = HttpClientUtil.executeHttpPost(url,httpClient,mpNewsMessageJson);
            mediaId = JsonUtils.read(response,"media_id");
        }catch (Exception e){
            logger.error("获取图文消息mediaid出错,请检查参数",e);
        }finally {
            httpClient.close();
        }
        return mediaId;
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-13 15:46
     * @Desc 发送图文消息
     */
    public static String seedMpNesMessage(String accessToken,String seedUrl,String mediaId) throws IOException {
        String response = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(seedUrl,accessToken);
            httpClient = HttpClientUtil.getHttpClient();
            String mpNewsMessage = JsonUtils.getMpNewsMessage(mediaId);
            HttpEntity mpNewsMessageJson = new StringEntity(mpNewsMessage,"utf-8");
            response = HttpClientUtil.executeHttpPost(url,httpClient,mpNewsMessageJson);
            System.out.println(response);
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
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-13 11:31
     * @Desc 将文件转化为二进制流
     */
    /*public static byte[] getByte(File file) throws IOException {
        byte[] bytes = null;
        if(file != null){
            FileInputStream fileInputeStream = new FileInputStream(file);
            int length = (int) file.length();
            if(length>Integer.MAX_VALUE){
                logger.error("文件太大，读取失败！");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while(offset<bytes.length&&(numRead=fileInputeStream.read(bytes,offset,bytes.length-offset))>=0)
            {
                offset+=numRead;
            }
            //如果得到的字节长度和file实际的长度不一致就可能出错了
            if(offset<bytes.length)
            {
                System.out.println("文件长度有误，读取失败！");
                return null;
            }
            fileInputeStream.close();
        }
        return bytes;
    }*/


}
