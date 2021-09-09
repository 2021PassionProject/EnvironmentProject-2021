package passion.spring.env.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.JSONParserTokenManager;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class NaverController {
    @Value("${sns.naver.url}")
    private String NAVER_SNS_BASE_URL;
    @Value("${sns.naver.client.id}")
    private String NAVER_SNS_CLIENT_ID;
    @Value("${sns.naver.callback.url}")
    private String NAVER_SNS_CALLBACK_URL;
    @Value("${sns.naver.client.secret}")
    private String NAVER_SNS_CLIENT_SECRET;
    @Value("${sns.naver.token.url}")
    private String NAVER_SNS_TOKEN_URL;
    @Value("${sns.naver.profile.url}")
    private String NAVER_SNS_USERINFO_URL;

    HttpSession session = null;
    @GetMapping("/naverLogin")
    public String getNaverLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        session = request.getSession();
        String redirectURI = null;
        try {
            redirectURI = URLEncoder.encode(NAVER_SNS_CALLBACK_URL, "UTF-8");
        }catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());
        }
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = NAVER_SNS_BASE_URL + "?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                NAVER_SNS_CLIENT_ID, redirectURI, state);
        session.setAttribute("state",state);
        model.addAttribute("apiURL",apiURL);

        return "member/callback";
    }

    @GetMapping("/callback")
    public String getCallback(HttpServletRequest request, Model model) {
        session = request.getSession();
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = null;
        try {
            redirectURI = URLEncoder.encode(NAVER_SNS_CALLBACK_URL, "UTF-8");
        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String apiURL;
        apiURL = NAVER_SNS_TOKEN_URL + "?grant_type=authorization_code&";
        apiURL += "client_id=" + NAVER_SNS_CLIENT_ID;
        apiURL += "&client_secret=" + NAVER_SNS_CLIENT_SECRET;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        System.out.println("apiURL=" + apiURL);
        String res = null;
        try {
            res = requestToServer(apiURL);
        }catch(IOException e) {
            e.printStackTrace();
        }

        if(res != null && !res.equals("")) {
            model.addAttribute("res",res);

            Map<String, Object> parsedJson = null;
            try {
                parsedJson = new JSONParser(res).parseObject();
            }catch(ParseException e) {
                e.printStackTrace();
            }

            String access_token = (String) parsedJson.get("access_token");
            String profileURL;
            String profiles = null;
            profileURL = NAVER_SNS_USERINFO_URL + "?access_token=" + access_token;
            try {
                profiles = requestToServer(profileURL);
            }catch(IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> profileJson = null;
                model.addAttribute("profiles",profiles);
                try {
                    profileJson = new JSONParser(profiles).parseObject();
                }catch(ParseException e) {
                    e.printStackTrace();
                }
            Map<String, Object> response = (Map<String, Object>) profileJson.get("response");

                session.setAttribute("naverName",response.get("name"));
                session.setAttribute("naverEmail",response.get("email"));
                System.out.println(response);
            System.out.println(profiles);
            session.setAttribute("currentUser",res);
            session.setAttribute("currentAT",parsedJson.get("access_token"));
            session.setAttribute("currentRT",parsedJson.get("refresh_token"));
        } else {
            model.addAttribute("res","Login failed!");
        }
        return "redirect:/";
    }
    /**
     *  서버 통신 메서드
     * @param apiURL
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL) throws IOException {
        return requestToServer(apiURL, "");
    }
    /**
     * 서버 통신 메서드
     * @param apiURL
     * @param headerStr
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL, String headerStr) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("header Str : " + headerStr);

        if(headerStr != null && !headerStr.equals("")) {
            con.setRequestProperty("Authorization", headerStr);
        }

        int responseCode = con.getResponseCode();
        BufferedReader br;
        System.out.println("responseCode=" + responseCode);

        if (responseCode == 200) {  // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuffer res = new StringBuffer();
        while((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        if(responseCode == 200) {
            return res.toString();
        } else {
            return null;
        }
    }

    @GetMapping("/naverLogout")
    public String naverLogout() {
        session.removeAttribute("currentAT");
        session.removeAttribute("access_token");
        session.removeAttribute("currentRT");
        session.removeAttribute("refresh_token");
        session.invalidate();

        return "redirect:/";
    }


}
