package net.vikram.pmsrest.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.vikram.pmsrest.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
