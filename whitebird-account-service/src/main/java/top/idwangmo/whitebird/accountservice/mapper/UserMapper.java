package top.idwangmo.whitebird.accountservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.idwangmo.whitebird.accountservice.entity.User;
import top.idwangmo.whitebird.accountservice.model.request.UserRequest;
import top.idwangmo.whitebird.accountservice.model.response.UserResponse;

/**
 * user mapper.
 *
 * @author idwangmo
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * 转换为实体类.
     *
     * @param userRequest 用户请求
     * @return 实体类.
     */
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest userRequest);

    /**
     * 转换为响应类。
     *
     * @param user 用户实体
     * @return 用户响应
     */
    UserResponse toResponse(User user);
}
