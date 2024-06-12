package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.entity.BoardE;
import codeclowns.planny.planny.exception.CustomException;

import java.util.List;

public interface BoardService {
    List<BoardE> doGetBoardByWorkSpace(Integer workSpace_Id) throws Exception;

    List<Object[]> findBoardWithMembersInWorkspace(Integer workspaceId) throws Exception;

    BoardE getBoardBySlugUrl(String slugUrl);
}
