/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietimetable;

import java.awt.Image;
import java.awt.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static movietimetable.Schedule.main;

class Schedule extends JPanel {

    List ls;
    JScrollPane scroll;
    JLabel pic,title;
    JButton btnBuy,btnFavorite;
    JTextArea ta;
    
    Schedule()
    {
        setLayout(null);
        ls=new List();
        ls.setBounds(5,5,400,570);
        ls.setFont(new java.awt.Font("Times New Roman", 0, 24));
        for (int i = 0; i < 50; i++) {
            ls.add("a");
        }
        
        scroll=new JScrollPane();
        scroll.setViewportView(ls);
        
        pic=new JLabel();
        pic.setBounds(440,5,210,230); 
        ImageIcon icon=new ImageIcon("src/img/a.jpg");
        Image i=icon.getImage();
        Image ii=i.getScaledInstance(pic.getWidth(), pic.getHeight(), java.awt.Image.SCALE_SMOOTH);
        Icon ic=new ImageIcon(ii);
        pic.setIcon(ic);
        
        btnBuy=new JButton("Buy");
        btnBuy.setBounds(440,250,100,30);
        
        btnFavorite=new JButton("Favorite");
        btnFavorite.setBounds(550, 250, 100, 30);
        
        title=new JLabel("Title");
        title.setBounds(670, 5, 300, 30);
        title.setFont(new java.awt.Font("Times New Roman", 1, 24));
        
        ta=new JTextArea();
        ta.setBounds(670,70,300,180);
        ta.setFont(new java.awt.Font("Times New Roman",0, 20));

        
        add(ls);
        add(pic);
        add(btnBuy);
        add(btnFavorite);
        add(title);
        add(ta);
    }
    
    public static void main(String args[])
    {
        new Schedule();
    }
}
