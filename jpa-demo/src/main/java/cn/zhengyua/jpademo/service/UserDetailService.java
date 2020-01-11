package cn.zhengyua.jpademo.service;

import cn.zhengyua.jpademo.model.UserDetail;
import cn.zhengyua.jpademo.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1014:01
 */


public interface UserDetailService {

    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);

}
