package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.WorkSpaceDto;

import java.util.List;

public interface WorkSpaceService {
    int saveWorkSpace(WorkSpaceDto workSpaceDto) throws Exception;
    List<WorkSpaceDto> getWorkSpace(WorkSpaceDto workSpaceDto) throws Exception;
}
