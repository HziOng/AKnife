package org.aknife.constant;

import org.aknife.business.user.packet.CM_UserHeart;
import org.aknife.business.user.packet.CM_UserLogin;
import org.aknife.business.user.packet.CM_UserOffLine;
import org.aknife.business.user.packet.CM_UserRegister;

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

        /**
         * 登录
         */
        CM_USERLOGIN(333, CM_UserLogin.class),
        /**
         * 注册
         */
        CM_USERREGISTER(222, CM_UserRegister.class),

        /**
         * 下线
         */
        CM_USERCLOSECONNECTION(111, CM_UserOffLine.class),

        /**
         * 心跳协议
         */
        CM_USERHEART(000, CM_UserHeart.class);

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
        throw new RuntimeException("不包含该协议中不包含"+clazz);
    }

    public static Class getClassByType(int type){
        return packetClassMap.get(type);
    }

}
