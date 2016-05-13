package mblog.wechat.utill;

import mblog.wechat.entity.message.TextMessage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-11
 * @Desc httpclient工具类
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 15:18
     * @Desc 创建httpclient
     */

    public static CloseableHttpClient getHttpClient(){
        CloseableHttpClient httpClient = null;
        try {
            PoolingHttpClientConnectionManager hctm = new PoolingHttpClientConnectionManager();
            hctm.setMaxTotal(10);
            SSLContext sslContext = SSLContexts.custom().build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(hctm).build();
        }catch (Exception e){
            logger.error("初始化httpClient失败",e);
        }
        return httpClient;
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-10 16:30
     * @Desc 发起http get请求返回数据
     */
    public static String executeHttpGet(String url, CloseableHttpClient httpClient) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        String response = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse  = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            logger.error("发起http get请求返回数据错误,我也不知道原因",e);
        }finally {
            httpResponse.close();
        }
        return response;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 21:25
     * @Desc 发起http post请求返回数据
     */
    public static String executeHttpPost(String url,CloseableHttpClient httpClient,HttpEntity msgEntity) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(msgEntity);
        String response = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity,"UTF-8");
        }catch (Exception e){
            logger.error("发起http post请求返回数据错误,我也不知道原因",e);
        }finally {
            httpResponse.close();
        }
        return  response;
    }
}
