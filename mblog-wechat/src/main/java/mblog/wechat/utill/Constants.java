package mblog.wechat.utill;

	/**
	 * @Author 阁楼麻雀
	 * @Date 2016-5-11 15:34
	 * @Desc 常量类
	 */

public class Constants {
	/**
	 * APPID
	 */
	public static String APPID = "wx6ae736d7c8dd60a9";//测试号     //"wx13e15f1ab754f357";订阅号
	/**
	 * SECRET
	 */
	public static String SECRET = "9049145084047b96a07a5fd9968a6cd0";//测试号    //"8cc77077c8d62cac71d6d60464afe853";订阅号
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	/**
	 * 获取用户信息接口
	 */
	public static String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}";
	/**
	 * 推送信息接口
	 */
	public static String MSG_PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/send?access_token={0}";
	/**
	 * 获取用户列表接口
	 */
	public static String GET_USERLIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}";
	/**
	 * 图片上传接口
	 */
	public static String UP_LOADING_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 700000;
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "src/com/resources/config.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "anchor123";
}
