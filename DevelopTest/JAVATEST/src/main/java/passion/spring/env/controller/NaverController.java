package passion.spring.env.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class NaverController {
    String CLIENT_ID = "21Q2VD9txfpkGj8TyYof";
    String CLIENT_SECRET = "o1Fjw_DuHK";
    String CALLBACK_URL = "http://localhost:8888/member/callback";

    HttpSession session = null;
    @GetMapping("/naverlogin")
    public String getNaverlogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        session = request.getSession();
        String redirectURI = null;
        try {
            redirectURI = URLEncoder.encode(CALLBACK_URL, "UTF-8");
        }catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());
        }
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                CLIENT_ID, redirectURI, state);
        session.setAttribute("state",state);
        model.addAttribute("apiURL",apiURL);

        return "redirect:/";
    }

    @GetMapping("/callback")
    public String getCallback(HttpServletRequest request, HttpServletResponse response, Model model) {
        session = request.getSession();
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = null;
        try {
            redirectURI = URLEncoder.encode(CALLBACK_URL, "UTF-8");
        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
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
            System.out.println(parsedJson);
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