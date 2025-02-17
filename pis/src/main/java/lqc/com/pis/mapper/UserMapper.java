package lqc.com.pis.mapper;

import lqc.com.pis.dto.request.auth.RegisterAccountRequest;
import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.auth.RegisterAccountResponse;
import lqc.com.pis.dto.response.user.UserResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    @Mapping(source = "password", target = "hashPassword")
    User toUser(RegisterAccountRequest registerAccountRequest);

    RegisterAccountResponse toRegisterAccountResponse(User user);

    UserUpdateResponse toUserUpdateResponse(User user);

    UserResponse toUserResponse(User user);
}
