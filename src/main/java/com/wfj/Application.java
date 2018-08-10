package com.wfj;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.wfj.util.DynamicDataSourceRegister;
import com.wfj.util.PropertiesListener;

@SpringBootApplication
@EnableSwagger2//启动swagger注解
@Import({DynamicDataSourceRegister.class})//注册动态多数据源,引入当前文件
@MapperScan("com.*.mapper")//扫描当前mapper包,省去文件中设置@Mapper注解
@EnableAsync//为了让@Async注解能够生效,执行异步流程
@EnableTransactionManagement //为程序增加事务，需在service上增加注解@Transactional
public class Application extends WebMvcConfigurerAdapter {
	//过滤后缀jsp,html,do...
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}
	@Bean
	public Queue queue(){
		return new ActiveMQQueue("miao.queue");
	}
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
	//主启动器
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
        // 第四种方式：注册监听器
        application.addListeners(new PropertiesListener("spring-client-exception-config.properties"));
        application.run(args);
	}
}
