package it.unibo.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import it.unibo.controller.api.Controller;
import it.unibo.view.api.View;

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
 * Models the GUI of the GameOver of the game.
 */
public final class GameOverView {

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

    /**
     * Constructor of the GameOverView.
     * @param controller controller of the game
     * @param levelGUI the GUI of the running level
     * @param view the main view
     */
    public GameOverView(final Controller controller, final LevelGUI levelGUI, final View view) {

        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(FRAME_DIMENSION);
        this.frame.setLocationRelativeTo(null);

        this.addingFont();

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(false);

        home.setFont(fontButton);
        home.setForeground(Color.BLACK);
        home.setBackground(Color.WHITE);
        home.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS, false));
        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.setVisible(false);
                levelGUI.hide();
                view.displayStart();
            }
        });

        retry.setFont(fontButton);
        retry.setForeground(Color.BLACK);
        retry.setBackground(Color.WHITE);
        retry.setBorder(BorderFactory.createLineBorder(Color.WHITE, THICKNESS, false));
        retry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.setVisible(false);
                controller.getRunner().restart();
                controller.getRunner().run();
            }
        });

        buttonPanel.setBackground(Color.BLACK);
        separator.setBackground(Color.BLACK);

        final int width1 = 20;
        final int verticalStrut = 40;
        panel.add(label);
        panel.add(Box.createVerticalStrut(verticalStrut));
        buttonPanel.add(home);
        final int buttonPanelWidthStrut = width1 + 30;
        buttonPanel.add(Box.createHorizontalStrut(buttonPanelWidthStrut));
        buttonPanel.add(retry);
        panel.add(buttonPanel);

        panel.setBorder(border);
        panel.setBackground(Color.BLACK);
        frame.getContentPane().add(panel);
        this.frame.setVisible(true);
    }

    private void addingFont() {
        try {
            final float fontLabelDim = 120f;
            final float fontButtonDim = 80f;

            InputStream fontStyle = ClassLoader.getSystemResourceAsStream("it/unibo/fonts/ProtestStrike-Regular.ttf");
            this.font = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontLabelDim)
                    .deriveFont(Font.BOLD);
            fontStyle = ClassLoader.getSystemResourceAsStream("it/unibo/fonts/Bungee-Regular.ttf");
            this.fontButton = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontButtonDim)
                    .deriveFont(Font.CENTER_BASELINE)
                    .deriveFont(Font.PLAIN);

            fontStyle.close();

        } catch (FontFormatException | IOException e) {
            Logger.getLogger(GameOverView.class.getName()).severe(e.getMessage());
        }
    }
}
