package cn.messycode.tree.locust.service;

import cn.messycode.tree.locust.api.LocustService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class LocustServiceImpl implements LocustService {

    private String config;

    @Override
    public String[] split(String separatorChar) {
        return this.config.split(separatorChar);
    }

    @PostConstruct
    private void init(){
        log.info("[Locust] SERVICE INIT START");
        NettyService nettyService = new NettyService();
        nettyService.start();
        log.info("[Locust] SERVICE START FINISH");
    }
}
