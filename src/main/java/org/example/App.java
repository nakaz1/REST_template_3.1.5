package org.example;

import org.example.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class App {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, null);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, httpEntity, String.class);
        String cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(cookie);

        httpHeaders.set(HttpHeaders.COOKIE, cookie);

        User user = new User(3L, "James", "Brown", (byte) 26);
        httpEntity = new HttpEntity<>(user, httpHeaders);
        System.out.println(httpEntity);

        responseEntity = restTemplate.exchange("http://94.198.50.185:7081/api/users/", HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseEntity.getBody());

        user = new User(3L, "Thomas", "Shelby", (byte) 26);
        httpEntity = new HttpEntity<>(user, httpHeaders);
        responseEntity = restTemplate.exchange("http://94.198.50.185:7081/api/users/", HttpMethod.PUT, httpEntity, String.class);
        System.out.println(responseEntity.getBody());


        httpEntity = new HttpEntity<>(null, httpHeaders);
        responseEntity = restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }
}
