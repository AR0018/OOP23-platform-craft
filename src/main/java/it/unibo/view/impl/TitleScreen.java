package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.Toolkit;

/**
 * Class to create the TitleScreen when the game starts.
 */
public class TitleScreen extends JPanel {

    private static final String title = "PlatformCraft";
    private static final int MAX_X_DIM = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    private static final int MAX_Y_DIM = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private final int xDim = 1200;
    private final int yDim = 1000;
    final JFrame frame = new JFrame("Prova");

    /**
     * Constructor to build the gui of the TitleScreen.
     */
    public TitleScreen() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 1000));
        frame.setMaximumSize(new Dimension(MAX_X_DIM, MAX_Y_DIM));
        frame.setSize(new Dimension(xDim, yDim));
        frame.setBackground(new Color(100, 120, 10));

        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(xDim, yDim));
        panel.setMinimumSize(new Dimension(1000, 1300));
        panel.setBackground(new Color(100, 120, 10));

        final JLabel label = new JLabel(title);
        label.setFont(new Font("Verdana", 0, 100));
        label.setBackground(new Color(100, 120, 10));
        label.setForeground(new Color(0, 0, 0));

        final JButton play = new JButton("Play");
        play.setPreferredSize(new Dimension(100, 50));
        play.setFont(new Font("Dialog", Font.BOLD, 30));
        play.setBackground(new Color(0, 0, 0));
        play.setForeground(new Color(255, 255, 255));
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.showSaveDialog(null);
            }
        });
        final JButton editor = new JButton("Editor");
        editor.setPreferredSize(new Dimension(100, 50));
        editor.setFont(new Font("Dialog", Font.BOLD, 30));
        editor.setBackground(new Color(0, 0, 0));
        editor.setForeground(new Color(255, 255, 255));

        final JButton quit = new JButton("Quit");
        quit.setPreferredSize(new Dimension(100, 50));
        quit.setFont(new Font("Dialog", Font.BOLD, 30));
        quit.setBackground(new Color(0, 0, 0));
        quit.setForeground(new Color(255, 255, 255));
        quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(frame, "Do you want to quit",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        int posX = frame.getWidth() / 2;
        int posY = frame.getHeight() / 2;
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, posX, 30));
        titlePanel.setBackground(new Color(100, 120, 10));
        titlePanel.add(label);
        /*titlePanel.setBackground(new Color(100, 120, 10));
        JPanel l = new JPanel();
        l.setBackground(new Color(100, 120, 10));*/
        //BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        //panel.setLayout(box);
        panel.setLayout(new GridLayout(3, 1, 0, 20));
        panel.setBorder(new EmptyBorder(new Insets(posY - 300, posX - 100, 400, 280)));
        //panel.setBounds(100, 100, maxXDim, MAX_Y_DIM);
        panel.add(play);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));    
        panel.add(editor);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));    
        panel.add(quit);
        //panel.setLocation(frame.getWidth() / 2, frame.getHeight() / 2);
        //l.add(titlePanel);
        //l.add(panel);
        titlePanel.add(panel);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        //frame.add(label);
        //frame.add(panel, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.CENTER);
    }

    public void setVisible() {
        frame.setVisible(true);
    }
}
