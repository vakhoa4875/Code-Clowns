package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.BoardDto;
import codeclowns.planny.planny.data.entity.BoardE;

import java.util.List;

public interface BoardService {
    List<BoardE> doGetBoardByWorkSpace(Integer workSpace_Id) throws Exception;
    List<BoardE> findBoardWithMembersInWorkspace(Integer workspaceId) throws Exception;
}
