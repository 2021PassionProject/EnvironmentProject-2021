//package passion.spring.env.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import passion.spring.env.controller.KakaoController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/")
//public class KakaoOauth {
//    @RequestMapping(value="/memberLoginForm", method = RequestMethod.GET)
//    public ModelAndView memberLoginForm(HttpSession session) {
//        ModelAndView mav = new ModelAndView();
//
//        String kakaoUrl = KakaoController.getAuthorizationUrl(session);
//        mav.setViewName("memberLoginForm");
//
//        mav.addObject("kakao_url",kakaoUrl);
//        return mav;
//    }
//
//    @RequestMapping(value="/kakaoLogin", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        // 결과값을 node에 담아줌
//        JsonNode node = KakaoController.getAccessToken(code);
//        // accessToken에 사용자의 로그인한 모든 정보가 들어있음
//        JsonNode accessToken = node.get("access_Token");
//        // 사용자의 정보
//        JsonNode userInfo= KakaoController.getKakaoUserInfo(accessToken);
//        String email = null;
//        String name = null;
//        String image = null;
//        // 유저정보 카카오세어 가져오기 Get properties
//        JsonNode properties = userInfo.path("properties");
//        JsonNode kakao_account = userInfo.path("kakao_account");
//        email = kakao_account.path("email").asText();
//        name = properties.path("nickname").asText();
//        image = properties.path("profile_image").asText();
//        session.setAttribute("userEmail",email);
//        session.setAttribute("nickname",name);
//        session.setAttribute("image",image);
//
//        mav.setViewName("main/index");
//        return mav;
//   }
//}
