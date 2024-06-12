package codeclowns.planny.planny.api;


import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.data.dto.WorkSpaceDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.service.WorkSpaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-public/workspace")
public class WorkSpaceApi {
    final WorkSpaceService workSpaceService;


    @PostMapping("/PostSaveWorkSpace")
    public ResponseObject<?> doPostSaveWorkSpace(@Valid @RequestBody WorkSpaceDto workSpaceDto) {
        ResponseObject resultApi = new ResponseObject();
        try {
            resultApi.setData(workSpaceService.saveWorkSpace(workSpaceDto));
            resultApi.setStatus("success");
        } catch (Exception e) {
            resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage(BasicApiConstant.ERROR.getStatus());
        }

    return resultApi;
    }
    @GetMapping("/recently-viewed")
    public ResponseObject<?> getRecentlyViewedWorkspaces(WorkSpaceDto workSpaceDto) {
        ResponseObject resultApi = new ResponseObject();
        try {
            List<WorkSpaceDto> recentlyViewedWorkspaces = workSpaceService.getWorkSpace(workSpaceDto);
            resultApi.setData(recentlyViewedWorkspaces);
            resultApi.setStatus("success");
            resultApi.setMessage("success");
        } catch (Exception e) {
            resultApi.setStatus("fail");
            resultApi.setMessage("fail");
        }
        return resultApi;
    }
}
