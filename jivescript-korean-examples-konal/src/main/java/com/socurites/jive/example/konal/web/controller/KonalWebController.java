package com.socurites.jive.example.konal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.socurites.jive.core.bot.JiveScriptBot;
import com.socurites.jive.core.bot.builder.JiveScriptReplyBuilder;
import com.socurites.jive.ext.analyze.entity.JiveExtDomainEntity;
import com.socurites.jive.ext.analyze.entity.JiveScriptExtBot;
import com.socurites.jive.ext.analyze.entity.JiveScriptExtReplyBuilder;

@Controller
public class KonalWebController {
	@Autowired
	private JiveScriptBot bot;
	
	@Autowired
	private RestTemplate restTemplate;
			
	@Autowired 
	private HttpEntity<String> httpEntity;
	
	@RequestMapping("/")
    @ResponseBody
    String listen(@RequestParam("message")String message) {
		System.out.println("message=" + message);
		
		JiveScriptReplyBuilder reply = bot.reply("송준이", message);
		
		String weatherResult = ""; 
		if ( bot instanceof JiveScriptExtBot ) {
			JiveScriptExtBot extBot = (JiveScriptExtBot)bot;
			reply.getReplyAsText();
			JiveExtDomainEntity extDomain = ((JiveScriptExtReplyBuilder)reply).getDomainEntity();
			System.out.println(extDomain);
			
			try {
				String sido = extDomain.getProp("sido");
				String sigungu = extDomain.getProp("sigungu");
				String district = extDomain.getProp("district");

				ResponseEntity<String>  responseEntity = restTemplate.exchange("http://apis.skplanetx.com/weather/current/hourly?lon=&village=" + district + "&county=" + sigungu + "&lat=&city=" + sido + "&version=1", HttpMethod.GET, httpEntity, String.class);
				String responseBody = responseEntity.getBody();
				
				JsonObject jsonObj = new JsonParser().parse(responseBody).getAsJsonObject();
				JsonObject hourlyWeatherObject = jsonObj.get("weather").getAsJsonObject()
						.get("hourly").getAsJsonArray().get(0).getAsJsonObject()
				;
				
				String sky = hourlyWeatherObject
						.get("sky").getAsJsonObject()
						.get("name")
						.getAsString()
						.replaceAll("\"", "");
			
				String tempCurr = hourlyWeatherObject
						.get("temperature").getAsJsonObject()
						.get("tc")
						.getAsString()
						.replaceAll("\"", "");
				
				String tempMax = hourlyWeatherObject
						.get("temperature").getAsJsonObject()
						.get("tmax")
						.getAsString()
						.replaceAll("\"", "");
				
				String tempMin = hourlyWeatherObject
						.get("temperature").getAsJsonObject()
						.get("tmin")
						.getAsString()
						.replaceAll("\"", "");
				
				String humidity = hourlyWeatherObject
						.get("humidity")
						.getAsString()
						.replaceAll("\"", "");
				
				weatherResult += "\n";
				weatherResult += sky + ", 현재 온도는 " + tempCurr + "(최저: " + tempMin + ", 최고: " + tempMax + "), 습도는 " + humidity + "%입니다.";
			} catch ( Exception e) {
				e.printStackTrace();
				return reply.getReplyAsText();
			}
		}
		
        return reply.getReplyAsText() + weatherResult;
    }

}
