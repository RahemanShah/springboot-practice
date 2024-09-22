package in.pr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.pr.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
