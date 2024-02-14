package it.unibo.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.LevelView;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
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

/**
 * Handles the building of the GUI's level.
 */
public final class LevelViewImpl implements LevelView {

    private final LevelGUI levelGUI;
    private final Controller controller;

    /**
     * Constructor of the LevelViewImpl.
     * @param controller the controller of the game
     */
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
    public void render(final Set<SimpleEntity> entities) {
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

    private static class Win {

        private static final Dimension FRAME_DIMENSION = new Dimension(900, 600);
        private static final int THICKNESS = 5;
        private final Border border = BorderFactory.createEmptyBorder(20, 120, 20, 120);
        private final JFrame frame = new JFrame();
        private final JPanel panel = new JPanel(new GridLayout(3, 1));
        private final JPanel separator = new JPanel();
        private final JLabel label = new JLabel();
        private final JButton button = new JButton();
        private Font font;
        private Font fontButton;

        Win(final Controller controller) {

            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.frame.setResizable(false);
            this.frame.setSize(FRAME_DIMENSION);
            this.frame.setLocationRelativeTo(null);

            try {
            final float fontLabelDim = 130f;
            final float fontButtonDim = 70f;

            //File fontStyle = new File("src\\main\\resources\\it\\unibo\\fonts\\ProtestStrike-Regular.ttf");
            InputStream fontStyle = ClassLoader.getSystemResourceAsStream("./it/unibo/fonts/ProtestStrike-Regular.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontLabelDim)
                    .deriveFont(Font.BOLD);
            //fontStyle = new File("src\\main\\resources\\it\\unibo\\fonts\\Bungee-Regular.ttf");
            fontStyle = ClassLoader.getSystemResourceAsStream("./it/unibo/fonts/Bungee-Regular.ttf");
            fontButton = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontButtonDim)
                    .deriveFont(Font.CENTER_BASELINE)
                    .deriveFont(Font.PLAIN);

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }

            label
            .setText("YOU WON!!");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            //label.setFont(new Font("Dialog", Font.BOLD, SIZE_LABEL_TEXT));
            label.setFont(font);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLACK);
            label.setOpaque(false);

            button.setText("HOME");
            //button.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            button.setFont(fontButton);
            button.setForeground(Color.BLACK);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS, false));
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
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
        private static final int THICKNESS = 5;
        private final Border border = BorderFactory.createEmptyBorder(20, 120, 20, 120);
        private final JFrame frame = new JFrame();
        private final JPanel panel = new JPanel(new GridLayout(3, 1));
        private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        private final JPanel separator = new JPanel();
        private final JLabel label = new JLabel("GAME OVER!!");
        private final JButton home = new JButton("HOME");
        private final JButton retry = new JButton("PLAY");
        private Font font;
        private Font fontButton;

        GameOver(final Controller controller) {

            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.frame.setResizable(false);
            this.frame.setSize(FRAME_DIMENSION);
            this.frame.setLocationRelativeTo(null);

            try {
                final float fontLabelDim = 120f;
                final float fontButtonDim = 80f;

                //File fontStyle = new File("src\\main\\resources\\it\\unibo\\fonts\\ProtestStrike-Regular.ttf");
                InputStream fontStyle = ClassLoader.getSystemResourceAsStream("./it/unibo/fonts/ProtestStrike-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                        .deriveFont(fontLabelDim)
                        .deriveFont(Font.BOLD);
                //fontStyle = new File("src\\main\\resources\\it\\unibo\\fonts\\Bungee-Regular.ttf");
                fontStyle = ClassLoader.getSystemResourceAsStream("./it/unibo/fonts/Bungee-Regular.ttf");
                fontButton = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                        .deriveFont(fontButtonDim)
                        .deriveFont(Font.CENTER_BASELINE)
                        .deriveFont(Font.PLAIN);

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }

            label.setHorizontalAlignment(SwingConstants.CENTER);
            //label.setFont(new Font("Dialog", Font.BOLD, SIZE_LABEL_TEXT));
            label.setFont(font);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLACK);
            label.setOpaque(false);

            //home.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            home.setFont(fontButton);
            home.setForeground(Color.BLACK);
            home.setBackground(Color.WHITE);
            home.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS, false));
            home.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                }
            });

            //retry.setFont(new Font("Dialog", Font.BOLD, SIZE_BUTTON_TEXT));
            retry.setFont(fontButton);
            retry.setForeground(Color.BLACK);
            retry.setBackground(Color.WHITE);
            retry.setBorder(BorderFactory.createLineBorder(Color.WHITE, THICKNESS, false));
            retry.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    frame.setVisible(false);
                    controller.getRunner().run();
                }
            });

            buttonPanel.setBackground(Color.BLACK);
            separator.setBackground(Color.BLACK);

            final int width = 20;
            final int verticalStrut = 40;
            panel.add(label);
            //panel.add(separator);
            panel.add(Box.createVerticalStrut(verticalStrut));
            buttonPanel.add(home);
            final int buttonPanelWidthStrut = width + 30;
            buttonPanel.add(Box.createHorizontalStrut(buttonPanelWidthStrut));
            buttonPanel.add(retry);
            panel.add(buttonPanel);

            panel.setBorder(border);
            panel.setBackground(Color.BLACK);
            frame.getContentPane().add(panel);
            this.frame.setVisible(true);
        }
    }
}
