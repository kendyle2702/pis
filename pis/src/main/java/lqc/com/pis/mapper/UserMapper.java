package lqc.com.pis.mapper;

import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.request.UserUpdateRequest;
import lqc.com.pis.dto.request.auth.RegisterAccountRequest;
import lqc.com.pis.dto.response.auth.RegisterAccountResponse;
import lqc.com.pis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    @Mapping(source = "password", target = "hashPassword")
    User toUser(RegisterAccountRequest registerAccountRequest);

    RegisterAccountResponse toRegisterAccountResponse(User user);
}
