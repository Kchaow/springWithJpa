package example;

import java.util.List;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import example.entities.Singer;
import example.service.SingerService;
import jakarta.annotation.PostConstruct;

@Testcontainers
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({"classpath:create-schema.sql"})
@SpringJUnitConfig(classes = {SingerServiceTest.TestContainersConfig.class})
public class SingerServiceTest 
{
	private static final Logger logger = LoggerFactory.getLogger(SingerServiceTest.class);
	
	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");
	
	@DynamicPropertySource
	static void setUp(DynamicPropertyRegistry registry) 
	{
		registry.add("jdbc.driverClassName", postgres::getDriverClassName);
		registry.add("jdbc.url", postgres::getJdbcUrl);
		registry.add("jdbc.username", postgres::getUsername);
		registry.add("jdbc.password", postgres::getPassword);
	 }
	
	@Autowired
	SingerService singerService;
	
	@Test
	@DisplayName("should return all singers")
	void testFindAll()
	{
		List<Singer> singers = singerService.findAll().toList();
		assertEquals(1, singers.size());
		singers.forEach(singer -> logger.info(singer.toString()));
	}
	
	@Configuration
	@Import(JpaConfig.class)
	public static class TestContainersConfig
	{
		@Autowired
		Properties jpaProperties;
		
		@PostConstruct
		public void initialize()
		{
			jpaProperties.put(Environment.FORMAT_SQL, true);
			jpaProperties.put(Environment.USE_SQL_COMMENTS, true);
			jpaProperties.put(Environment.SHOW_SQL, true);
		}
	}
}
