package passion.spring.env.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProjectController {
    @GetMapping("/projects")
    public String projects() {
        return "project/projects";
    }
    @GetMapping("/project-details")
    public String projectdetails() {
        return "project/project-details";
    }
    @GetMapping("/project-details2")
    public String projectdetails2() {
        return "project/project-details2";
    }
    @GetMapping("/project-details3")
    public String projectdetails3() {
        return "project/project-details3";
    }
    @GetMapping("/project-details4")
    public String projectdetails4() {
        return "project/project-details4";
    }
    @GetMapping("/project-details5")
    public String projectdetails5() {
        return "project/project-details5";
    }
    @GetMapping("/project-details6")
    public String projectdetails6() {
        return "project/project-details6";
    }
    @GetMapping("/project-details7")
    public String projectdetails7() {
        return "project/project-details7";
    }
    @GetMapping("/project-details8")
    public String projectdetails8() {
        return "project/project-details8";
    }
}
