package codeclowns.planny.planny.api;

import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.data.dto.BoardDto;
import codeclowns.planny.planny.data.entity.BoardE;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-public/board")
@RequiredArgsConstructor
@Slf4j
public class BoardApi {
    private final BoardService boardService;

    @GetMapping("/getEnableAllBoard")
    public ResponseObject<?> doGetEnableAllBoard(BoardDto boardDto) {
        ResponseObject resultApi = new ResponseObject();
        try {
            resultApi.setData(boardService.getAllEnabledBoard(boardDto));
            resultApi.setStatus(BasicApiConstant.SUCCEED.getStatus());
        } catch (Exception e) {
            resultApi.setStatus(BasicApiConstant.ERROR.getStatus());
            resultApi.setMessage(LoginStatus.ERROR.getStateDescription());
            log.error("Failed when calling API: " + e);
        }
        return resultApi;
    }
    @PostMapping("/saveAllBoard")
    public ResponseEntity<ResponseObject> saveAllBoard(@RequestBody BoardDto boardDto) {
        ResponseObject resultApi = new ResponseObject();
        try {
            log.debug("Received BoardDto: {}", boardDto);
            BoardE savedBoard = boardService.saveBoard(boardDto);
            resultApi.setData(savedBoard);
            resultApi.setStatus(BasicApiConstant.SUCCEED.getStatus());
            return ResponseEntity.ok(resultApi);
        } catch (Exception e) {
            resultApi.setStatus(BasicApiConstant.ERROR.getStatus());
            resultApi.setMessage(LoginStatus.ERROR.getStateDescription());
            log.error("Failed when calling API: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultApi);
        }
    }
}
