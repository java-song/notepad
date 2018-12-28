package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @Auther:yimohuocun
 * @Date: 2018/12/26
 * @Description: GUI
 * @version: 1.0
 */
public class NotepadGUI extends JFrame {

    /**
     *获取文件路径
     */
    private static String path;

    /**
     *路径文本框
     */
    private static JTextField setPath=new JTextField(30);

    /**
     *文本框组件
     */
    private static JTextArea mytext=new JTextArea(20,50);

    /**
     *新建文件按钮
     */
    private static JButton create=new JButton("新建");

    /**
     *保存文件按钮
     */
    private static JButton save=new JButton("保存");

    /**
     *打开文件按钮
     */
    private static JButton open=new JButton("打开");

    /**
     *窗体方法
     */
    public static void Note(){
        /**
         *设置窗体
         */
        JFrame jframe=new JFrame("Notepad");
        jframe.setSize(700,600);
        jframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jframe.setLayout(new GridLayout(2,1));

        /**
         *编辑框
         */
        JPanel jp1=new JPanel();
        mytext.setLineWrap(true);
        jp1.setSize(10,600);
        jp1.add(mytext);

        /**
         *菜单栏
         */
        JPanel jp2=new JPanel();
        jp2.setBackground(Color.gray);
        jp2.setLayout(new FlowLayout());

        jp2.add(create);
        jp2.add(setPath);
        create.addActionListener(new getPath());

        jp2.add(open);
        open.addActionListener(new getFile());

        jp2.add(save);
        save.addActionListener(new Mysave());

        jframe.add(jp2);
        jframe.add(jp1);
        jframe.setVisible(true);
    }

    public static void main(String[] args){
        NotepadGUI ng=new NotepadGUI();
        ng.Note();
    }

    /**
     *功能描述：按钮监听事件
     *@return:  对象
     *auther:yimohuocun
     *@date: 2018-12-26
     */
    static class Mysave implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            File file=new File(path);
            try {
                //创建FileOutputStream对象
                FileWriter out=new FileWriter(file);
                String text=mytext.getText();
                out.write(text);
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     *功能描述：设置文件保存路径
     *@return:  path
     *auther: yimohuocun
     *@date: 2018-12-27
     */
    static class getPath implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            path=setPath.getText();
            System.out.println(path);
        }
    }

    /**
     *功能描述：打开文件事件
     *@return:  file
     *auther: yimohuocun
     *@date: 2018-12-28
     */
    static class getFile implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();             //设置选择器
            chooser.setMultiSelectionEnabled(true);             //设为多选
            int returnVal = chooser.showOpenDialog(open);        //是否打开文件选择框
            System.out.println("returnVal="+returnVal);

            if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型

                path = chooser.getSelectedFile().getAbsolutePath();     //获取绝对路径
                System.out.println(path);
                System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());  //输出相对路径
            }

            /**
             *读取文件
             */
            File openfile=new File(path);
            try {
                FileReader in=new FileReader(openfile);
                char byt[]=new char[1024];
                int len=in.read(byt);   //将字节读入数组
                mytext.setText(new String(byt,0,len));
                in.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
