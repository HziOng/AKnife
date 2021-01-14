package org.aknife.business.user.swing;

import io.netty.channel.Channel;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.business.user.packet.CM_UserRegister;
import org.aknife.constant.ProtocolFixedData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 用户注册-图形化界面客户端
 * @ClassName SwingLoginForm
 * @Author HeZiLong
 * @Data 2021/1/11 10:40
 */
public class SwingRegisterForm extends JFrame{

    /**
     * 用于使用Netty和数据进行交互
     */
    private Channel channel;

    public SwingRegisterForm(Channel channel){
        // 设置窗体属性
        super("Register Example");
        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 添加窗体包含控件
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);

        // 设置
        this.channel = channel;

        // 设置可见
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 显示用户名
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 显示密码
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 确认密码
        JLabel confirmPasswordLabel = new JLabel("Confirm:");
        confirmPasswordLabel.setBounds(10,80,80,25);
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordText = new JPasswordField(20);
        confirmPasswordText.setBounds(100,80,165,25);
        panel.add(confirmPasswordText);

        /**
         * 操作按钮
         */
        JButton registerButton = new JButton("register");
        registerButton.setBounds(135, 110, 80, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInputDateFormat();
                CM_UserRegister packet = new CM_UserRegister(userText.getText(),
                        new String(passwordText.getPassword()),
                        new String(confirmPasswordText.getPassword()));
                Message message = new Message(PacketFixedConsts.getCodeByClass(CM_UserRegister.class), ProtocolFixedData.STATUS_OK, new Date(System.currentTimeMillis()), packet);
                channel.writeAndFlush(message);
            }
        });
        panel.add(registerButton);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(250, 110, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingRegisterForm.this.dispose();
                SwingLoginForm loginForm = new SwingLoginForm(channel);
            }
        });
        panel.add(loginButton);

    }

    public boolean checkInputDateFormat(){
        return false;
    }
}
