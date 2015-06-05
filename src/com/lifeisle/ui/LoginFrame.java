package com.lifeisle.ui;

import com.google.gson.Gson;
import com.lifeisle.Utils.PrintRunnable;
import com.lifeisle.Utils.QueryString;
import com.lifeisle.Utils.UserInfo;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * @author Jekton Luo
 * @version 0.01 6/4/2015.
 */
public class LoginFrame extends JFrame {

//    private static final String signUpUrl = "http://shenghuodao.gotoip2.com/work/main.php";
    private static final String signUpUrl = "http://www.baidu.com";
    private static final String loginUrl = "http://shenghuodao.gotoip2.com/work/main.php";


    private JTextField userNameField;
    private JPasswordField passwordField;
    private JCheckBox rememberPasswordCheckBox;

    private JButton loginButton;
    private JButton signUpButton;


    private UserInfo userInfo;
    private Gson gson;



    public LoginFrame() {
        gson = new Gson();

        initView();
        addEventListeners();

        try {
            restoreUserInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initView() {

        JPanel content = new JPanel(new GridLayout(4, 2));

        content.add(new JLabel("用户名："));
        userNameField = new JTextField();
        content.add(userNameField);

        content.add(new JLabel("密码："));
        passwordField = new JPasswordField();
        content.add(passwordField);

        rememberPasswordCheckBox = new JCheckBox("记住密码");
        rememberPasswordCheckBox.setSelected(true);
        content.add(rememberPasswordCheckBox);
        content.add(new JLabel());

        loginButton = new JButton("登陆");
        content.add(loginButton);

        signUpButton = new JButton("注册");
        content.add(signUpButton);

        add(content, BorderLayout.CENTER);
    }

    private void addEventListeners() {

        loginButton.addActionListener(e -> postLoginInfo());

        signUpButton.addActionListener(e -> {
            setVisible(false);
            BrowserLauncher.launchBrowser(signUpUrl);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

    }

    private int postLoginInfo() {
        String userName = userNameField.getText();
        char[] password = passwordField.getPassword();

        try {
            QueryString query = new QueryString();
            query.add("user_name", userName);
            query.add("password", new String(password));
            query.add("sys", "user");
            query.add("ctrl", "user");
            query.add("action", "login");

            URL url = new URL(loginUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
                writer.write(query.getQueryString());
                writer.flush();
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                CharBuffer buffer = CharBuffer.allocate(1024);
                reader.read(buffer);

                JSONObject json = new JSONObject(new String(buffer.array()));
                switch (json.getInt("status")) {
                    case 0: // success
                        userInfo.setUserID(json.getInt("user_id"));
                        Thread printThread = new Thread(new PrintRunnable("E:/test/test/"));
                        printThread.setDaemon(true);
                        printThread.start();
                        setVisible(false);
//                        BrowserLauncher.launchBrowser("http://shenghuodao.gotoip2.com/work/main.php?user_name=jekton&password=helloworld&ctrl=user&sys=user&action=login");
                        BrowserLauncher.launchBrowser("http://www.baidu.com");
                        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                        break;
                    case 1:
                        // TODO goto a url and let the user to complete his/her info
                        break;
                    case 2:
                        // TODO password error
                        break;
                    case 3:
                        // TODO username error
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            if (rememberPasswordCheckBox.isSelected()) {
                saveUserInfo(userName, password);
            } else {
                saveUserInfo(userName, new char[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 0;
    }



    private void saveUserInfo(String username, char[] password) throws IOException {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }

        userInfo.setUsername(username);
        userInfo.setPassword(password);

        FileWriter writer = new FileWriter("./config");
        writer.write(gson.toJson(userInfo));
        writer.close();
    }

    private void restoreUserInfo() throws IOException {
        File file = new File("./config");
        if (!file.exists()) return;

        Scanner scanner = new Scanner(file);

        userInfo = gson.fromJson(scanner.nextLine(), UserInfo.class);

        System.out.println(userInfo);

        userNameField.setText(userInfo.getUsername());
        passwordField.setText(new String(userInfo.getPassword()));
    }








    public static void main(String[] args) {
        JFrame frame = new LoginFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(320, 160);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
