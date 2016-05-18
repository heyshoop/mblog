package mblog.scheduler.entity;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-17
 * @Desc accesstoken实体类
 */
public class Token {
    private Token() {}
    private  String token;
    private static Token instance  = new Token();
    public static Token getInstance() {
        return instance;
    }
    public  String getToken() {
        return token;
    }
    public  void setToken(String token) {
        this.token = token;
    }
}
