package ins.webeye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author webeye
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class WebEyeApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WebEyeApplication.class, args);
		System.out.println("          (♥◠‿◠)ﾉﾞ  WebEye启动成功   ლ(´ڡ`ლ)ﾞ  \n ");
    }
}