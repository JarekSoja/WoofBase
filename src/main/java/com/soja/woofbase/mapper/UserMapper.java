package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.UserDtoIn;
import com.soja.woofbase.domain.dto.outbound.UserDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDtoIn userDtoIn) {
        return new User(
                userDtoIn.getEmailAddress(),
                userDtoIn.getMobileNumber(),
                userDtoIn.getDogsOwned(),
                userDtoIn.getAlerts()
        );
    }

    public UserDtoOut mapToUserDtoOut(User user) {
        return new UserDtoOut(
                user.getEmailAddress(),
                user.getMobileNumber(),
                user.getDogsOwned(),
                user.getAlerts()
        );
    }

    public List<UserDtoOut> mapToUserDtoOutList(List<User> users) {
        return users.stream()
                .map(u -> mapToUserDtoOut(u))
                .collect(Collectors.toList());
    }
}
