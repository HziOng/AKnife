package org.aknife;

/**
 * 游戏服务端启动器
 * @ClassName GameLauncher
 * @Author HeZiLong
 * @Data 2021/1/11 10:15
 */
public class GameServerLauncher {

    public static void main(String[] args) {
        new GameServer(8888).run();
    }
}
