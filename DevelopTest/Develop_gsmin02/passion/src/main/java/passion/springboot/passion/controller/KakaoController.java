package passion.springboot.passion.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class KakaoController {
    private final static String K_CLIENT_ID = "c34c9b0b74f8394309ca8e4fcdeef949";
    private final static String K_REDIRECT_URI = "http://localhost:8888/kakaoLogin";

    public static String getAuthorizationUrl(HttpSession session) {
        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id=" + K_CLIENT_ID + "&redirect_uri=" + K_REDIRECT_URI + "&response_type=code";
        return kakaoUrl;
    }

    public static JsonNode getAccessToken(String authorize_code) {
        final String requestUrl = "https://kauth.kakao.com/oauth/token";
        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id",""));
        postParams.add(new BasicNameValuePair("redirect_uri", "login-kakao"));
        postParams.add(new BasicNameValuePair("code", authorize_code)); // 로그인 과정 중 얻은 code 값

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(requestUrl);
        JsonNode returnNode = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));
            final HttpResponse response = client.execute(post);

            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
        }catch(IOException e) {
            e.printStackTrace();
        }
        return returnNode;
    }

    public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
        // add Header
        post.addHeader("Authorization", "Bearer " + accessToken);
        JsonNode returnNode = null;
        try {
            final HttpResponse response = client.execute(post);
            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
        }catch(IOException e) {
            e.printStackTrace();
        }
        return returnNode;
    }

    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        }catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
