package net.vikram.pmsrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.vikram.pmsrest.entity.Project;
import net.vikram.pmsrest.exception.ResourceNotFoundException;
import net.vikram.pmsrest.repository.ProjectRepository;

@RestController 
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	//Get all project
	@GetMapping
	public List<Project> getAllProjects()
	{
	return projectRepository.findAll();	
	}
	
	//Create  project
	@PostMapping
	public Project createProjects(@RequestBody Project project)
	{
	return projectRepository.save(project);	
	}
	
	//Get project By Id
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId)
			throws ResourceNotFoundException {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found for this id :: " + projectId));
		return ResponseEntity.ok().body(project);
	}
	
	//Update a  project
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
			@RequestBody Project project) throws ResourceNotFoundException {
		Project Existingproject = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Projct not found for this id :: " + projectId));
		Existingproject.setProjectId(project.getProjectId());
        Existingproject.setProjectName(project.getProjectName());
		Existingproject.setDescription(project.getDescription());
		Existingproject.setAuthorId(project.getAuthorId());
		Existingproject.setStatus(project.getStatus());
		final Project updatedProject = projectRepository.save(project);
		return ResponseEntity.ok(updatedProject);
	}
	
	
	//delete project by id
	@DeleteMapping("/projects/{id}")
	public ResponseEntity deleteProject(@PathVariable(value = "id") Long projectId)
			throws ResourceNotFoundException {
			projectRepository.findById(projectId)
					.orElseThrow(() -> new ResourceNotFoundException("Project not found for this id :: " + projectId));

			projectRepository.deleteById(projectId);
			
			return ResponseEntity.ok().build();
		}
	
	
	
}


