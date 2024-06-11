package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.entity.BoardE;
import codeclowns.planny.planny.exception.CustomCause;
import codeclowns.planny.planny.exception.CustomException;
import codeclowns.planny.planny.repository.BoardRepository;
import codeclowns.planny.planny.repository.ListRepository;
import codeclowns.planny.planny.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;
    final ListRepository listRepository;

    @Override
    public List<BoardE> doGetBoardByWorkSpace(Integer workSpace_Id) throws Exception {
        var listResultEntity = boardRepository.findBoardByWorkSpace(workSpace_Id);
        if (Objects.nonNull(listResultEntity)) {
            return listResultEntity;
        }
        return null;
    }

    @Override
    public List<Object[]> findBoardWithMembersInWorkspace(Integer workspaceId) throws Exception {
        var listResultEntity = boardRepository.findBoardWithMembersInWorkspace(workspaceId);
        if (Objects.nonNull(listResultEntity)) {
            return listResultEntity;
        }
        return null;
    }

    @SneakyThrows
    @Override
    public BoardE getBoardBySlugUrl(String slugUrl) {
        var board = boardRepository.findBySlugUrl(slugUrl);
        if (board == null) throw new CustomException(CustomCause.BOARD404);
        board.setList(listRepository.findByBoard(slugUrl));
        return board;
    }
}
