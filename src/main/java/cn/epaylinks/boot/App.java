package cn.epaylinks.boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.epaylinks.controller.UserController;

/**
 * Hello world!
 *
 */
@Controller
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("cn.epaylinks")
@MapperScan("cn.epaylinks.mapper")
public class App
{
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource()
	{
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	@Bean
	public SqlSessionFactory SqlSessionFactoryBean() throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	public PlatformTransactionManager transactionManager()
	{
		return new DataSourceTransactionManager(dataSource());
	}

	@RequestMapping("/")
	String index()
	{
		System.out.println("进入首页");
		return "index";
	}

	public static void main(String[] args)
	{
		SpringApplication.run(new Object[] { App.class, UserController.class },
				args);
	}
}
