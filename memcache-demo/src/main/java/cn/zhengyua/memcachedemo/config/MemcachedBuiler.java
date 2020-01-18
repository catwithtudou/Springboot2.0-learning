package cn.zhengyua.memcachedemo.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.ElectionMemcachedSessionLocator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * TODO
 *
 * @author tudou
 * @date 2020/1/1816:02
 */


@Configuration
public class MemcachedBuiler {

    protected static Logger logger = LoggerFactory.getLogger(MemcachedBuiler.class);

    @Resource
    private XMemcachedProperties xMemcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClient(){
        MemcachedClient memcachedClient = null;
        try{
            MemcachedClientBuilder builder = new XMemcachedClientBuilder((AddrUtil.getAddresses(xMemcachedProperties.getServers())));
            //设置集群权重
            // MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcacheConfig.getServers()),new int[]{3,2,1});
            // 开启/关闭failure模式
            builder.setFailureMode(false);
            //单Memcached时启用一致性哈希算法
            builder.setSessionLocator(new KetamaMemcachedSessionLocator());
            //多Memcached时启用选举散列算法
            //builder.setSessionLocator(new ElectionMemcachedSessionLocator());
            builder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
            //操作超时时间
            builder.setOpTimeout(xMemcachedProperties.getOpTimeout());
            //进行数据压缩,大于1KB时进行压缩
            builder.getTranscoder().setCompressionThreshold(1024);
            //使用序列化传输编码
            builder.setTranscoder(new SerializingTranscoder());
            //使用二进制协议
            builder.setCommandFactory(new BinaryCommandFactory());
            memcachedClient = builder.build();
        }catch (IOException e){
            logger.error("init failed",e);
        }
        return memcachedClient;
    }

}
