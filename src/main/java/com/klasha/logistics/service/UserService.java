package com.klasha.logistics.service;

import com.klasha.logistics.common.Crypto;
import com.klasha.logistics.common.State;
import com.klasha.logistics.dto.LoginDto;
import com.klasha.logistics.dto.UserRegistrationDto;
import com.klasha.logistics.model.User;
import com.klasha.logistics.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Value("${encryption.auth.salt}")
    public String salt;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    * Ideally registration & login should be handled by an Identity Management system like AWS Cognito/FusionAuth
    * but for the purposes of this demo I used my custom solution because of time. But ideally this method should
    * return a jwt that is valid for x number minutes
    */
    public Boolean login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if(user == null)
            return false;

        return Crypto.getHashPassword(loginDto.getPassword(), salt).equals(user.getPassword());
    }

    public User register(UserRegistrationDto userRegistrationDto) {
        return userRepository.save(new User(userRegistrationDto.getName(), userRegistrationDto.getEmail(),
                Crypto.getHashPassword(userRegistrationDto.getPassword(), salt),
                userRegistrationDto.getType().toString(), State.Active.toString()));
    }

    public User updateUser(User user, UserRegistrationDto userRegistrationDto) {
        if (StringUtils.isNotBlank(userRegistrationDto.getName()))
            user.setName(userRegistrationDto.getName());

        if(StringUtils.isNotBlank(userRegistrationDto.getPassword()))
            user.setPassword(Crypto.getHashPassword(userRegistrationDto.getPassword(), salt));

        return userRepository.saveAndFlush(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }


}
