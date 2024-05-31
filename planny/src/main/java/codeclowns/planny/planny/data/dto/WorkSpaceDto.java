package codeclowns.planny.planny.data.dto;

import codeclowns.planny.planny.data.entity.Collaborator;
import codeclowns.planny.planny.data.entity.UserE;
import jakarta.persistence.*;
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
}
