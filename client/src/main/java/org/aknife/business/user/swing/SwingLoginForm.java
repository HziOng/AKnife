package org.aknife.business.user.swing;

import io.netty.channel.Channel;
import org.aknife.business.base.service.BaseService;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.CM_UserLogin;
import org.aknife.message.transmitter.PacketTransmitterUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户登录-图形化界面客户端
 * @ClassName SwingLoginForm
 * @Author HeZiLong
 * @Data 2021/1/11 10:40
 */
public class SwingLoginForm extends JFrame{

    private User user;

    /**
     * 用于实现注册之后直接在登录界面显示用户名
     */
    private String username;

    private String password;

    /**
     * 用于使用Netty和数据进行交互
     */
    private Channel channel;


    // 之后是控件
    /**
     * 用户信息显示
     */
    JTextField userText = new JTextField(20);

    /**
     * 用户错误信息显示
     */
    JLabel errorLabel = new JLabel("");

    public void setUsername(String username){
        this.username = username;
        userText.setText(username);
    }

    public void setError(String error){
        errorLabel.setText(error);
    }

    public SwingLoginForm(){
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
        panel.add(userLabel);


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
                PacketTransmitterUtil.writePacket(packet);
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
                BaseService.jFrameStack.pop();
                BaseService.jFrameStack.push(registerForm);
            }
        });
        panel.add(registerButton);

        /**
         * 显示错误信息
         */
        errorLabel.setBounds(50,110,150,25);
        panel.add(errorLabel);
    }

    public boolean checkInputDateFormat(){
        return false;
    }
}
