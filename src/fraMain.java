import javax.swing.*;

public class fraMain {
    //需要顶部菜单栏
    //带* 有子菜单
    //带# 优先度低或者做不到
    //从左至右依次是，个人中心*，大厅*，发布*
    // 点击用户下拉框才会跳转页面
    //
    public static void UserMenu(JMenuBar menubar, JFrame frame, String userID){

        JMenu menuUser = new JMenu("个人中心");//菜单
        menubar.add(menuUser);

        JMenuItem userInformation = new JMenuItem("个人信息");
        menuUser.add(userInformation);

        JMenuItem usermodify = new JMenuItem("修改信息");
        menuUser.add(usermodify);

        JMenuItem userEXIT = new JMenuItem("退出登录");
        menuUser.add(userEXIT);

        Userbar.Useruse(userInformation,userEXIT,usermodify,frame,userID);
    }//个人中心:查看个人信息*，退出登录查
    public static void HallMenu(JMenuBar menubar, JFrame frame, String userID){
        JMenu menuHall = new JMenu("大厅");
        menubar.add(menuHall);

        JMenuItem catlook = new JMenuItem("大厅");
        menuHall.add(catlook);

        Hallbar.Hallmenu(catlook,frame,userID);
    }//大厅:查阅，筛选#
    public static void PublishMenu(JMenuBar menubar, JFrame frame,String userID){
        JMenu menuPublish = new JMenu("发布");
        menubar.add(menuPublish);

        JMenuItem UserPosts = new JMenuItem("已发布帖子");
        menuPublish.add(UserPosts);

        JMenuItem newUserPosts = new JMenuItem("发布帖子");
        menuPublish.add(newUserPosts);

        Publishbar.Publishmenu(UserPosts,newUserPosts,frame);
    }//发布:查看已发布帖子，发布帖子
    public static void startfraMain( JFrame frame , String userID){
        JMenuBar menubar = new JMenuBar();//菜单栏

        UserMenu(menubar,frame,userID);
        HallMenu(menubar,frame,userID);
        PublishMenu(menubar,frame,userID);

        frame.add(menubar);
        frame.setJMenuBar(menubar);

        frame.revalidate();
        frame.repaint();
    }
}
