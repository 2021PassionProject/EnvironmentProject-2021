package passion.spring.env.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProjectController {

    @GetMapping("/project-details")
    public String projectDetails() {
        return "project/project-details";
    }
    @GetMapping("/projects")
    public String projects() {
        return "project/projects";
    }
}
