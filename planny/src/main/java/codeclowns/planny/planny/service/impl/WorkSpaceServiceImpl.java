package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.constant.common.URLUtils;
import codeclowns.planny.planny.data.dto.WorkSpaceDto;
import codeclowns.planny.planny.data.entity.WorkSpaceE;
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
        int rowEffected = 0;
        try {
            // Validate the input DTO
            if (workSpaceDto == null || workSpaceDto.getWorkspaceName() == null || workSpaceDto.getWorkspaceName().isEmpty()) {
                throw new IllegalArgumentException("Workspace name cannot be null or empty");
            }

            // Convert DTO to Entity
            WorkSpaceE workSpace = new WorkSpaceE();
            workSpace.setWorkspaceName(workSpaceDto.getWorkspaceName());
            workSpace.setShortName(URLUtils.encode(workSpaceDto.getWorkspaceName()));
            // Set other fields as needed

            // Save the entity to the database
            workSpaceRepository.save(workSpace);
            rowEffected = 1; // Assuming save is successful

        } catch (Exception e) {
            // Log the exception (optional)
            System.err.println("An error occurred while saving the workspace: " + e.getMessage());
            throw new Exception("Failed to save workspace", e);
        }

        return rowEffected;
    }
}