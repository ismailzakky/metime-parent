package com.cus.metime.uaa.assembler;

import com.cus.metime.uaa.assembler.core.ObjectAssembler;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.domain.builder.UserBuilder;
import com.cus.metime.uaa.service.dto.UserDTO;
import com.cus.metime.uaa.service.dto.builder.UserDTOBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C-US on 10/18/2017.
 */
@Service
public class UserToUserDTOAssembler implements ObjectAssembler<User,UserDTO> {

    @Override
    public User toDomain(UserDTO dtoObject) {
        return null;
    }

    @Override
    public UserDTO toDTO(User domainObject) {
        return new UserDTOBuilder()
            .setLogin(domainObject.getLogin())
            .setId(domainObject.getId())
            .setLogin(domainObject.getLogin())
            .setFirstName(domainObject.getFirstName())
            .setLastModifiedBy(domainObject.getLastName())
            .setEmail(domainObject.getEmail())
            .setImageUrl(domainObject.getImageUrl())
            .setImageUrl(domainObject.getImageUrl())
            .setCreatedBy(null)
            .setCreatedDate(domainObject.getCreatedDate())
            .setLastModifiedDate(domainObject.getLastModifiedDate())
            .setUuid(domainObject.getUuid())
            .createUserDTO();
    }

    @Override
    public List<User> toDomainList(List<UserDTO> dtoObjectList) {
        return null;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> domainObjectList) {
        List<UserDTO> userDTOList = new ArrayList();
        for (User user : domainObjectList){
            userDTOList.add(toDTO(user));
        }
        return userDTOList;
    }
}
