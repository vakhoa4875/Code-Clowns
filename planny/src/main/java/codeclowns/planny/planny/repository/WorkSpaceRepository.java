package codeclowns.planny.planny.repository;

import codeclowns.planny.planny.data.entity.WorkSpaceE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkSpaceRepository extends JpaRepository<WorkSpaceE,Integer> {
    @Query(value = "SELECT TOP 3 * FROM Workspace", nativeQuery = true)
    List<WorkSpaceE> findTop3ByOrderByLastViewedDesc();
}
