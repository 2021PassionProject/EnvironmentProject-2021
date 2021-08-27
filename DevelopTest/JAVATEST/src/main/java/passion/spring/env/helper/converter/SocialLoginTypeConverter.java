package passion.spring.env.helper.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import passion.spring.env.helper.constants.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}
