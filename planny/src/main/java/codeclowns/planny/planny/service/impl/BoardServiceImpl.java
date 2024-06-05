package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.BoardDto;
import codeclowns.planny.planny.data.entity.BoardE;
import codeclowns.planny.planny.repository.BoardRepository;
import codeclowns.planny.planny.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

   final BoardRepository boardRepository;
    @Override
    public List<BoardE> doGetBoardByWorkSpace(Integer workSpace_Id) throws Exception {
        var listResultEntity=boardRepository.findBoardByWorkSpace(workSpace_Id);
        if(Objects.nonNull(listResultEntity)){
            return listResultEntity;
        }
        return null;
    }

    @Override
    public List<BoardE> findBoardWithMembersInWorkspace(Integer workspaceId) throws Exception {
        var listResultEntity=boardRepository.findBoardWithMembersInWorkspace(workspaceId);
        if(Objects.nonNull(listResultEntity)){
            return listResultEntity;
        }
        return null;
    }
}
