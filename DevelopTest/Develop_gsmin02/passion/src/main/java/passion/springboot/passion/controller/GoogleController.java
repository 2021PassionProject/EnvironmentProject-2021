package passion.springboot.passion.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import passion.springboot.passion.model.GoogleOAuthRequest;
import passion.springboot.passion.model.GoogleOAuthResponse;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class GoogleController {

    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;
    @Value("${sns.google.token.url}")
    private String GOOGLE_SNS_TOKEN_URL;
    @Value("${sns.google.client.id}")
    private String clientId;
    @Value("${sns.google.client.secret}")
    private String clientSecret;

    /**
     * Authentication Code를 전달 받는 엔드포인트
     **/
    @GetMapping("google/auth")
    public String googleAuth(HttpSession session, @RequestParam(value = "code") String authCode)
            throws JsonProcessingException {

        //HTTP Request를 위한 RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //Google OAuth Access Token 요청을 위한 파라미터 세팅
        GoogleOAuthRequest googleOAuthRequestParam = GoogleOAuthRequest
                .builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(authCode)
                .redirectUri(GOOGLE_SNS_CALLBACK_URL)
                .grantType("authorization_code").build();

        //JSON 파싱을 위한 기본값 세팅
        //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //AccessToken 발급 요청
        ResponseEntity<String> resultEntity = restTemplate.postForEntity(GOOGLE_SNS_TOKEN_URL, googleOAuthRequestParam, String.class);

        //Token Request
        GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
        });

        System.out.println(resultEntity.getBody());

        //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
        String jwtToken = result.getIdToken();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                .queryParam("id_token", jwtToken).encode().toUriString();

        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});

        session.setAttribute("googleName", userInfo.get("name"));
        session.setAttribute("googleEmail", userInfo.get("email"));
        session.setAttribute("userInfo", userInfo);
        session.setAttribute("access_token", result.getAccessToken());
        System.out.println(userInfo);

        return "redirect:/";
    }

    /**
     * 토큰 무효화
     *
     * @return*/
    @GetMapping("google/revoke/token")
    public String revokeToken( HttpSession session) throws JsonProcessingException {

//        Map<String, String> result = new HashMap<>();
//        RestTemplate restTemplate = new RestTemplate();
//        final String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_REVOKE_TOKEN_BASE_URL)
//                .queryParam("access_token", token).encode().toUriString();
//
//        System.out.println("TOKEN ? " + token);
//
//        String resultJson = restTemplate.postForObject(requestUrl, null, String.class);
//        result.put("result", "success");
//        result.put("resultJson", resultJson);
//
//        System.out.println(result);
//        System.out.println(resultJson);
//
        session.invalidate();

        return "redirect:/";
    }
}
