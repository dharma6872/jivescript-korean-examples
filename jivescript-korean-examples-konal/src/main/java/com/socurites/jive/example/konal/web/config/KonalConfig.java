package com.socurites.jive.example.konal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.socurites.jive.core.bot.JiveScriptBot;
import com.socurites.jive.ext.analyze.entity.JiveScriptExtBotBuilder;

@Configuration
public class KonalConfig {
	/** template file directory path. */
	private static final String TEMPLATE_DIR_PATH = "konal/rive";
	private static final String KEYWORD_DIR_PATH = null;
	
	/** analyzing user request. */
	private static final boolean ENABLE_ANALYZE = true;
	
	/**
	 * Initiates JiveSCriptBot bean.
	 * 
	 * @return
	 */
	@Bean
	public JiveScriptBot bot() {
		JiveScriptBot bot = (new JiveScriptExtBotBuilder())
				.analyze(ENABLE_ANALYZE)
				.parse(TEMPLATE_DIR_PATH, KEYWORD_DIR_PATH)
				.build()
		;
		
		return bot;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate;
	}
	
	@Bean
	public HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-skpop-userId", "socurites");
		headers.set("Accept-Language", "ko_KR");
		headers.set("Accept", "application/json");
		headers.set("access_token", "eb64fbf3-3400-3a27-86c1-618541ba70be");
		headers.set("appKey", "eb64fbf3-3400-3a27-86c1-618541ba70be");

		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		
		return entity;
	}
}
