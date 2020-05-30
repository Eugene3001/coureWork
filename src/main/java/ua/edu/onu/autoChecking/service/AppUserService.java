package ua.edu.onu.autoChecking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.AppUser;
import ua.edu.onu.autoChecking.dao.repositories.AppUserRepository;
import ua.edu.onu.autoChecking.exception.NotFoundException;

@Slf4j
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser findByUserName(String userName) {
        AppUser appUser = appUserRepository.findByUserName(userName)
                .orElseThrow(NotFoundException::new);

        return appUser;
    }
}
