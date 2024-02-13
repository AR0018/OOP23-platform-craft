package it.unibo.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.LevelView;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.Border;
import javax.swing.JLabel;


public class LevelViewImpl implements LevelView{

    private final LevelGUI levelGUI;
    private final Controller controller;

    public LevelViewImpl(final Controller controller) {
        this.controller = controller;
        this.levelGUI = new LevelGUI(controller);
    }

    @Override
    public void show() {
        this.levelGUI.show();
    }

    @Override
    public void hide() {
        this.levelGUI.hide();
    }

    @Override
    public boolean isShown() {
        return this.levelGUI.isShown();
    }

    @Override
    public void render(Set<SimpleEntity> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void displayWin() {
        hide();                 //new Win(controller).display();    display fa hide di LevelView
        new Win(controller);
    }

    @Override
    public void displayGameOver() {
        hide();
        new GameOver(controller);
    }
    
    private static class Win {      //TODO: aggiungere font
        
        private static final Dimension FRAME_DIMENSION = new Dimension(900, 600);
        private static final int SIZE_LABEL_TEXT = 100;
        private static final int SIZE_BUTTON_TEXT = 60;
        private static final int THICKNESS = 5;
        private final Border border = BorderFactory.createEmptyBorder(20, 120, 20, 120);
        private final JFrame frame = new JFrame();
        private final JPanel panel = new JPanel(new GridLayout(3, 1));
        private final JPanel separator = new JPanel();
        private final JLabel label = new JLabel();
        private final JButton button = new JButton();
        
        Win(final Controller controller) {
            
            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.frame.setResizable(false);
            this.frame.setSize(FRAME_DIMENSION);
            this.frame.setLocationRelativeTo(null);
            
            label.setText("YOU WON!!");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Dialog", Font.BOLD, SIZE_LABEL_TEXT));
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLACK);
            label.setOpaque(false);

            button.setText("HOME");
            button.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, THICKNESS, true));
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                }
            });

            separator.setBackground(Color.BLACK);

            panel.add(label);
            panel.add(separator);
            panel.add(button);

            panel.setBorder(border);
            panel.setBackground(Color.BLACK);
            frame.getContentPane().add(panel);
            this.frame.setVisible(true);
        }

    }

    private static class GameOver {

        private static final Dimension FRAME_DIMENSION = new Dimension(1000, 600);
        private static final int SIZE_LABEL_TEXT = 100;
        private static final int SIZE_BUTTON_TEXT = 60;
        private static final int THICKNESS = 5;
        private final Border border = BorderFactory.createEmptyBorder(20, 120, 20, 120);
        private final JFrame frame = new JFrame();
        private final JPanel panel = new JPanel(new GridLayout(3, 1));
        private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        private final JPanel separator = new JPanel();
        private final JLabel label = new JLabel("GAME OVER!!");
        private final JButton home = new JButton("HOME");
        private final JButton retry = new JButton("RE-TRY");
        
        GameOver(final Controller controller) {
            
            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.frame.setResizable(false);
            this.frame.setSize(FRAME_DIMENSION);
            this.frame.setLocationRelativeTo(null);
            
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Dialog", Font.BOLD, SIZE_LABEL_TEXT));
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLACK);
            label.setOpaque(false);

            home.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            home.setForeground(Color.WHITE);
            home.setBackground(Color.BLACK);
            home.setBorder(BorderFactory.createLineBorder(Color.WHITE, THICKNESS, true));
            home.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                }
            });

            retry.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            retry.setForeground(Color.WHITE);
            retry.setBackground(Color.BLACK);
            retry.setBorder(BorderFactory.createLineBorder(Color.WHITE, THICKNESS, true));
            retry.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    controller.getRunner().run();
                }
            });

            buttonPanel.setBackground(Color.BLACK);
            separator.setBackground(Color.BLACK);

            panel.add(label);
            panel.add(separator);
            buttonPanel.add(home);
            buttonPanel.add(Box.createHorizontalStrut(20));
            buttonPanel.add(retry);
            panel.add(buttonPanel);

            panel.setBorder(border);
            panel.setBackground(Color.BLACK);
            frame.getContentPane().add(panel);
            this.frame.setVisible(true);
        }

    }
}
