package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.BoardDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.data.entity.BoardE;
import codeclowns.planny.planny.data.entity.WorkSpaceE;
import codeclowns.planny.planny.repository.BoardRepository;
import codeclowns.planny.planny.repository.WorkSpacesRepository;
import codeclowns.planny.planny.service.BoardService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.FileStore;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final WorkSpacesRepository workSpaceRepository;
    private final HttpSession session;
    public List<BoardE> getAllEnabledBoard(BoardDto boardDto) throws Exception {
        AccountE currentAccount = (AccountE) session.getAttribute("currentAccount");
        if (currentAccount == null) {
            throw new IllegalStateException("User not logged in or session expired");
        }
        Long userId = Long.valueOf(currentAccount.getAccountId());
        return boardRepository.findAllByWorkSpaceUserIdAndWorkSpaceIsEnabledTrueAndIsEnabledTrue(userId);
    }
    @Override
    @Transactional
    public BoardE saveBoard(BoardDto boardDto) throws Exception {
        BoardE boardEntity = convertToEntity(boardDto);
        BoardE savedBoard = boardRepository.save(boardEntity);
        return savedBoard;
    }

private BoardE convertToEntity(BoardDto dto) throws Exception {
    Integer workSpaceId = dto.getWorkSpaceId();
    if (workSpaceId == null) {
        throw new Exception("WorkSpaceId is null in BoardDto");
    }
    WorkSpaceE workSpace = workSpaceRepository.findById(workSpaceId)
            .orElseThrow(() -> new Exception("WorkSpace not found with id: " + workSpaceId));
    return BoardE.builder()
            .boardName(dto.getBoardName())
            .shortName(dto.getShortName())
            .slugUrl(dto.getSlugUrl())
            .visibility(dto.getVisibility())
            .isEnabled(dto.isEnabled())
            .workSpace(workSpace)
            .build();
}


}
