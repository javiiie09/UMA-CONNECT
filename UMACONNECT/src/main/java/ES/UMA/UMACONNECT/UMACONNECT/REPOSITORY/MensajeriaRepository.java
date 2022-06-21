package ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.Mensajeria;


@Repository
public interface MensajeriaRepository extends JpaRepository<Mensajeria, Long>  {
    //public Optional<Mensajeria> findByUsername(String username);
}
