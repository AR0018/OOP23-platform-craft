package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;

import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import java.util.Objects;
import java.util.Optional;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Command;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.View;

/**
 * Models the GUI of the running level.
 */
public final class LevelGUI {

    private static final int WIDTH_FRAME = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT_FRAME = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int MENUBAR_ORIZZONTAL_GAP = 20;
    private static final int MENUBAR_VERTICAL_GAP = 0;
    private static final int MENUBAR_TOP = 0;
    private static final int MENUBAR_LEFT = 9;
    private static final int MENUBAR_BOTTOM = 0;
    private static final int MENUBAR_RIGHT = 10;
    private static final Dimension BUTTON_DIM = new Dimension(125, 35);
    private static final int THICKNESS = 4;
    private final JFrame frame = new JFrame();
    //private final JPanel panelView = new JPanel();
    private final PaintPanel panelView;
    private Font fontButton;
    private Controller controller;

    /**
     * Constructor of the LevelGUI used to build the view of the level.
     * @param controller the controller of the game
     * @param width of the map level
     * @param height of the map level
     * @param view main view of the level
     */
    public LevelGUI(final Controller controller, final double width, final double height, final View view) {

        this.panelView = new PaintPanel(controller, WIDTH_FRAME, HEIGHT_FRAME, Optional.empty());
        this.controller = Objects.requireNonNull(controller);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.frame.setMinimumSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.frame.setLocationRelativeTo(null);
        this.frame.setFocusable(true);

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

        //TODO: test System.out.println("GUI startata, aggiungo key listener");

        this.frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent e) {
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                int inputReceived = e.getKeyCode();

                if (frame.isVisible()) {
                    switch (inputReceived) {
                        case KeyEvent.VK_W, KeyEvent.VK_UP, KeyEvent.VK_SPACE:
                            System.out.println("Tasto su1");             //TODO: test
                            controller.getRunner().notifyCommand(Command.MOVE_UP);
                            System.out.println("Tasto su2");             //TODO: test
                            break;
                        case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                            System.out.println("Tasto sinistra1");             //TODO: test
                            controller.getRunner().notifyCommand(Command.MOVE_LEFT);
                            System.out.println("Tasto sinistra2");             //TODO: test
                            break;
                        case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                            System.out.println("Tasto destra1");             //TODO: test
                            controller.getRunner().notifyCommand(Command.MOVE_DOWN);
                            System.out.println("Tasto destra2");             //TODO: test
                            break;
                        case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                            System.out.println("Tasto giu1");             //TODO: test
                            controller.getRunner().notifyCommand(Command.MOVE_RIGHT);
                            System.out.println("Tasto giu2");             //TODO: test
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
            }
        });

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
                    controller.getRunner().stopLevel();
                    //new ViewImpl(controller, width, height).displayStart();
                    view.displayStart();
                }
            }
        });

        JComponent component = new JPanel(new BorderLayout());
        menuBar.add(menu);
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
        this.controller.getRunner().run();
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

    /**
     * Shows on the screen all the entities of the set.
     * @param entities set of the entities
     */
    public void render(final Set<SimpleEntity> entities) {
        this.panelView.render(entities);
    }
}
