package cn.zhengyua.jpademo.reposity;

import cn.zhengyua.jpademo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1013:51
 */


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);

    User findByUserNameOrEmail(String username,String email);

    @Transactional
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String username,Long id);

    @Override
    @Transactional
    @Modifying
    @Query("delete from user where id = ?1")
    void deleteById(Long id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u")
    Page<User> findAll(Pageable pageable);

    Page<User> findByNickName(String nickName,Pageable pageable);

    Slice<User> findByNickNameAndEmail(String nickName,String email,Pageable pageable);
}
