package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.WorkSpaceDto;
import codeclowns.planny.planny.data.entity.WorkSpaceE;
import codeclowns.planny.planny.repository.WorkSpaceRepository;
import codeclowns.planny.planny.security.service.AuthService;
import codeclowns.planny.planny.service.WorkSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkSpaceServiceImpl implements WorkSpaceService {
     private  final WorkSpaceRepository workSpaceRepository;
     private final AuthService authService;

    @Override
    public int saveWorkSpace(WorkSpaceDto workSpaceDto) throws Exception {
        int rowEffected = 0;
        try {

            // Convert DTO to Entity
            WorkSpaceE workSpace = new WorkSpaceE();
            workSpace.setWorkspaceName(workSpaceDto.getWorkspaceName());
            workSpace.setShortName(workSpaceDto.getShortName());
            workSpace.setWebsite(workSpaceDto.getWebsite());
            workSpace.setDescription(workSpaceDto.getDescription());
            workSpaceRepository.save(workSpace);
            rowEffected = 1;

        } catch (Exception e) {
            System.err.println("An error occurred while saving the workspace: " + e.getMessage());
            throw new Exception("Failed to save workspace", e);
        }
        return rowEffected;
    }

    @Override
    public List<WorkSpaceDto> getWorkSpace(WorkSpaceDto workSpaceDto) throws Exception {
        List<WorkSpaceE> workSpaces = workSpaceRepository.findTop3ByOrderByLastViewedDesc();
        return workSpaces.stream().map(workSpaceDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkSpaceE> getAllByUser(int user_Id) throws Exception {
        var listWorkSpace = workSpaceRepository.getAllByUser(authService.getCurrentUser().getAccountId());
        return Objects.nonNull(listWorkSpace) ? listWorkSpace : null;
    }

}