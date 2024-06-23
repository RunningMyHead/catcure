import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hallbar {
    public static void Hallmenu(JMenuItem catlook,JFrame frame, String userID) {
        catlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHallPanel(frame,userID);
                //显示大厅
            }
        });
    }//实现大厅功能

    public static JPanel HallPanel(){
        JPanel Panel = new JPanel();

        return Panel;
    }
    public static void showHallPanel(JFrame frame, String userID) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 创建滚动面板
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 添加滚动面板到窗口
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 模拟多篇文章
        for (int i = 0; i < 10; i++) {
            String authorName = "Author " + i;
            String articleText = "Article content for article " + i;
            // 创建文章面板
            JPanel articlePanel = createArticlePanel(authorName, articleText);

            // 添加点击事件
            articlePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 处理点击事件，这里只是简单打印文章信息
                }
            });
            // 将文章面板添加到主面板
            mainPanel.add(articlePanel);
            mainPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        }
        // 刷新布局
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private static JPanel createArticlePanel(String authorName, String articleText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 添加边框

        // 添加作者姓名
        JLabel nameLabel = new JLabel("Author: " + authorName);
        panel.add(nameLabel, BorderLayout.NORTH);

        // 添加文章内容
        JTextArea textArea = new JTextArea(articleText);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        panel.add(textScrollPane, BorderLayout.CENTER);

        // 添加图片（示例：一个简单的图标）
        ImageIcon imageIcon = new ImageIcon("");
        JLabel imageLabel = new JLabel(imageIcon);
        panel.add(imageLabel, BorderLayout.WEST);
        // 设置点击效果
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return panel;
    }


}
