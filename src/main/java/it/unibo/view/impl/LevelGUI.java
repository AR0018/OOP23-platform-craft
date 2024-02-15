package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import it.unibo.controller.api.Controller;

/**
 * Models the GUI of the running level.
 */
public final class LevelGUI {

    private static final int WIDTH_FRAME = 1280;
    private static final int HEIGHT_FRAME = 960;
    private static final int MENUBAR_ORIZZONTAL_GAP = 20;
    private static final int MENUBAR_VERTICAL_GAP = 0;
    private static final int MENUBAR_TOP = 0;
    private static final int MENUBAR_LEFT = 9;
    private static final int MENUBAR_BOTTOM = 0;
    private static final int MENUBAR_RIGHT = 10;
    private static final Dimension BUTTON_DIM = new Dimension(125, 35);
    private static final int THICKNESS = 4;
    private final JFrame frame = new JFrame();
    private final JPanel panelView = new JPanel();              //TODO: sostituire con il DrawPanel
    private Font fontButton;

    /**
     * Constructor of the LevelGUI used to build the view of the level.
     * @param controller the controller of the game
     */
    public LevelGUI(final Controller controller) {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.frame.setMinimumSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.frame.setLocationRelativeTo(null);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);
        menuBar.setLayout(new BorderLayout(MENUBAR_ORIZZONTAL_GAP, MENUBAR_VERTICAL_GAP));
        menuBar.setBorder(new EmptyBorder(MENUBAR_TOP, MENUBAR_LEFT, MENUBAR_BOTTOM, MENUBAR_RIGHT));

        try {
            final float fontButtonDim = 25f;

            InputStream fontStyle = ClassLoader.getSystemResourceAsStream("./it/unibo/fonts/Bungee-Regular.ttf");
            fontButton = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontButtonDim)
                    .deriveFont(Font.CENTER_BASELINE)
                    .deriveFont(Font.PLAIN);

            fontStyle.close();

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        final JButton menu = new JButton("Quit");
        menu.setFont(fontButton);
        menu.setPreferredSize(BUTTON_DIM);
        menu.setBackground(Color.white);
        menu.setForeground(Color.black);
        menu.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to return to the Title Screen?",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                }
            }
        });
        menuBar.add(menu);
        JComponent component = new JPanel(new BorderLayout());
        component.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        component.setBackground(Color.GRAY);
        panelView.setBackground(Color.WHITE);
        panelView.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS + 1));
        component.add(panelView);
        frame.add(component);
        this.frame.setJMenuBar(menuBar);
    }

    /**
     * Shows the GUI of the level.
     */
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * Hides the GUI of the level.
     */
    public void hide() {
        this.frame.setVisible(false);
    }

    /**
     * Checks if the GUI of the level is visible.
     * @return true if it's visible, false otherwise
     */
    public boolean isShown() {
        return this.frame.isVisible();
    }
}