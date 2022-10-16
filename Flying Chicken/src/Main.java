package src;

import java.awt.BorderLayout;
import javax.swing.JFrame;

class Main {
        public static void main(String[] args) {

                JFrame window = new JFrame("Flying Chicken");
                GamePanel gamePanel = new GamePanel();
                MenuPanel menuPanel = new MenuPanel(gamePanel.listener);
                gamePanel.addMenuPanel(menuPanel);
                window.add(gamePanel,BorderLayout.CENTER);
                window.add(menuPanel,BorderLayout.PAGE_END);
                window.validate();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.pack();
                window.setResizable(false);
                window.setVisible(true);
                gamePanel.startGameThread();
                 
        }


}
