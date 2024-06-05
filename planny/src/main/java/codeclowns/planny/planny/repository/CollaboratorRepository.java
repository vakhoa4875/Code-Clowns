
package codeclowns.planny.planny.repository;

import codeclowns.planny.planny.data.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {
    List<Collaborator> findByWorkSpace_WorkspaceId(Integer workspaceId);
     List<Collaborator> findByUsernameContainingIgnoreCase(String username);
}
