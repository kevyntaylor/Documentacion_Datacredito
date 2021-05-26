package com.ebs.datacredito.service.interfaces;

import com.ebs.datacredito.model.User;

import java.util.List;

public interface IUserService {

    List<User> get();
    void create(User user);
    void delete(Long id);
    void update(Long id, String password);

}
