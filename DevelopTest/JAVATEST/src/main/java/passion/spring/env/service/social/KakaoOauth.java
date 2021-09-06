package passion.spring.env.service.social;

import org.springframework.stereotype.Component;

@Component
public class KakaoOauth implements SocialOauth{
    @Override
    public String getOauthRedirectURL() {
        return "";
    }

    /**
     * API Server로부터 받은 code를 활용하여 사용자 인증 정보 요청
     *
     * @param code API Server에서 받아온 code
     * @return API 서버로부터 응답받은 json형태의 결과를 string으로 반영
     */
    @Override
    public String requestAccessToken(String code) {
        return null;
    }
}
