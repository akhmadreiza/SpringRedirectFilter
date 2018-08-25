package com.reigasm.simplerestapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

public class HttpUtil {
    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> performGetForEntity(String url){
        return restTemplate.getForEntity(url,String.class);
    }

    public ResponseEntity<String> performRestCallExchange(ApiForwardingUtil apiForwardingUtil) throws Exception
    {
        String sHttpMethod = apiForwardingUtil.getReq().getMethod();

        if(HttpMethod.POST.name().equals(sHttpMethod))
        {
            return performRestCallExchangePost(apiForwardingUtil);
        }
        else if(HttpMethod.GET.name().equals(sHttpMethod))
        {
            return performRestCallExchangeGet(apiForwardingUtil);
        }
        /*else if(HttpMethod.PUT.name().equals(sHttpMethod))
        {

        }
        else if(HttpMethod.PATCH.name().equals(sHttpMethod))
        {

        }*/
        else{
            return performRestCallExchangeGet(apiForwardingUtil);
        }
    }

    private ResponseEntity<String> performRestCallExchangePost(ApiForwardingUtil apiForwardingUtil) throws Exception {
        HttpHeaders headers = createAuthenticationHeaders(HttpHeaders.AUTHORIZATION,apiForwardingUtil.getIssuedToken());
        String body = constructRequestBody(apiForwardingUtil);
        return restTemplate.exchange(apiForwardingUtil.getDestinationUrl(), HttpMethod.POST, new HttpEntity<>(body,headers), String.class);
    }

    private ResponseEntity<String> performRestCallExchangeGet(ApiForwardingUtil apiForwardingUtil){
        HttpHeaders headers = createAuthenticationHeaders(HttpHeaders.AUTHORIZATION,apiForwardingUtil.getIssuedToken());
        return restTemplate.exchange(apiForwardingUtil.getDestinationUrl(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
    }

    public HttpHeaders createAuthenticationHeaders(String authName, String authVal){
        return new HttpHeaders() {{
            set(authName,authVal);
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }

    private String constructRequestBody(ApiForwardingUtil apiMigration) throws Exception{
        return apiMigration.getReq().getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(object);
    }
}
