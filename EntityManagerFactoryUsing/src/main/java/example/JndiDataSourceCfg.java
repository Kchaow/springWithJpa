package example;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class JndiDataSourceCfg 
{
	@Bean
	public DataSource dataSource()
	{
		final JndiDataSourceLookup dsLookup = 
				new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		return dsLookup.getDataSource("persistence/springWithJpa");
	}
}
