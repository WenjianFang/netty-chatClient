import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Wenjian on 2016/4/20, 0020.
 */
public class HelloClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //the same as in server, add handler
        ChannelPipeline pipeline = socketChannel.pipeline();
        //decoder is an inbound handler and encoder is an outbound handler
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        //client handler, read message
        pipeline.addLast("handler", new HelloClientHandler());
    }
}
