package codeclowns.planny.planny.data.dto;

import codeclowns.planny.planny.data.entity.WorkSpaceE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private String boardName;
    private String shortName;
    private String slugUrl;
    private String visibility;
    private boolean isEnabled = true;
    private Integer workSpaceId;
}
