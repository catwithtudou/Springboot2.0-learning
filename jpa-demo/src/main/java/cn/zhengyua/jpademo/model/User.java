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
 * @date 2020/1/1013:46
 */

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false,unique = true)
    private String passWord;
    @Column(nullable = false,unique = false)
    private String email;
    @Column(nullable = true,unique = true)
    private String nickName;

    public User(){
    }
    public User(String userName, String passWord, String email, String nickName) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
    }

}
