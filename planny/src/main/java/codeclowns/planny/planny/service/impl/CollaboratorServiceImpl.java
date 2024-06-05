// CollaboratorServiceImpl.java
package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.entity.Collaborator;
import codeclowns.planny.planny.repository.CollaboratorRepository;
import codeclowns.planny.planny.service.CollaboratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;


    @Override
    public List<Collaborator> getCollaboratorsByWorkspaceId(Integer workspaceId) {
        return collaboratorRepository.findByWorkSpace_WorkspaceId(workspaceId);
    }

    @Override
    public List<Collaborator> searchCollaboratorsByUsername(String username) {
        return collaboratorRepository.findByUsernameContainingIgnoreCase(username);
    }
}
