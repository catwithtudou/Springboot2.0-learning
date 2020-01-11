package cn.zhengyua.swaggerdemo.repository;

import cn.zhengyua.swaggerdemo.model.Message;

import java.util.List;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/621:07
 */


public interface MessageRepository {


    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    Message updateText(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);

}
