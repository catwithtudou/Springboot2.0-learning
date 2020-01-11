package cn.zhengyua.druiddemo.mapper.two;

import cn.zhengyua.druiddemo.enums.UserSexEnum;
import cn.zhengyua.druiddemo.mapper.UserSql;
import cn.zhengyua.druiddemo.model.User;
import cn.zhengyua.druiddemo.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserTwoMapper {
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> getAll();


    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(Long id);

    @SelectProvider(type = UserSql.class,method = "getList")
    List<User> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class,method = "getCount")
    int getCount(UserParam userParam);


    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);


}
