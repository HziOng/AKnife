package org.aknife.user.swing;

import io.netty.channel.Channel;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.constant.ProtocolFixedData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 用户登录-图形化界面客户端
 * @ClassName SwingLoginForm
 * @Author HeZiLong
 * @Data 2021/1/11 10:40
 */
public class SwingLoginForm extends JFrame{

    /**
     * 用于实现注册之后直接在登录界面显示用户名
     */
    private String username;

    private String password;

    /**
     * 用于使用Netty和数据进行交互
     */
    private Channel channel;

    public SwingLoginForm(Channel channel){
        // 设置窗体属性
        super("Login Example");
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

        /**
         * 显示用户名
         */
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        if(username != null && !"".equals(username)){
            userLabel.setText(username);
        }
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        /**
         * 显示密码2
         */
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        /**
         * 操作按钮
         */
        JButton loginButton = new JButton("login");
        loginButton.setBounds(135, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInputDateFormat();
                CM_UserLogin packet = new CM_UserLogin(userText.getText(),new String(passwordText.getPassword()));
                Message message = new Message(PacketFixedConsts.getCodeByClass(CM_UserLogin.class), ProtocolFixedData.STATUS_OK, new Date(System.currentTimeMillis()), packet);
                channel.writeAndFlush(message);
            }
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(250, 80, 80, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingLoginForm.this.dispose();
                SwingRegisterForm registerForm = new SwingRegisterForm(channel);
            }
        });
        panel.add(registerButton);

    }

    public boolean checkInputDateFormat(){
        return false;
    }
}
