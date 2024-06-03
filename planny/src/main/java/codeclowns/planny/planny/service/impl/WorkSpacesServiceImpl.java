package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.WorkspacesDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.data.entity.WorkSpaceE;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.repository.WorkSpacesRepository;
import codeclowns.planny.planny.service.WorkSpacesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpacesServiceImpl implements WorkSpacesService {
    private final WorkSpacesRepository workSpacesRepository;
    private final AccountRepository accountRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    public List<WorkSpaceE> getAllEnableWorkspaces(WorkspacesDto workspacesDto) throws Exception {
        AccountE currentAccount = (AccountE) session.getAttribute("currentAccount");
        if (currentAccount == null) {
            throw new IllegalStateException("User not logged in or session expired");
        }
        return workSpacesRepository.findAllByIsEnabledTrueAndUserId(Long.valueOf(currentAccount.getAccountId()));
    }

    @Override
    public List<WorkSpaceE> getAllWorkspaces(WorkspacesDto workspacesDto) {
        return workSpacesRepository.findAll();
    }

    @Override
    public WorkSpaceE saveWorkspace(WorkspacesDto workspacesDto) throws Exception {
        HttpSession session = request.getSession();
        AccountE currentAccount = (AccountE) session.getAttribute("currentAccount");
        if (currentAccount == null) {
            throw new IllegalStateException("User not logged in or session expired");
        } else {
            AccountE managedAccount = accountRepository.findById(currentAccount.getAccountId()).orElse(null);
            if (managedAccount == null) {
                throw new IllegalStateException("User account not found in the database");
            }
            int exists = workSpacesRepository.existsByWorkspaceName(workspacesDto.getWorkspaceName());
            if (exists > 0) {
                WorkSpaceE existingWorkSpace = workSpacesRepository.findByWorkspaceName(workspacesDto.getWorkspaceName());
                existingWorkSpace.setShortName(workspacesDto.getShortName());
                existingWorkSpace.setWebsite(workspacesDto.getWebsite());
                existingWorkSpace.setDescription(workspacesDto.getDescription());
                existingWorkSpace.setEnabled(workspacesDto.isEnabled());
                existingWorkSpace.setUser(managedAccount.getUser());
                return workSpacesRepository.save(existingWorkSpace);
            } else {
                WorkSpaceE newWorkSpace = convertToEntity(workspacesDto);
                newWorkSpace.setUser(managedAccount.getUser());
                return workSpacesRepository.save(newWorkSpace);
            }
        }
    }

    @Override
    public Object deleteWorkspace(WorkspacesDto workspacesDto) throws Exception {
        HttpSession session = request.getSession();
        AccountE currentAccount = (AccountE) session.getAttribute("currentAccount");
        if (currentAccount == null) {
            throw new IllegalStateException("User not logged in or session expired");
        } else {
            AccountE managedAccount = accountRepository.findById(currentAccount.getAccountId()).orElse(null);
            if (managedAccount == null) {
                throw new IllegalStateException("User account not found in the database");
            }
            int exists = workSpacesRepository.existsByWorkspaceName(workspacesDto.getWorkspaceName());
            if (exists > 0) {
                WorkSpaceE existingWorkSpace = workSpacesRepository.findByWorkspaceName(workspacesDto.getWorkspaceName());
                 workSpacesRepository.delete(existingWorkSpace);
            }
        }
        return null;
    }


    private WorkSpaceE convertToEntity(WorkspacesDto dto) {
        return WorkSpaceE.builder()
                .workspaceName(dto.getWorkspaceName())
                .shortName(dto.getShortName())
                .website(dto.getWebsite())
                .description(dto.getDescription())
                .isEnabled(dto.isEnabled())
                .build();
    }
}
