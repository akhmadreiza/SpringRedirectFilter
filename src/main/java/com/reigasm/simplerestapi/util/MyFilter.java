package com.reigasm.simplerestapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(MyFilter.class);
    private static final String destUrl = "http://192.168.1.2:7001";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //do cast to HTTPServletReq
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        String uri = httpServletRequest.getRequestURI();

        logRequestDetail(httpServletRequest);

        if(uri.contains("/ARASpringTest")){
            //ResponseEntity<String> responseEntity = performRestCall(destUrl+uri);
            ResponseEntity<String> responseEntity = performRestCallExchange(destUrl+uri);
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.getWriter().write(convertObjectToJson(responseEntity.getBody())); //TODO STILL ERROR SHOW TO CLIENT
        }
        else{
            chain.doFilter(req, resp);
        }
    }

    private void logRequestDetail(HttpServletRequest req){

        logger.info( "==Start Logging Request==");
        logger.info( "Logging Request >> Method: {}", req.getMethod());
        logger.info( "Logging Request >> URL: {}", req.getRequestURL());
        logger.info( "Logging Request >> URI: {}", req.getRequestURI());
        logger.info( "Logging Request >> Remote Host: {}", req.getRemoteHost());
        logger.info( "Logging Request Header >> MyCustomHeader : {}", req.getHeader("MyCustomHeader"));
        logger.info( "==Done Logging Request Here==");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private ResponseEntity<String> performRestCall(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url,String.class);
    }

    private ResponseEntity<String> performRestCallExchange(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders("x-auth-ibab","27101991ABCIBAB567")), String.class);
    }

    HttpHeaders createHeaders(String authName, String authVal){
        return new HttpHeaders() {{
            /*String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );*/
            //String authHeader = "Basic " + new String( encodedAuth );
            //set( "Authorization", authHeader );
            set(authName,authVal);
        }};
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
