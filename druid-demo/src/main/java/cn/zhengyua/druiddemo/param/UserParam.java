package cn.zhengyua.druiddemo.param;

import lombok.Data;

/**
 * User表参数
 *
 * @author tudou
 * @date 2020/1/1116:41
 */


@Data
public class UserParam extends PageParam{

    private String userName;

    private String userSex;

}
