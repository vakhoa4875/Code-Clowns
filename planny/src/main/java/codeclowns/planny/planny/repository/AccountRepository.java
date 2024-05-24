package codeclowns.planny.planny.repository;

import codeclowns.planny.planny.data.entity.AccountE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountE, Long> {

}