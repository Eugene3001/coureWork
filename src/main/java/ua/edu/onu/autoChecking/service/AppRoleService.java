package ua.edu.onu.autoChecking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.repositories.spec.AppRoleRepository;

import java.util.List;

@Slf4j
@Service
public class AppRoleService {
    private final AppRoleRepository appRoleRepository;

    @Autowired
    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public List<String> getRoles(Long userId) {
        List<String> roles = appRoleRepository.getRoleNames(userId);
        return roles;
    }
}
