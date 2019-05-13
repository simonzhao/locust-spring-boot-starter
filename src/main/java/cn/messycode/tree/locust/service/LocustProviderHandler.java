package cn.messycode.tree.locust.service;

import cn.messycode.tree.locust.api.message.LocustMessage;
import cn.messycode.tree.locust.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author simon.zhao
 */
@Slf4j
public class LocustProviderHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof LocustMessage.RequestMsg) {
            LocustMessage.RequestMsg requestMsg = (LocustMessage.RequestMsg) msg;

            log.info("sessionNo:[{}] serviceName:[{}] methodName:[{}]"
                    , requestMsg.getSessionNo(), requestMsg.getServiceName(), requestMsg.getMethodName());

            Object object = SpringContextUtil.getBean("lilyServiceImpl");
            log.info("Bean:[{}]", object.getClass().getName());

            Method method = ReflectionUtils.findMethod(object.getClass(), "getId");
            log.info("method:[{}]", method.getName());

            Object o = ReflectionUtils.invokeMethod(method, object);
            log.info("result:[{}]", o);


            ctx.close();
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
