package passion.spring.env.service.social;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{
    @Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;
    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    @Value("${sns.google.token.url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope","openid email profile");
        params.put("response_type","code");
        params.put("client_id",GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri",GOOGLE_SNS_CALLBACK_URL);
        params.put("access_type","offline");

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

    /**
     * API Server로부터 받은 code를 활용하여 사용자 인증 정보 요청
     *
     * @param code API Server에서 받아온 code
     * @return API 서버로부터 응답받은 json형태의 결과를 string으로 반영
     */
   // @Override
    public String requestAccessTokenUsingURL(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code",code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret",GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri",GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type","authorization_code");
        params.put("access_type","offline");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return "구글 로그인 요청 처리 실패";
    }
    @Override
    public String requestAccessToken(String code) {
        try {
            URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("client_id",GOOGLE_SNS_CLIENT_ID);
            params.put("client_secret",GOOGLE_SNS_CLIENT_SECRET);
            params.put("redirect_uri",GOOGLE_SNS_CALLBACK_URL);
            params.put("grant_type","authorization_code");
            params.put("access_type","offline");

            String parameterString = params.entrySet().stream()
                    .map(x -> x.getKey() + "=" + x.getValue())
                    .collect(Collectors.joining("&"));

            BufferedOutputStream bous = new BufferedOutputStream(conn.getOutputStream());
            bous.write(parameterString.getBytes());
            bous.flush();
            bous.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb =new StringBuilder();
            String line;

            while((line= br.readLine()) != null) {
                sb.append(line);
            }

            if(conn.getResponseCode() == 200) {
                return sb.toString();
            }
            return "구글로그인 요청 처리 실패";
        }catch(IOException e) {
            throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
        }
    }


}