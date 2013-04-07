package com.tmm.maker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Spring doesn't yet support pure java configuration of spring security
 * so this will just have to import the old fashioned xml file.
 * 
 * @author rob
 *
 */
@Configuration
@ImportResource("classpath:META-INF/spring/security.xml")
public class SecurityConfiguration {

	@Bean
	public ShaPasswordEncoder passwordEncoder(){
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
		encoder.setEncodeHashAsBase64(true);
		return encoder;
	}

}