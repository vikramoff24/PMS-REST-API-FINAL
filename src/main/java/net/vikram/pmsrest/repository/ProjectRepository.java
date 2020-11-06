package net.vikram.pmsrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.vikram.pmsrest.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{

}