package cn.zhengyua.druiddemo.mapper;


import cn.zhengyua.druiddemo.enums.UserSexEnum;
import cn.zhengyua.druiddemo.mapper.one.UserOneMapper;
import cn.zhengyua.druiddemo.mapper.two.UserTwoMapper;
import cn.zhengyua.druiddemo.model.User;
import cn.zhengyua.druiddemo.param.UserParam;
import cn.zhengyua.druiddemo.result.Page;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.List;

/**
 * 测试类
 *
 * @author tudou
 * @date 2020/1/1117:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @Test
    public void testInsert() throws Exception {
        userOneMapper.insert(new User("aaa", "a123456", UserSexEnum.MAN));
        userOneMapper.insert(new User("bbb", "b123456", UserSexEnum.WOMAN));
        userTwoMapper.insert(new User("ccc", "b123456", UserSexEnum.WOMAN));
/*
		Assert.assertEquals(2, userOneMapper.getAll().size());
		Assert.assertEquals(2, userOneMapper.getAll().size());*/
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userTwoMapper.getAll();
        System.out.println(users.toString());
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userOneMapper.getOne(3l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userOneMapper.update(user);
        Assert.assertTrue(("neo".equals(userOneMapper.getOne(3l).getNickName())));
    }


    @Test
    public void testPage() {
        UserParam userParam=new UserParam();
        userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(1);
        List<User> users=userOneMapper.getList(userParam);
        long count=userOneMapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        System.out.println(page);
    }
}
