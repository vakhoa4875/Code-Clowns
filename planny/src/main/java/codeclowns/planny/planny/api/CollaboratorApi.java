package codeclowns.planny.planny.api;

import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.data.entity.Collaborator;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.service.CollaboratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api-public/collaborators")
public class CollaboratorApi {

    private final CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorApi(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping("/getInformation")
    public ResponseObject<?> getCollaboratorsByWorkspaceId(@RequestParam Integer workspaceId) {
            ResponseObject resultApi=new ResponseObject();
        try {
             resultApi.setData(collaboratorService.getCollaboratorsByWorkspaceId(workspaceId));
             resultApi.setStatus("success");
            resultApi.setMessage("success");
        }catch (Exception e){
             resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage(BasicApiConstant.ERROR.getStatus());
            log.error("Fail when call api /api-public/collaborator/getInformation",e);
        }
        return resultApi;
    }
     @GetMapping("/searchByUsername")
    public ResponseObject<?> searchCollaboratorsByUsername(@RequestParam String username) {
        ResponseObject resultApi = new ResponseObject();
        try {
            resultApi.setData(collaboratorService.searchCollaboratorsByUsername(username));
            resultApi.setStatus("success");
            resultApi.setMessage("success");
        } catch (Exception e) {
            resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage(BasicApiConstant.ERROR.getStatus());
            log.error("Fail when call api /api-public/collaborators/searchByUsername", e);
        }
        return resultApi;
    }

}
