package org.aknife.business.map.swing;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.map.model.Location;
import org.aknife.business.map.packet.CM_MoveLocation;
import org.aknife.business.user.model.User;
import org.aknife.message.transmitter.PacketTransmitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
    private JLabel errorLabel = new JLabel("");

    /**
     * 用户错误信息显示
     */
    private JLabel messageLabel = new JLabel("");

    private String message;

    // 用户信息

    /**
     * 用户对象
     */
    private User user;

    /**
     * 用户角色要去的位置
     */
    private Location toLocation = new Location(10, 10, 0);

    /**
     * 该地图中的所有角色
     */
    private HashMap<Integer, UserCharacter> characters = new HashMap<>();


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
        switchMapButton.setBounds(135, 110, 200, 40);
        switchMapButton.addActionListener(new ActionListener() {
            private CM_SwitchMap packet = new CM_SwitchMap(0,1);
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInputDateFormat();
                PacketTransmitter.writePacket(packet);

            }
        });
        panel.add(switchMapButton);

        /**
         * 操作按钮
         */
        JButton moveButton = new JButton("移动到铁匠处");
        moveButton.setBounds(135, 140, 200, 40);
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInputDateFormat();
                toLocation = new Location(25,25,0);
                System.out.println(characters);
                System.out.println(user.getCharacterId());
                System.out.println(characters.get(user.getCharacterId()));
                CM_MoveLocation packet = new CM_MoveLocation(user.getCharacterId(), characters.get(user.getCharacterId()).getLocation(), toLocation);
                PacketTransmitter.writePacket(packet);
            }
        });
        panel.add(moveButton);

        /**
         * 显示错误信息
         */
        errorLabel.setBounds(50,140,300,25);
        panel.add(errorLabel);

        messageLabel.setBounds(300, 20, 400, 300);
        panel.add(messageLabel);

        /**
         * 显示地图图片
         */
        image.setBounds(160,160, 800,600);
        panel.add(image);
    }

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

    public void setUser(User user) {
        this.user = user;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public HashMap<Integer, UserCharacter> getCharacters() {
        return characters;
    }

    public String getGameMapString(){
        return mapText.getText();
    }

    public void setMessage(String message){
        this.message = message;
        messageLabel.setText(message);
    }

    public void addMessage(String msg){
        message += "\r\n"+msg;
        messageLabel.setText(message);
    }

    public boolean checkInputDateFormat(){
        return false;
    }
}
