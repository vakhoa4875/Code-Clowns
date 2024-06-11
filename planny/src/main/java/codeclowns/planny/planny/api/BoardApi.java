package codeclowns.planny.planny.api;

import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.data.dto.BoardDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.exception.CustomException;
import codeclowns.planny.planny.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api-public/board")
public class BoardApi {

    final BoardService boardService;

    @GetMapping("/getInformationBoard")
    public ResponseObject<?> getInformationWorkSpaceByBoard(@RequestParam Integer workspaceId){
        ResponseObject resultApi=new ResponseObject();
        try {
            resultApi.setData(boardService.doGetBoardByWorkSpace(workspaceId));
            resultApi.setStatus("success");
            resultApi.setMessage("success");
        } catch (Exception e){
            resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage(BasicApiConstant.ERROR.getStatus());
            log.error("Fail when call api /api-public/board/getInformationBoard",e);
        }
      return resultApi;
    }

     @GetMapping("/getBoardsByWorkspace")
    public ResponseObject<?> getBoardsByWorkspace(@RequestParam Integer workspaceId){
        ResponseObject resultApi = new ResponseObject();
        try {
            // Gọi phương thức service để lấy danh sách các bảng trong workspace
            resultApi.setData(boardService.findBoardWithMembersInWorkspace(workspaceId));
            resultApi.setStatus("success");
            resultApi.setMessage("Successfully retrieved boards for workspace with id: " + workspaceId);
        } catch (Exception e){
            // Xử lý nếu có lỗi xảy ra
            resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage("Failed to retrieve boards for workspace with id: " + workspaceId);
            log.error("Failed when calling API /api-public/board/getBoardsByWorkspace", e);
        }
        return resultApi;
    }

    @GetMapping("/getBoardBySLugUrl")
    public ResponseObject<?> doGetBoardBySlugUrl(@RequestParam String slugUrl) {
        var resultApi = new ResponseObject<>();
        var board = boardService.getBoardBySlugUrl(slugUrl);
        resultApi.setData(board);
        resultApi.setStatus("success");
        resultApi.setMessage("Get board successfully");
        return resultApi;
    }
}
