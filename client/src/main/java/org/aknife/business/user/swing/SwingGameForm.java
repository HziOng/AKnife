package org.aknife.business.user.swing;

import io.netty.channel.Channel;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.account.CM_UserLogin;
import org.aknife.business.user.packet.character.CM_SwitchMap;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.message.model.Message;
import org.aknife.message.transmitter.PacketTransmitter;
import org.aknife.resource.ResourceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * 用户游戏界面客户端
 * @ClassName SwingLoginForm
 * @Author HeZiLong
 * @Data 2021/1/11 10:40
 */
public class SwingGameForm extends JFrame{

    /**
     * 界面控件
     * @param channel
     */
    /**
     * 用户名
     */
    private JTextField userText = new JTextField(20);

    /**
     * 所处地图
     */
    private JTextField mapText = new JTextField(20);

    /**
     * 所处位置
     */
    private JTextField locationText = new JTextField(20);

    /**
     * 显示图片
     */
    private JLabel image = new JLabel("");

    /**
     * 用户错误信息显示
     */
    JLabel errorLabel = new JLabel("");


    public void setUserText(String data){
        userText.setText(data);
    }

    public void setMapText(String data){
        mapText.setText(data);
    }

    public void setLocationText(String data){
        locationText.setText(data);
    }

    public void setError(String error){
        errorLabel.setText(error);
    }

    public void setMapImage(String url){
        try {
            Icon icon = new ImageIcon(new URL(url));
            image.setIcon(icon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public SwingGameForm(){
        // 设置窗体属性
        super("Game Example");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 添加窗体包含控件
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);

        // 设置可见
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        /**
         * 显示用户名
         */
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        /**
         * 显示所处地图
         */
        JLabel mapLabel = new JLabel("Map:");
        mapLabel.setBounds(10,50,80,25);
        panel.add(mapLabel);
        mapText.setBounds(100,50,165,25);
        panel.add(mapText);

        /**
         * 显示所处位置
         */
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10,80,80,25);
        panel.add(locationLabel);
        locationText.setBounds(100,80,165,25);
        panel.add(locationText);

        /**
         * 操作按钮
         */
        JButton switchMapButton = new JButton("去恶人谷");
        switchMapButton.setBounds(135, 110, 80, 40);
        switchMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInputDateFormat();
                CM_SwitchMap packet = new CM_SwitchMap(0,1);
                PacketTransmitter.writePacket(packet);
            }
        });
        panel.add(switchMapButton);

        /**
         * 显示错误信息
         */
        errorLabel.setBounds(50,140,300,25);
        panel.add(errorLabel);
        /**
         * 显示地图图片
         */
        image.setBounds(160,160, 800,600);
        panel.add(image);
    }

    public boolean checkInputDateFormat(){
        return false;
    }
}
