package com.ebs.datacredito.controller;

import com.ebs.datacredito.dto.UserDTO;
import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.model.User;
import com.ebs.datacredito.service.interfaces.ILogService;
import com.ebs.datacredito.service.interfaces.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(path = "datacredito/api/v1/user")
@Api(value = "users", tags = "Users", description = "User administration")
public class UserController {

    private final IUserService userService;
    private final ILogService logService;

    @Autowired
    public UserController(IUserService userService, ILogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @GetMapping
    @ApiOperation(value = "Get all users", authorizations = {@Authorization(value = "jwtToken")})
    public @ResponseBody
    List<UserDTO> get(HttpServletRequest request) {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userService.get();
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            usersDTO.add(new UserDTO(
                    user.getId(),
                    user.getEmail(),
                    user.getCreate_date(),
                    user.getEdit_date(),
                    user.isActive()));
        }
        logService.create(new Log(String.format("Get all users"), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
        return usersDTO;
    }

    @PostMapping
    @ApiOperation(value = "Create user", authorizations = {@Authorization(value = "jwtToken")})
    public void create(HttpServletRequest request, @RequestBody User user) {
        logService.create(new Log(String.format("Create user [%s]", user.getEmail()), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
        userService.create(user);
    }

    @PutMapping(path = "{idUser}")
    @ApiOperation(value = "Update user", authorizations = {@Authorization(value = "jwtToken")})
    public void update(
            HttpServletRequest request,
            @PathVariable("idUser") Long idUser,
            @RequestParam(required = true) String password
    ) {
        userService.update(idUser, password);
        logService.create(new Log(String.format("Update password for user with id [%s]", idUser), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
    }

    @DeleteMapping(path = "{idUser}")
    @ApiOperation(value = "Delete user", authorizations = {@Authorization(value = "jwtToken")})
    public void delete(
            HttpServletRequest request,
            @PathVariable("idUser") Long id) {
        userService.delete(id);
        logService.create(new Log(String.format("User with id [%s] deleted", id), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
    }

}
