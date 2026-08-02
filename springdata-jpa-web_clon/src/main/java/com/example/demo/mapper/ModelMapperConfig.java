package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean		// Bean -> Singleton / static 으로 하나만 생성됨
	public ModelMapper modelMapper() {
		//return new ModelMapper();
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
					.setFieldAccessLevel(AccessLevel.PRIVATE)	// 필드 접근 수준 설정(PRIVATE 필드에 직접 접근 가능)
					.setFieldMatchingEnabled(true)				// getter/setter가 없는 경우 필드에 직접 접근 허용
					.setMatchingStrategy(MatchingStrategies.STRICT);	// 매칭 전략 설정(STANDARD, STRICT, LOOSE)
		
		return modelMapper;
	}

}
