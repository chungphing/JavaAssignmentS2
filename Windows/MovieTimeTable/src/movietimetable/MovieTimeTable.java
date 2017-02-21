/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MovieTimeTable extends JFrame{

    JTabbedPane tab;
    
    MovieTimeTable()
    {
        setSize(1024, 650);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tab=new JTabbedPane();
        tab.addTab("Schedule", new Schedule());
        tab.addTab("Favorite",new Favorite());
        tab.addTab("Now Showing",new NowShowing());
        tab.addTab("Coming Soon",new ComingSoon());
        
        add(tab);       
    }
    
    public static void main(String[] args) {
        MovieTimeTable mv = new MovieTimeTable();
        new MovieTimeTable();
        mv.tab.setSelectedIndex(0);
    }
}
