import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Publishbar {

    public static void Publishmenu(JMenuItem UserPosts,JMenuItem newUserPosts, JFrame frame) {
        UserPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //管理

            }
        });//实现颁布帖子功能
        newUserPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //管理
                showUserPostsPanel(frame);
            }
        });//实现删除已颁布帖子功能
    }
    public static class CatInfoPanel extends JPanel {
        JTextField catNameField;
        JTextField catIDField;
        JTextArea catTextField;
        JLabel catImageLabel;
        Image catImage;

        public CatInfoPanel() {
            setLayout(new BorderLayout());

            // 创建输入域
            catNameField = new JTextField(15);
            catIDField = new JTextField(15);
            catTextField = new JTextArea(5, 15);
            catImageLabel = new JLabel();

            JButton chooseImageButton = new JButton("选择图片");
            chooseImageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        catImage = imageIcon.getImage();
                        catImageLabel.setIcon(new ImageIcon(catImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                    }
                }
            });

            // 创建提交按钮
            JButton submitButton = new JButton("提交");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ImageText.createInstance(catNameField.getText(), catIDField.getText(), catTextField.getText(), catImage);
                    JOptionPane.showMessageDialog(null, "猫的信息已提交！");
                }
            });

            // 布局
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4, 2));
            inputPanel.add(new JLabel("猫的名字:"));
            inputPanel.add(catNameField);
            inputPanel.add(new JLabel("猫的 ID:"));
            inputPanel.add(catIDField);
            inputPanel.add(new JLabel("描述:"));
            inputPanel.add(new JScrollPane(catTextField));
            inputPanel.add(chooseImageButton);
            inputPanel.add(catImageLabel);

            add(inputPanel, BorderLayout.CENTER);
            add(submitButton, BorderLayout.SOUTH);
        }
        public JPanel getUserPostsJPanel() {
            return this;
        }
    }//实现发布帖子功能
    public static void showUserPostsPanel(JFrame frame) {
        CatInfoPanel catinfopanel = new CatInfoPanel();
        frame.getContentPane().removeAll();
        frame.add(catinfopanel);
        frame.revalidate();
        frame.repaint();
    }//跳转发帖页面

    public static void shownewUserPostsPanel(JFrame frame){
    }

}
