package cz.bernhard.memsource.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Projects getAll() {
        return projectRepository.getAll();
    }
}
