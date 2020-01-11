package cn.zhengyua.jpademo.param;

import lombok.Data;

import javax.persistence.Entity;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1013:50
 */

@Entity
@Data
public class UserDetailParam {
    private String userId;
    private Integer minAge;
    private Integer maxAge;
    private String realName;
    private String introduction;
    private String city;
}
