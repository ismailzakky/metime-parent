package com.cus.metime.uaa.assembler;

import com.cus.metime.uaa.assembler.core.ObjectAssembler;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.domain.builder.UserBuilder;
import com.cus.metime.uaa.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by C-US on 10/18/2017.
 */
@Service
public class UserToUserDTOAssembler implements ObjectAssembler<User,UserDTO> {

    @Override
    public User toDomain(UserDTO dtoObject) {
        return new UserBuilder().createUser();
    }

    @Override
    public UserDTO toDTO(User domainObject) {
        return null;
    }

    @Override
    public List<User> toDomainList(List<UserDTO> dtoObjectList) {
        return null;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> domainObjectList) {
        return null;
    }
}
