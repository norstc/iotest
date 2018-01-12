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
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	public void setXmlRequestService(XmlRequestService xmlRequestService){
		this.xmlRequestService = xmlRequestService;
	}
	
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
			log.info("URL is : " + xmlRequest.getXmlRequestUrl());
			log.info("header is : " + xmlRequest.getXmlHeader());
			log.info("body is : " + xmlRequest.getXmlBody());
			//向目标发送真实的请求
			String xmlRes = getXmlRes(xmlRequest.getXmlRequestUrl(),xmlRequest.getXmlHeader(), xmlRequest.getXmlBody());
			if(xmlRes.equals(null)){
				log.info("nothing get back!");
				return "req0101";
			}else{
				log.info("xmlRes is : " + xmlRes);
				xmlRequest.setXmlResult(xmlRes);
				this.xmlRequestService.saveOrUpdate(xmlRequest);
				return "req0101";
			}
			
		}
		private String getXmlRes(String xmlRequestUrl, String xmlHeader, String xmlBody) {
			String USER_AGENT = "Mozilla/5.0";
			//String GET_URL="http://221.181.100.71:8123/newcss/MN/mn.jsp";
			//String POST_URL="http://221.181.100.71:8123/newcss/ReceiveMNXml.do";
			String POST_URL = xmlRequestUrl;
			
			//换行
			String LINE_END="\r\n";
			//分隔符
			String SEPRATOR = "---1661047269137";
			//第一个参数名
			String HEADER_NAME ="Content-Disposition: form-data; name=\"xmlhead\"";
			//第二个参数名
			String BODY_NAME  = "Content-Disposition: form-data; name=\"xmlbody\"";
			
			//构建post form  multipart 有固定的格式
			StringBuffer sb  = new StringBuffer();
			//开头是 -- 加 分隔符
			sb.append("--").append(SEPRATOR).append(LINE_END);
			//第一个参数名
			sb.append(HEADER_NAME);
			//正式参数前两个空行
			sb.append(LINE_END).append(LINE_END);
			//真实的参数
			sb.append(xmlHeader);
			//结束的空行
			sb.append(LINE_END);
			//第二个参数开头，仍然是 -- 加 分隔符 加 换行
			sb.append("--").append(SEPRATOR).append(LINE_END);
			//参数名
			sb.append(BODY_NAME);
			//正式参数前两个空行
			sb.append(LINE_END).append(LINE_END);
			//正式的参数
			sb.append(xmlBody);
			//结束换行
			sb.append(LINE_END);
			//整体结束：-- 加 分隔符 加 -- 加 换行
			sb.append("--").append(SEPRATOR).append("--").append(LINE_END);
			
			String POST_PARAMS = sb.toString();
			if(POST_URL.isEmpty() || POST_PARAMS.isEmpty()){
				return null;
			}
			log.info("POST_PARAMS : " +POST_PARAMS);
			try {
				URL url = new URL(POST_URL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(15000);
				connection.setRequestProperty("User-Agent", USER_AGENT);
				connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---1661047269137");
				
				connection.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.write(POST_PARAMS.getBytes());
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
