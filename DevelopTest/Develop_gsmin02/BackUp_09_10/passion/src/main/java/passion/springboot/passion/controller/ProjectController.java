package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProjectController {
    @GetMapping("/projects")
    public String project() {
        return "project/projects";
    }
    @GetMapping("/project-details")
    public String project_details() {
        return "project/project-details";
    }
}
