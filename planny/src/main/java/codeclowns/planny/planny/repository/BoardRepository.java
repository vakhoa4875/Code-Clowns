package codeclowns.planny.planny.repository;

import codeclowns.planny.planny.data.entity.BoardE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import codeclowns.planny.planny.data.entity.WorkSpaceE;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardE,Integer> {
    List<BoardE> findAllByWorkSpaceIsEnabledTrue();
}
