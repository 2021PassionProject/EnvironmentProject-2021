package passion.spring.environment.naverController;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class NaverLoginTutorial {
    /* NaverLoginBO */
    private NaverLoginBO naverLoginBO;

    /* NaverLoginBO */
    private void setNaverLoginBO( NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }

    @RequestMapping("/naverLogin")
    public ModelAndView naverLogin(HttpSession session) {
        /* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        /* 생성한 인증 URL을 View로 전달 */
        return new ModelAndView("naverLogin", "url", naverAuthUrl);
    }

    @RequestMapping("/callback")
    public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
        /* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
        OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
        String apiResult = naverLoginBO.getUserProfile(oauthToken);
        return new ModelAndView("callback","result", apiResult);
    }
}
