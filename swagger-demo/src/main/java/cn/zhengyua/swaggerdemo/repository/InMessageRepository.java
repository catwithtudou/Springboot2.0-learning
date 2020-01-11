package cn.zhengyua.swaggerdemo.repository;

import cn.zhengyua.swaggerdemo.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/621:09
 */

@Service("messageRepository")
public class InMessageRepository implements MessageRepository {


    //原子操作消息ID
    private static AtomicLong counter = new AtomicLong();
    //使用类变量保存消息
    private final ConcurrentHashMap<Long,Message> message = new ConcurrentHashMap<>();




    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>(this.message.values());
        return messages;
    }

    @Override
    public Message save(Message message) {
        Long id =message.getId();
        if(id == null){
            id = counter.incrementAndGet();
            message.setId(id);
        }
        //若Message存在则覆盖
        this.message.put(id,message);
        return message;
    }

    @Override
    public Message update(Message message) {
        this.message.put(message.getId(),message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message reMessage = this.message.get(message.getId());
        reMessage.setText(message.getText());
        this.message.put(reMessage.getId(),reMessage);
        return reMessage;
    }

    @Override
    public Message findMessage(Long id) {
        if(!this.message.containsKey(id)){
            return new Message();
        }
        return this.message.get(id);
    }

    @Override
    public void deleteMessage(Long id) {
        this.message.remove(id);
    }
}
