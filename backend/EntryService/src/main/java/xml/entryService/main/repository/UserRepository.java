package xml.entryService.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.entryService.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
