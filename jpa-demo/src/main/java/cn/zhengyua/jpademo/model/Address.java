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
 * @date 2020/1/1013:45
 */


@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long userId;
    private String province;
    private String city;
    private String street;
}
