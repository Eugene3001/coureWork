package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.onu.autoChecking.dao.entities.AppRole;
import ua.edu.onu.autoChecking.dao.entities.AppUser;
import ua.edu.onu.autoChecking.dao.entities.UserRole;
import ua.edu.onu.autoChecking.service.AppRoleService;
import ua.edu.onu.autoChecking.service.AppUserService;
import ua.edu.onu.autoChecking.service.UserDetailsServiceImpl;
import ua.edu.onu.autoChecking.utils.EncryptedPasswordUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    private final AppUserService appUserService;
    private UserDetailsServiceImpl userDetailsService;
    private AppRoleService appRoleService;

    public UserController(AppUserService appUserService, UserDetailsServiceImpl userDetailsService, AppRoleService appRoleService) {
        this.appUserService = appUserService;
        this.userDetailsService = userDetailsService;
        this.appRoleService = appRoleService;
    }

    @GetMapping("/users/main")
    public String list(Model model) {
        List<AppUser> appUsers = appUserService.list();
        List<UserDetails> userDetails = new LinkedList<>();
        appUsers.forEach(appUser -> userDetails.add(userDetailsService.loadUserByUsername(appUser.getUserName())));

        model.addAttribute("users", userDetails);
        return "users/users";
    }

    @GetMapping("/users/create")
    public String createPage(Model model) {
        AppUser appUser = new AppUser();

        model.addAttribute("user", appUser);
        return "users/users-create";
    }

    @PostMapping("/user/create")
    public String create(@ModelAttribute AppUser appUser, Model model) {
        String enc = EncryptedPasswordUtils.encryptPassword(appUser.getEncryptedPassword());
        appUser.setEncryptedPassword(enc);
        AppUser user = appUserService.create(appUser);
        log.info("CREATE one user {}", user);
        return "redirect:/users/main";
    }
}
