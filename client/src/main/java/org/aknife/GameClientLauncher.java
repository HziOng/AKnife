package org.aknife;

/**
 *
 * 游戏Java版客户端启动器
 * @ClassName GameClientLauncher
 * @Author HeZiLong
 * @Data 2021/1/11 10:50
 */
public class GameClientLauncher {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8888;
        new GameClient(host, port).run();
    }

}
