package passion.springboot.passion.config;

import org.springframework.context.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class AppConfig {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        /*
         * HTML Form에서 GET과 POST방식의 Method만 지원함
         * Hidden 타입의 input 태그의 속성들을 읽어서 HttpServletRequestWrapper.getMethod()
         * 반환 값을 변경해 요청된 HTTP 메소드의 타입을 PUT, DELETE, PATCH로 변경해주는 필터
         */
        return new HiddenHttpMethodFilter();
    }
}