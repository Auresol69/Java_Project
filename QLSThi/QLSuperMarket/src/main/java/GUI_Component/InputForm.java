package GUI_Component;

import javax.swing.*;
import java.awt.*;

public class InputForm extends JLabel {
    private JTextField inputField; // Trường nhập liệu thực sự
    private String labelText; // Văn bản nhãn
    private static final int DEFAULT_WIDTH = 200; // Chiều rộng mặc định

    // Constructor chỉ nhận labelText, mặc định là username
    public InputForm(String labelText) {
        this(labelText, null); // Gọi constructor khác với password = null
    }

    // Constructor nhận labelText và password
    public InputForm(String labelText, String password) {
        this.labelText = labelText;

        // Thiết lập JLabel làm nhãn
        setText(labelText + ": ");
        setFont(new Font("Arial", Font.PLAIN, 14));

        // Kiểm tra password để quyết định loại trường nhập liệu
        if (password != null && !password.isEmpty()) {
            inputField = new JPasswordField();
            ((JPasswordField) inputField).setEchoChar('*'); // Ẩn ký tự cho password
        } else {
            inputField = new JTextField();
        }

        // Tùy chỉnh giao diện cho inputField với chiều rộng mặc định
        inputField.setPreferredSize(new Dimension(DEFAULT_WIDTH, 30));
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Thêm placeholder
        inputField.setForeground(Color.GRAY);
        inputField.setText(labelText);
        inputField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (inputField.getText().equals(labelText)) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText(labelText);
                }
            }
        });

        // Sử dụng layout để bố trí nhãn và trường nhập liệu
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(this); // Thêm chính JLabel (nhãn)
        add(inputField); // Thêm trường nhập liệu
    }

    // Lấy giá trị người dùng nhập
    public String getInputValue() {
        String value = inputField.getText();
        return value.equals(labelText) || value.isEmpty() ? "" : value;
    }

    // Kiểm tra xem trường có rỗng không
    public boolean isEmpty() {
        return getInputValue().isEmpty();
    }

    // Trả về trường nhập liệu để tùy chỉnh thêm nếu cần
    public JTextField getInputField() {
        return inputField;
    }
    public String getPass() {
        if (inputField instanceof JPasswordField) {
            return new String(((JPasswordField) inputField).getPassword());
        }
        return null;
    }
    // Ví dụ sử dụng
    public static void main(String[] args) {
        JFrame frame = new JFrame("Input Form with JLabel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setSize(300, 200);

        // Tạo trường username và password
        InputForm usernameForm = new InputForm("Username"); // Username
        InputForm passwordForm = new InputForm("Password", "abc"); // Password

        frame.add(usernameForm);
        frame.add(passwordForm);

        // Nút kiểm tra giá trị
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String username = usernameForm.getInputValue();
            String password = passwordForm.getInputValue();
            JOptionPane.showMessageDialog(frame, 
                "Username: " + username + "\nPassword: " + password);
        });
        frame.add(submitButton);

        frame.setVisible(true);
    }
}