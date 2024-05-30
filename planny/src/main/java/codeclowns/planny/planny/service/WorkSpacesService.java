package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.WorkspacesDto;
import codeclowns.planny.planny.data.entity.WorkSpaceE;


import java.util.List;


public interface WorkSpacesService {
    public List<WorkSpaceE> getAllWorkspaces(WorkspacesDto workspacesDto) throws Exception;
    public WorkSpaceE SaveWorkspace(WorkspacesDto workspacesDto) throws Exception;
    public Object DeleteWorkspace(WorkspacesDto workspacesDto) throws Exception;

}
