import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Scanner;

/**
 * Created by Wenjian on 2016/4/20, 0020.
 */
public class EchoClient {
    private static final String host = "127.0.0.1";//here we use localhost
    private static final int port = 8080;

    public static void main(String[] args){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //the same as in sever, we need bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new HelloClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            //get info from console, and use channel to write
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()) {
                String msg =  scanner.next();
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
