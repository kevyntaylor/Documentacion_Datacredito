package com.ebs.datacredito.service;

import com.ebs.datacredito.model.User;
import com.ebs.datacredito.repository.IUserRepository;
import com.ebs.datacredito.service.interfaces.IUserService;
import com.ebs.datacredito.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public void create(User user) {
        Optional<User> UserByBarcodeOptional = userRepository.findByEmail(user.getEmail());
        if(UserByBarcodeOptional.isPresent()) {
            throw new IllegalStateException("User email already registered");
        }
        user.setPassword(Crypto.encrypt(user.getPassword()));
        user.setActive(true);
        user.setCreate_date(new Timestamp(System.currentTimeMillis()));
        user.setEdit_date(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id %s does not exist", id)));
        user.setActive(false);
    }

    @Transactional
    @Override
    public void update(Long id, String password) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id %s does not exist", id)));

        if(password != null && !Objects.equals(password, user.getPassword())) {
            user.setEdit_date(new Timestamp(System.currentTimeMillis()));
            user.setPassword(Crypto.encrypt(password));
        }

    }
}
