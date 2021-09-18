package passion.springboot.passion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;

@RequiredArgsConstructor
@Controller // Spring Web MVC 컨트롤러
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @REstController  // Restful 웹 서비스 컨트롤러
public class HomeController {

    @GetMapping("") // url : http://<server_ip>:<port>/
    public String goHome(Model model, String[] args) {
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        model.addAttribute("month", month);
        model.addAttribute("day", day);

        int[] data_mon = {2, 4, 4, 6, 5, 6};
        int[] data_day = {2, 5, 22, 17, 10, 5};
        int near_mon = 0;
        int near_day = 0;
        int min_mon = Integer.MAX_VALUE;
        int min_day = Integer.MAX_VALUE;

        for(int i = 0; i < data_mon.length; i++) {
            int a = Math.abs((data_mon[i] - month));
            if(min_mon > a) {
                min_mon = a;
                near_mon = data_mon[i];
            }
            else if (min_mon == a) {
                for(int j = 0; j < data_day.length; j++) {
                    int b = Math.abs((data_day[j] - day));
                    if(min_day > b) {
                        min_day = b;
                        near_day = data_day[j];
                    }
                }
            }
        }

        model.addAttribute("near_mon", near_mon);
        model.addAttribute("near_day", near_day);

        return "main/index";    // index.html 파일을 view or template으로 사용함
    }
}
