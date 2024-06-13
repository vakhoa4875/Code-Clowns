package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.WorkspacesDto;
import codeclowns.planny.planny.data.entity.WorkSpaceE;


import java.util.List;


public interface WorkSpacesService {
    public List<WorkSpaceE> getAllEnableWorkspaces(WorkspacesDto workspacesDto) throws Exception;
    public List<WorkSpaceE> getAllWorkspaces(WorkspacesDto workspacesDto) throws Exception;
    public WorkSpaceE saveWorkspace(WorkspacesDto workspacesDto) throws Exception;
    public Object deleteWorkspace(WorkspacesDto workspacesDto) throws Exception;

}
