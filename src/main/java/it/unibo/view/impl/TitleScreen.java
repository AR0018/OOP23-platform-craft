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
final public class TitleScreen extends JPanel {

    private static final int MAX_X_DIM = 1600;  //(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    private static final int MAX_Y_DIM = 900;   //(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private static final String TITLE = "PlatformCraft";
    private static final Color BACKGROUND = new Color(100, 120, 10);    //Sfondo comune
    private static final Color BUTTON_BACK = new Color(0, 0, 0);
    private static final Color FOREGROUND = new Color(255, 255, 255);
    //private static final int RED_BUTTON = 100;
    //private static final int GREEN_BUTTON = 120;
    //private static final int BLUE_BUTTON = 10;
    private static final int TITLE_SIZE = 100;
    private static final Dimension BUTTON_SIZE = new Dimension(100, 50); 
    private final int xDim = 1200;
    private final int yDim = 1000;
    private final JFrame frame = new JFrame("Prova");

    /**
     * Constructor to build the gui of the TitleScreen.
     */
    public TitleScreen() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(xDim, yDim));
        frame.setMaximumSize(new Dimension(MAX_X_DIM, MAX_Y_DIM));
        frame.setSize(new Dimension(xDim, yDim));
        frame.setBackground(BACKGROUND);

        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(xDim, yDim));
        //panel.setMinimumSize(new Dimension(1000, 1300));
        panel.setBackground(BACKGROUND);

        final JLabel label = new JLabel(TITLE);
        label.setFont(new Font("Verdana", Font.BOLD, TITLE_SIZE));
        label.setBackground(BACKGROUND);
        label.setForeground(BUTTON_BACK);

        final JButton play = new JButton("Play");
        play.setPreferredSize(BUTTON_SIZE);
        play.setFont(new Font("Dialog", Font.BOLD, 30));
        play.setBackground(BUTTON_BACK);
        play.setForeground(FOREGROUND);
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.showSaveDialog(null);
                file.getSelectedFile();     //TODO: restituire il file ai piani superiori
            }
        });
        final JButton editor = new JButton("Editor");
        editor.setPreferredSize(BUTTON_SIZE);
        editor.setFont(new Font("Dialog", Font.BOLD, 30));
        editor.setBackground(BUTTON_BACK);
        editor.setForeground(FOREGROUND);

        final JButton quit = new JButton("Quit");
        quit.setPreferredSize(BUTTON_SIZE);
        quit.setFont(new Font("Dialog", Font.BOLD, 30));
        quit.setBackground(BUTTON_BACK);
        quit.setForeground(FOREGROUND);
        quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to quit",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        int posX = frame.getWidth() / 2;
        int posY = frame.getHeight() / 2;
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, posX, 30));
        titlePanel.setBackground(BACKGROUND);
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

    /**
     * Sets visible the frame of the TitleScreen.
     */
    public void setVisible() {
        frame.setVisible(true);
    }
}
