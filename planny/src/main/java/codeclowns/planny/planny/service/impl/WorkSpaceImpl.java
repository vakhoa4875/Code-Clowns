package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.WorkSpaceDto;
import codeclowns.planny.planny.repository.WorkSpaceRepository;
import codeclowns.planny.planny.service.WorkSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkSpaceImpl implements WorkSpaceService {
    final WorkSpaceRepository workSpaceRepository;
    @Override
    public int saveWorkSpace(WorkSpaceDto workSpaceDto) throws Exception {
    int rowEffected=0;
    workSpaceDto.setWorkspaceName(workSpaceDto.getWorkspaceName());
    workSpaceRepository.save(workSpaceDto);
        return 0;
    }
}
