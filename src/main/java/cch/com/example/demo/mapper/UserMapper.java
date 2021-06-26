package cch.com.example.demo.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cch.com.example.demo.provider.UserProvider;
import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.UserResponseVO;

@Mapper
public interface UserMapper {
    @SelectProvider(type = UserProvider.class, method = "addUser")
    public UserResponseVO add(UserRequestVO user);
    
    @DeleteProvider(type = UserProvider.class, method = "delete")
    public Integer delete(@Param("id") String id);
    
    @SelectProvider(type = UserProvider.class, method = "update")
    public UserResponseVO update(UserRequestVO user);
    
    // List<UserResponseVO> get(@Param("id") String id);
    @SelectProvider(type = UserProvider.class, method = "getById")
    public UserResponseVO getById(@Param("id") String id);

    @SelectProvider(type = UserProvider.class, method = "isExistUser")
    public Boolean isExistUser(@Param("id") String id);

}
