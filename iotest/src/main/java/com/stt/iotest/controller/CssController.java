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

import com.stt.iotest.model.XmlRequest;
import com.stt.iotest.model.XmlRequestService;

@Controller
public class CssController {
	private static final Logger log = LoggerFactory.getLogger(CssController.class);
	private XmlRequestService xmlRequestService;
	
	//req0101显示表单
		@RequestMapping(value="/req0101",method=RequestMethod.GET)
		public String req0101Handler(Map<String, Object> model){
			 XmlRequest xmlRequest = new XmlRequest(); 
			 model.put("xmlRequest", xmlRequest);
			return "req0101";
		}
		
		//req0101提交表单
		@RequestMapping(value="/req0101",method=RequestMethod.POST)
		public String processReq0101(@Valid XmlRequest xmlRequest, BindingResult result){
			if(result.hasErrors()){
				log.info("form error: " + result.toString());
				result.rejectValue("xmlRequest", "error.xmlRequest","无效的请求");
				return "req0101";
			}
			log.info("header is : " + xmlRequest.getXmlHeader());
			log.info("body is : " + xmlRequest.getXmlBody());
			//向目标发送真实的请求
			String xmlRes = getXmlRes(xmlRequest.getXmlHeader(), xmlRequest.getXmlBody());
			return "req0101";
		}
		private String getXmlRes(String xmlHeader, String xmlBody) {
			String USER_AGENT = "Mozilla/5.0";
			String GET_URL="http://221.181.100.71:8123/newcss/MN/mn.jsp";
			String POST_URL="http://221.181.100.71:8123/newcss/ReceiveMNXml.do";
			String POST_PARAMS = "xmlheader="+xmlHeader + "&" + "xmlbody="+ xmlBody;
			try {
				URL url = new URL(POST_URL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(15000);
				connection.setRequestProperty("User-Agent", USER_AGENT);
				
				connection.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(POST_PARAMS);
				wr.flush();
				wr.close();
				
				int responseCode = connection.getResponseCode();
				String responseMessage = connection.getResponseMessage();
				
				log.info("send 'post' to URL: " + POST_URL);
				log.info("xmlheader: " + xmlHeader);
				log.info("xmlbody: " + xmlBody);
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
