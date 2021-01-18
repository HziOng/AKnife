package org.aknife.constant;

import org.aknife.business.user.packet.account.*;
import org.aknife.business.user.packet.character.CM_SwitchMap;
import org.aknife.business.user.packet.character.SM_SwitchMap;

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
        CM_USERLOGIN(333, CM_UserLogin.class),
        /**
         * 注册
         */
        CM_USERREGISTER(222, CM_UserRegister.class),

        /**
         * 断开连接
         */
        CM_USERCLOSECONNECTION(111, CM_UserOffLine.class),

        /**
         * 心跳协议
         */
        CM_USERHEART(000, CM_UserHeart.class),

        /**
         * 用户登录响应
         */
        SM_USERLOGIN(334, SM_UserLogin.class),

        /**
         * 用户注册响应
         */
        SM_USERREGISTER(224, SM_UserRegister.class),

        // 以下部分为用户角色操作协议========================================================================

        SM_SWITCHMAP(400, SM_SwitchMap.class),

        CM_SWITCHMAP(401, CM_SwitchMap.class);



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
