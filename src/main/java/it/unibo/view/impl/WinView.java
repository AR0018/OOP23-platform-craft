package it.unibo.view.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.view.api.View;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;

/**
 * Models the GUI when the player wins the game.
 */
public final class WinView {

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

    /**
     * Constructor of the WinView.
     * @param levelGUI the view of the running level
     * @param view the main view of the game
     */
    public WinView(final LevelGUI levelGUI, final View view) {

        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(FRAME_DIMENSION);
        this.frame.setLocationRelativeTo(null);

        this.addingFont();

        label
        .setText("YOU WON!!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(false);

        button.setText("HOME");
        button.setFont(fontButton);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS, false));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.setVisible(false);
                levelGUI.hide();
                view.displayStart();
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

    private void addingFont() {
        try {
            final float fontLabelDim = 130f;
            final float fontButtonDim = 70f;

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
            Logger.getLogger(WinView.class.getName()).severe(e.getMessage());
        }
    }
}
