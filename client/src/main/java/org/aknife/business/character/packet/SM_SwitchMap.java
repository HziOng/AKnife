package org.aknife.business.character.packet;


import lombok.Data;
import org.aknife.business.base.packet.Packet;
import org.aknife.business.user.model.UserVO;

import java.util.List;

/**
 * 用户地图切换响应
 * @ClassName CM_SwitchMap
 * @Author HeZiLong
 * @Data 2021/1/18 17:00
 */
@Data
public class SM_SwitchMap extends Packet {

    /**
     * 表示响应是成功还是失败，1表示成功，2表示失败
     */
    private int status;

    /**
     * 要去的地图
     */
    private int toMapId;

    /**
     * 响应结果信息
     */
    private String message;

    /**
     * 要去的地图中已经存在的用户
     */
    private List<UserVO> userVOS;
}
