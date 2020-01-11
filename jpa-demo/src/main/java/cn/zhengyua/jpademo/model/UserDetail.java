package cn.zhengyua.jpademo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1013:49
 */

@Entity
@Data
public class UserDetail {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private Long userId;
    private Integer age;
    private String realName;
    private String status;
    private String hobby;
    private String introduction;
    private String lastLoginIp;

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", age=" + age +
                ", realName='" + realName + '\'' +
                ", status='" + status + '\'' +
                ", hobby='" + hobby + '\'' +
                ", introduction='" + introduction + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
