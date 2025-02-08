package lqc.com.pis.mapper;

import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.request.UserUpdateRequest;
import lqc.com.pis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
