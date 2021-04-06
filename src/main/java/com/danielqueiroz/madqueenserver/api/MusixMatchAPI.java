package com.danielqueiroz.madqueenserver.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusixMatchAPI {

    @Value("${musixmatch.credential}")
    private String token;
    
    private RestTemplate client;
    
    public MusixMatchAPI() throws MalformedURLException {
    	this.client = new RestTemplate();
	}
    
    
    public ResponseEntity<String> getResult() throws MalformedURLException {

    	String url = new URL("https://api.musixmatch.com/ws/1.1/track.search?format=json&callback=<string>&q_track=Heaven and hell&page_size=100&page=1&apikey=" + getToken()).toString();
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Cache-Control", "no-cache");
//    	headers.add("User-Agent", "PostmanRuntime/7.26.10");
    	headers.add("Accept", "*/*");
//    	headers.add("Accept-Encoding", "gzip, deflate, br");
    	headers.add("Connection", "keep-alive");
    	HttpEntity<String> entity = new HttpEntity<>("", headers);
    	
		ResponseEntity<String> exchange = client.exchange(url.toString(), HttpMethod.GET, entity, String.class);
		return exchange;
    }


	public String getToken() {
		return token;
	}

}
