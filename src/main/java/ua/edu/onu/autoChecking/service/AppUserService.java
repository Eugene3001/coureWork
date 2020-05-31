package ua.edu.onu.autoChecking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.AppUser;
import ua.edu.onu.autoChecking.dao.repositories.AppRoleRepository;
import ua.edu.onu.autoChecking.dao.repositories.AppUserRepository;
import ua.edu.onu.autoChecking.dto.AppUserDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    private final Function<AppUser, AppUserDto> userToDto = entity -> AppUserDto.builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .enabled(entity.getEnabled())
                .encryptedPassword(entity.getEncryptedPassword())
                .roles(appRoleRepository.getRoleNames(entity.getUserId()))
                .build();

    private final Function<AppUserDto, AppUser> dtoToUser = dto -> AppUser.builder()
            .userId(dto.getUserId())
            .userName(dto.getUserName())
            .enabled(dto.getEnabled())
            .encryptedPassword(dto.getEncryptedPassword())
            .build();

    public AppUser findByUserName(String userName) {
        AppUser appUser = appUserRepository.findByUserName(userName)
                .orElseThrow(NotFoundException::new);

        return appUser;
    }

    public List<AppUser> list() {
        List<AppUser> list = new LinkedList<>();
        appUserRepository.findAll().forEach(appUser -> list.add(appUser));
        return list;
    }

    public AppUser create(AppUser appUser) {

        return appUserRepository.save(appUser);
    }
}
