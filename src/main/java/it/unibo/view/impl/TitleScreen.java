package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.EditorView;
import it.unibo.view.api.LevelView;
import it.unibo.view.api.View;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;

import java.util.Objects;

import java.util.Set;
import java.util.logging.Logger;

import java.io.File;

/**
 * Class to create the TitleScreen when the game starts.
 */
public final class TitleScreen {

    private static final String TITLE = "PlatformCraft";
    private static final Color BUTTON_BACK = new Color(0, 0, 0);
    private static final Color FOREGROUND = new Color(255, 255, 255);
    private static final float TITLE_SIZE = 80f;
    private static final Dimension BUTTON_SIZE = new Dimension(60, 50); 
    private static final int XDIM = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.2);
    private static final int YDIM = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.2);
    private final JFrame frame = new JFrame("Prova");
    private final Controller controller;
    private final View view;
    private LevelView levelView;
    private EditorView editorView;
    private Font font;
    private Font fontButton;

    /**
     * Constructor to build the gui of the TitleScreen.
     * @param controller the controller of the game
     * @param view main view of the level
     */
    public TitleScreen(final Controller controller, final View view) {

        this.controller = controller;
        this.view = view;

        final int numberRow = 3;
        final int numberCol = 1;
        final int zero = 0;
        final int panelWidthPreferred = 500;
        final int panelHeightPreferred = 500;
        final int panelTopDim = 150;
        final int panelVerticalGap = 20;

        this.levelView = new LevelViewImpl(controller, view);
        this.editorView = new EditorViewImpl(controller, view);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(XDIM, YDIM));
        this.frame.setMinimumSize(new Dimension(XDIM, YDIM));
        this.frame.pack();

        final JPanel panel = new JPanel(new GridLayout(numberRow, numberCol, zero, panelVerticalGap));
        final int bottom = 100;
        final int left = 200;
        final int right = 200;

        panel.setPreferredSize(new Dimension(panelWidthPreferred, panelHeightPreferred));
        panel.setBorder(new EmptyBorder(panelTopDim, left, bottom, right));
        panel.setBackground(BUTTON_BACK);

        final JLabel label = new JLabel(TITLE, JLabel.CENTER);
        this.addingFont();

        label.setLayout(new FlowLayout());
        label.setFont(font);
        label.setForeground(FOREGROUND);

        final JButton play = new JButton("Play");
        play.setPreferredSize(BUTTON_SIZE);
        play.setFont(fontButton);
        play.setBorder(BorderFactory.createLineBorder(Color.white));
        play.setBorderPainted(true);
        play.setBackground(FOREGROUND);
        play.setForeground(BUTTON_BACK);
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                if (file.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    startLevel(file.getSelectedFile());
                }
            }
        });

        final JButton editor = new JButton("Editor");
        editor.setPreferredSize(BUTTON_SIZE);
        editor.setFont(fontButton);
        editor.setBorder(BorderFactory.createLineBorder(Color.white));
        editor.setBorderPainted(true);
        editor.setBackground(FOREGROUND);
        editor.setForeground(BUTTON_BACK);
        editor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.setVisible(false);
                controller.getEditor().reset();
                editorView = new EditorViewImpl(controller, view);
                editorView.show();
            }
        });

        final JButton quit = new JButton("Quit");
        quit.setPreferredSize(BUTTON_SIZE);
        quit.setFont(fontButton);
        quit.setBorder(BorderFactory.createLineBorder(Color.white));
        quit.setBorderPainted(true);
        quit.setBackground(FOREGROUND);
        quit.setForeground(BUTTON_BACK);
        quit.addActionListener(new ActionListener() {

            @SuppressFBWarnings(
                value = {
                    "DM_EXIT"
                },
                justification = "This code calls System.exit(), exiting the program. "
                    + "It is exactly what we want in this case."
            )

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to quit?",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(BUTTON_BACK);
        titlePanel.add(label, BorderLayout.NORTH);
        titlePanel.add(panel, BorderLayout.CENTER);
        //titlePanel.setBorder(new EmptyBorder(new Insets(top, left, zero, right)));

        panel.add(play);
        panel.add(editor);
        panel.add(quit);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.add(titlePanel, BorderLayout.CENTER);
    }
    /**
     * Sets visible the frame of the TitleScreen.
     */
    public void setVisible() {
        frame.setVisible(true);
    }

    /**
     * Tells the LevelView to print the Entity.
     * @param entities set of Entity that have to be printed
     */
    public void render(final Set<SimpleEntity> entities) {
        this.levelView.render(entities);
    }

    /**
     * Returns the level view of this TitleScreen.
     * @return the level view of the TitleScreem
     * @throws NullPointerException if the level view is null
     */
    public LevelView getLevelView() {
        return Objects.requireNonNull(this.levelView);
    }

    private void startLevel(final File file) {
        if (this.controller.getRunner().loadLevel(file)) {
            this.frame.setVisible(false);
            levelView = new LevelViewImpl(this.controller, this.view);
            levelView.show();
        } else {
            JOptionPane.showMessageDialog(this.frame, "The selected file could not be loaded",
                "Loading error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addingFont() {
        try {
            final float fontLabelDim = TITLE_SIZE + 60;
            final float fontButtonDim = 50f;

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
            Logger.getLogger(TitleScreen.class.getName()).severe(e.getMessage());
        }
    }
}
