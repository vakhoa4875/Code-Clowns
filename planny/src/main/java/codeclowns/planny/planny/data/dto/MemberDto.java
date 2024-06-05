package codeclowns.planny.planny.data.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MemberDto {

    private Integer boardId;
    private String boardName;
    private String shortName;
    private String slugUrl;
    private String visibility;
    private Boolean isEnable=true;
}
