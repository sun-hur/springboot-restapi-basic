package com.example.hkms.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@MapperScan(value="com.example.hkms.**.model.mapper")
@Configuration
public class DataSourceConfig {
	public final static String MYBATIS_CONFIG_LOCATION_PATH = "classpath:mybatis-config.xml";
	public final static String MYBATIS_MAPPER_LOCATION_PATH = "classpath:mybatis/mapper/**/*.xml";
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().getResources(MYBATIS_MAPPER_LOCATION_PATH);
		sqlSessionFactory.setMapperLocations(res);
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory);
		return sqlSessionTemplate;
	}
}
