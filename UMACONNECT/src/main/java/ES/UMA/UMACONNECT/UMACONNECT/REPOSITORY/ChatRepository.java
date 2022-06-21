package ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.Chats;


@Repository
public interface ChatRepository extends JpaRepository<Chats, Long>  {
    public Optional<Chats> findByIDUser(Long IDUser);
}
