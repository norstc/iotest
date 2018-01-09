package com.stt.iotest.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stt.iotest.model.JsonRequest;

@Controller
public class PompController {
	private static final Logger log = LoggerFactory.getLogger(PompController.class);
	//req0201 GET
	@RequestMapping(value="/req0201",method=RequestMethod.GET)
	public String req0201Handler(Map<String,Object> model){
		JsonRequest jsonRequest = new JsonRequest();
		model.put("jsonRequest", jsonRequest);
		return "req0201";
	}
	
	//req0201 post
	@RequestMapping(value="/req0201", method=RequestMethod.POST)
	public String processReq0201(@Valid JsonRequest jsonRequest, BindingResult result){
		if(result.hasErrors()){
			log.info("form error: " + result.toString());
			result.rejectValue("jsonRequest", "error.jsonRequest","无效的请求");
			return "req0201";
		}
		log.info("form - json request url: " + jsonRequest.getJsonRequestUrl());
		log.info("form - json request data: " + jsonRequest.getJsonRequestData());
		String jsonRes = getJsonRes( jsonRequest.getJsonRequestUrl(),jsonRequest.getJsonRequestData());
		if(jsonRes.equals(null)){
			log.info("nothing get back!");
			return "req0201";
		}else{
			jsonRequest.setJsonResponse(jsonRes);
			return "req0201";
		}
		
	}

	private String getJsonRes(String jsonRequestUrl, String jsonRequestData) {
		String USER_AGENT = "Mozilla/5.0";
		String CONTENT_TYPE="application/json";
		String POST_URL=jsonRequestUrl;
		String POST_PARAMS = jsonRequestData;
		if(jsonRequestUrl.equals(null)){
			return null;
		}
		try {
			URL url = new URL(POST_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(15000);
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Type", CONTENT_TYPE);
			
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(POST_PARAMS);
			wr.flush();
			wr.close();
			
			int responseCode = connection.getResponseCode();
			String responseMessage = connection.getResponseMessage();
			
			log.info("send 'post' to URL: " + POST_URL);

			log.info("response code is " + responseCode);
			log.info("response message is " + responseMessage);
			
			
			
			//返回200
			if(responseCode == HttpURLConnection.HTTP_OK){
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while((inputLine = in.readLine()) != null){
					response.append(inputLine);
				}
				in.close();
				log.info("response is: " + response.toString());
				return response.toString();
				
			}
			
		} catch (MalformedURLException  e) {
			log.info("URL error");
			e.printStackTrace();
		} catch( IOException e ){
			log.info("IO error");
			e.printStackTrace();
		}catch (Exception e){
			log.info("error");
			e.printStackTrace();
		}
		return null;
	}
	
}

