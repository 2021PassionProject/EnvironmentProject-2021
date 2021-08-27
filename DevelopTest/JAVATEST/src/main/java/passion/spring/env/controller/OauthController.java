package passion.spring.env.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import passion.spring.env.helper.constants.SocialLoginType;
import passion.spring.env.service.OauthService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value="/auth")
@Slf4j
public class OauthController {
    private final OauthService oauthService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type을 받아 처리
     * @Param socialLoginType (GOOGLE, KAKAO, NAVER)
     */
    @GetMapping(value="/{socialLoginType}")
    public void socialLoginType(@PathVariable(name="socialLoginType")SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }

    /**
     * Social Login API Server 요청에 의한 callback을 처리
     * @param socialLoginType (GOOGLE, KAKAO, NAVER)
     * @param code API Server로부터 넘어오는 code
     * @return SNS Login 요청 결과로 받은 json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value="/{socialLoginType}/callback")
    public String callback (@PathVariable(name="socialLoginType") SocialLoginType socialLoginType, @RequestParam(name="code") String code) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code::{}", code);
        return oauthService.requestAccessToken(socialLoginType, code);
    }
}
