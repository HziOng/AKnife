package org.aknife.constant;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.packet.CM_UserRegister;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议对应代码
 * @ClassName PacketFixedData
 * @Author HeZiLong
 * @Data 2021/1/11 16:49
 */
public class PacketFixedConsts {

    public static ConcurrentHashMap<Class, Integer> packetCodeMap = null;

    static {
        packetCodeMap = new ConcurrentHashMap();
        for (Packet packet : Packet.values()){
            packetCodeMap.put(packet.clazz, packet.value);
        }
    }

    enum Packet{

        /**
         * 登录
         */
        CM_USERLOGIN(CM_UserLogin.class,111),
        /**
         * 注册
         */
        CM_USERREGISTER(CM_UserRegister.class,222);

        private final Class clazz;
        private final int value;

        private Packet(Class clazz, int value) {
            this.clazz = clazz;
            this.value = value;
        }
    }

    public static ConcurrentHashMap<Class, Integer> getAllPacketCode(){
        return packetCodeMap;
    }

    public static int getPacketCodeByClass(Class clazz){
        return packetCodeMap.get(clazz);
    }
}
