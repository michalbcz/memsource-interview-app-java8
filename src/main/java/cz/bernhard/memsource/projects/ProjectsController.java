package cz.bernhard.memsource.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public Projects getAll() {
        return projectService.getAll();
    }

}
