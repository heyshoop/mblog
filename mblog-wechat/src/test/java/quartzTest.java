import mblog.scheduler.entity.Token;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-17
 * @Desc 测试定时任务执行
 */
public class quartzTest {
    private static Token token = Token.getInstance();
    public static void main(String args[]){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("conf/spring-quartz.xml");
        System.out.println("获取到的accesstoken为："+token.getToken());
    }
}
