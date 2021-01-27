package org.aknife.constant;


import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.character.packet.SM_SwitchMap;

import org.aknife.business.map.packet.*;
import org.aknife.business.user.packet.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议对应代码
 * @ClassName PacketFixedData
 * @Author HeZiLong
 * @Data 2021/1/11 16:49
 */
public class PacketFixedConsts {

    public static ConcurrentHashMap<Integer, Class> packetClassMap = null;

    public static ConcurrentHashMap<Class, Integer> packetCodeMap = null;


    static {
        packetCodeMap = new ConcurrentHashMap<>();
        packetClassMap = new ConcurrentHashMap<>();
        for (Packet packet : Packet.values()){
            packetClassMap.put(packet.value, packet.clazz);
            packetCodeMap.put(packet.clazz, packet.value);
        }
    }


    enum Packet{

        // 以下部分为用户账号操作协议========================================================================

        /**
         * 登录
         */
        CM_USER_LOGIN(333, CM_UserLogin.class),
        /**
         * 注册
         */
        CM_USER_REGISTER(222, CM_UserRegister.class),

        /**
         * 断开连接
         */
        CM_USER_CLOSE_CONNECTION(111, CM_UserOffLine.class),

        /**
         * 心跳协议
         */
        CM_USER_HEART(000, CM_UserHeart.class),

        /**
         * 服务端发送给客户端的心跳协议
         */
        SM_USER_HEART(001, SM_UserHeart.class),

        /**
         * 用户登录响应
         */
        SM_USER_LOGIN(334, SM_UserLogin.class),

        /**
         * 用户注册响应
         */
        SM_USER_REGISTER(224, SM_UserRegister.class),

        // 以下部分为用户角色操作协议========================================================================

        /**
         * 用户切换地图响应
         */
        SM_SWITCH_MAP(400, SM_SwitchMap.class),

        /**
         * 用户切换地图请求
         */
        CM_SWITCH_MAP(401, CM_SwitchMap.class),

        /**
         * 用户移动请求
         */
        CM_MOVE_LOCATION(501, CM_MoveLocation.class),

        /**
         * 用户移动响应
         */
        SM_MOVE_LOCATION(502, SM_MoveLocation.class),

        /**
         * 其他用户进入地图之后通知其他用户的客户端
         */
        SM_OTHER_USER_ENTRY_MAP(503, SM_OtherUserEntryMap.class),

        /**
         * 其他用户离开地图之后通知其他用户的客户端
         */
        SM_OTHER_USER_AWAY_MAP(504,SM_OtherUserAwayMap.class),

        /**
         * 其他用户移动之后通知其他客户端
         */
        SM_OTHER_MOVE_LOCATION(505,SM_OtherMoveLocation .class);




        private final int value;

        private final Class clazz;


        private Packet(int value, Class clazz) {
            this.value = value;
            this.clazz = clazz;
        }
    }

    public static ConcurrentHashMap<Class, Integer> getAllPacketCode(){
        return packetCodeMap;
    }

    public static ConcurrentHashMap<Integer, Class> getAllPacketClass(){return packetClassMap;}

    public static int getCodeByClass(Class clazz){
        if (packetCodeMap.containsKey(clazz)){
            return packetCodeMap.get(clazz);
        }
        throw new RuntimeException("不包含该协议! 不包含"+clazz);
    }

    public static Class getClassByType(int type){
        return packetClassMap.get(type);
    }

}
