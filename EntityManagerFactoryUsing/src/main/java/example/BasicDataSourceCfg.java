package example;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class BasicDataSourceCfg 
{
	private static Logger logger = LoggerFactory.getLogger(BasicDataSourceCfg.class);
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource()
	{
		try 
		{
			HikariConfig hc = new HikariConfig();
			hc.setJdbcUrl(url);
			hc.setDriverClassName(driverClassName);
			hc.setUsername(username);
			hc.setPassword(password);
			HikariDataSource dataSource = new HikariDataSource(hc);
			dataSource.setMaximumPoolSize(25);
			return dataSource;
		}
		catch (Exception e)
		{
			logger.error("DBCP DataSource bean cannot be created!", e);
			return null;
		}
	}
}
