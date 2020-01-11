package cn.zhengyua.jpademo.service;

import cn.zhengyua.jpademo.model.UserDetail;
import cn.zhengyua.jpademo.param.UserDetailParam;
import cn.zhengyua.jpademo.reposity.UserDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mysql.cj.util.StringUtils;
import javax.annotation.Resource;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1014:01
 */


public class UserDetailServiceImpl implements UserDetailService{

    @Resource
    private UserDetailRepository userDetailRepository;


    @Override
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable) {

        return userDetailRepository.findAll((root,query,cb)->{
            List<Predicate> predicates= new ArrayList<>();

            if(!StringUtils.isNullOrEmpty(detailParam.getIntroduction())){
                predicates.add(cb.equal(root.get("introduction"),detailParam.getIntroduction()));
            }

            if(!StringUtils.isNullOrEmpty(detailParam.getRealName())){
                predicates.add(cb.like(root.get("realName"),"%"+detailParam.getRealName()+"%"));
            }

            if (detailParam.getMinAge()!=null && detailParam.getMaxAge()!=null) {
                Predicate agePredicate = cb.between(root.get("age"), detailParam.getMinAge(), detailParam.getMaxAge());
                predicates.add(agePredicate);
            }


            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        },pageable);
    }
}
