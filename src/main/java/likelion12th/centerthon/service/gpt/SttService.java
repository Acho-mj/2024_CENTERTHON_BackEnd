package likelion12th.centerthon.service.gpt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
@RequiredArgsConstructor
@Service
public class SttService {

    @Value("${ncp.api.url}")
    private String apiUrl;

    @Value("${ncp.api.client-id}")
    private String clientId;

    @Value("${ncp.api.client-secret}")
    private String clientSecret;

    public String recognizeSpeech(MultipartFile audioFile) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(audioFile.getBytes(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }



}
