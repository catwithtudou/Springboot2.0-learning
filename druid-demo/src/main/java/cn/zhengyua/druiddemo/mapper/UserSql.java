package cn.zhengyua.druiddemo.mapper;

import cn.zhengyua.druiddemo.param.UserParam;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Sql语句
 *
 * @author tudou
 * @date 2020/1/1116:43
 */


public class UserSql {


    private static final Logger log = LoggerFactory.getLogger(UserSql.class);


    public String getList(UserParam userParam){
        int currentPage = 1;
        int pageSize = 15;
        StringBuffer sql = new StringBuffer("selct id,userName,passWord,user_sex as userSex,nick_name as nickName");
        sql.append("from users where 1=1");
        if(userParam !=null){
            if(!StringUtils.isNullOrEmpty(userParam.getUserName())){
                sql.append(" and userName = #{userName}");
            }
            if(!StringUtils.isNullOrEmpty(userParam.getUserSex())){
                sql.append(" and user_sex = #{userSex}");
            }
        }
        sql.append(" order by id desc");
        sql.append(" limit "+userParam.getBeginLine() + ","+userParam.getPageSize());
        log.info("getList sql is :" + sql.toString());
        return sql.toString();
    }


    public String getCount(UserParam userParam){
        String sql = new SQL(){{
            SELECT("count(1)");
            FROM("users");
            if(!StringUtils.isNullOrEmpty(userParam.getUserName())){
                WHERE("userName = #{userName}");
            }
            if(!StringUtils.isNullOrEmpty(userParam.getUserSex())){
                WHERE("user_sex = #{userSex}");
            }
        }}.toString();
        log.info("getCount sql is :"+sql);
        return sql;
    }

}
