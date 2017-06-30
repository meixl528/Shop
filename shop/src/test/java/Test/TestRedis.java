package Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssm.dto.User;
import com.ssm.service.UserService;

public class TestRedis {

	//@Test
	public static void test() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService user = (UserService) ctx.getBean("userService");
				
		System.out.println(user.getUserList());
		
	}

	public static void main(String[] args) {
		test();
	}
	
}
