package codeclowns.planny.planny.data.dto;

import codeclowns.planny.planny.data.entity.Collaborator;
import codeclowns.planny.planny.data.entity.UserE;
import codeclowns.planny.planny.data.entity.WorkSpaceE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
public class WorkSpaceDto {
    private Integer workspaceId;
    private String workspaceName;
    private String shortName;
    private String website;
    private String description;
    private Boolean isEnabled = true;

    public WorkSpaceDto convertToDto(WorkSpaceE workSpaceE) {
        WorkSpaceDto dto = new WorkSpaceDto();
        dto.setWorkspaceId(workSpaceE.getWorkspaceId());
        dto.setWorkspaceName(workSpaceE.getWorkspaceName());
        dto.setShortName(workSpaceE.getShortName());
        dto.setWebsite(workSpaceE.getWebsite());
        dto.setDescription(workSpaceE.getDescription());
        dto.setIsEnabled(workSpaceE.getIsEnabled());
        return dto;
    }
    private WorkSpaceE convertToEntity(WorkspacesDto dto) {
        return WorkSpaceE.builder()
                .workspaceName(dto.getWorkspaceName())
                .shortName(dto.getShortName())
                .website(dto.getWebsite())
                .description(dto.getDescription())
                .isEnabled(dto.isEnabled())
                .build();
    }
}
