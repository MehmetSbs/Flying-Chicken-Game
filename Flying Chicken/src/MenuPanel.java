package src;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;

public class MenuPanel extends JPanel {

    private int screenWidth = 900;
    private int screenHeight = 700;
    private int gap = 15;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton restart;

    int width = 40;
    int height = 40;

    int firstButtonX = screenWidth / 8 * 3;

    ImageIcon level1Tex;
    ImageIcon level2Tex;
    ImageIcon level3Tex;
    ImageIcon level1PressedTex;
    ImageIcon level2PressedTex;
    ImageIcon level3PressedTex;
    ImageIcon level2LockedTex;
    ImageIcon level3LockedTex;
    ImageIcon restartTex;
    //JButton exit;



    public MenuPanel(Listener smpListener){


        setLayout(null);
        level1Tex =   new ImageIcon(this.getClass().getResource("../assets/1.png"));
        level2Tex =   new ImageIcon(this.getClass().getResource("../assets/2.png"));
        level3Tex =   new ImageIcon(this.getClass().getResource("../assets/3.png"));
        level1PressedTex =   new ImageIcon(this.getClass().getResource("../assets/1P.png"));
        level2PressedTex =   new ImageIcon(this.getClass().getResource("../assets/2P.png"));
        level3PressedTex =   new ImageIcon(this.getClass().getResource("../assets/3P.png"));
        level2LockedTex =   new ImageIcon(this.getClass().getResource("../assets/2L.png"));
        level3LockedTex=   new ImageIcon(this.getClass().getResource("../assets/3L.png"));
        restartTex =   new ImageIcon(this.getClass().getResource("../assets/restart.png"));
        

        level1 = new JButton();
        level1.setActionCommand("level 1");
        level1.setIcon(level1Tex);
        level1.setBounds(firstButtonX, 5, width, height);
        level1.setBorderPainted(false); 
        level1.setContentAreaFilled(false); 
        level1.setFocusPainted(false); 
        level1.setOpaque(false);
        level1.addActionListener(smpListener);
        add(level1);

        level2 = new JButton();
        level2.setActionCommand("level 2");
        level2.setIcon(level2LockedTex);
        level2.setBounds(firstButtonX + width + gap, 5, width, height);
        level2.setBorderPainted(false); 
        level2.setContentAreaFilled(false); 
        level2.setFocusPainted(false); 
        level2.setOpaque(false);
        level2.addActionListener(smpListener);
        add(level2);

        level3 = new JButton();
        level3.setActionCommand("level 3");
        level3.setIcon(level3LockedTex);
        level3.setBounds(firstButtonX + (width + gap)*2, 5, width, height);
        level3.setBorderPainted(false); 
        level3.setContentAreaFilled(false); 
        level3.setFocusPainted(false); 
        level3.setOpaque(false);
        level3.addActionListener(smpListener);
        add(level3);

        restart = new JButton();
        restart.setActionCommand("restart");
        restart.setIcon(restartTex);
        restart.setBounds(firstButtonX + (width + gap)*3, 5, width, height);
        restart.setBorderPainted(false); 
        restart.setContentAreaFilled(false); 
        restart.setFocusPainted(false); 
        restart.setOpaque(false);
        restart.addActionListener(smpListener);
        add(restart);
        /*exit = new JButton("exit");
        exit.addActionListener(smpListener);
        add(exit);*/

        level1.setFocusable(false);
        level2.setFocusable(false);
        level3.setFocusable(false);
        restart.setFocusable(false);
        //exit.setFocusable(false);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight-650));
		//this.setBackground(new Color(117,0,156));
		this.setDoubleBuffered(true);
    }

    public void changeIcon(String ex,String status, boolean l2Locked , boolean l3Locked){

        if (l2Locked == false && status != "level2") {
            level2.setIcon(level2Tex);
        }
        if (l3Locked == false && status != "level3") {
            level3.setIcon(level3Tex);
        }
        if (status != "level1") {
            level1.setIcon(level1Tex);
        }
        
        if (status == "level1") {
            
            level1.setIcon(level1PressedTex);

        }else if (status == "level2") {

            level2.setIcon(level2PressedTex);

        }else if (status == "level3") {

            level3.setIcon(level3PressedTex);
        }
    }

    public void paintComponent(Graphics g) {
    
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    
    }

}
