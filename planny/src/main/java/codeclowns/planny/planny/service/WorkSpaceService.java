package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.WorkSpaceDto;

public interface WorkSpaceService {
    int saveWorkSpace(WorkSpaceDto workSpaceDto) throws Exception;
}
