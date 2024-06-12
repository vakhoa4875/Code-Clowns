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
    private Integer boardId;
    private String boardName;
    private String shortName;
    private String slugUrl;
    private String visibility;
    private boolean isEnabled = true;
    private Integer workSpaceId;

    public BoardDto convertToDto(BoardE boardE){
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(boardE.getBoardId());
        boardDto.setBoardName(boardE.getBoardName());
        boardDto.setShortName(boardE.getShortName());
        boardDto.setSlugUrl(boardE.getSlugUrl());
        boardDto.setVisibility(boardE.getVisibility());
        boardDto.setIsEnable(boardE.getIsEnable());
        return boardDto;
    }
}
