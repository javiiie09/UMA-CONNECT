package ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    public Optional<User> findByUsername(String username);
}