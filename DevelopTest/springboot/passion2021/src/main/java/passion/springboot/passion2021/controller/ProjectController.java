package passion.springboot.passion2021.controller;

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
}
