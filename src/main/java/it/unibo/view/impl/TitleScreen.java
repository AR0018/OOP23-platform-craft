package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;

/**
 * Class to create the TitleScreen when the game starts.
 */
public final class TitleScreen extends JPanel {

    private static final String TITLE = "PlatformCraft";
    //private static final Color BACKGROUND = new Color(100, 120, 10);    //Sfondo comune
    private static final Color BUTTON_BACK = new Color(0, 0, 0);
    private static final Color FOREGROUND = new Color(255, 255, 255);
    private static final float TITLE_SIZE = 80f;
    private static final Dimension BUTTON_SIZE = new Dimension(60, 50); 
    private static final int XDIM = 1200;
    private static final int YDIM = 1000;
    private final JFrame frame = new JFrame("Prova");
    private Font font;
    private Font fontButton;

    /**
     * Constructor to build the gui of the TitleScreen.
     */
    public TitleScreen() {

        final int numberRow = 3;
        final int numberCol = 1;
        final int zero = 0;
        final int panelWidthPreferred = 500;
        final int panelHeightPreferred = 500;
        final int panelTopDim = 150;
        final int panelVerticalGap = 20;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(XDIM, YDIM));
        frame.setMinimumSize(new Dimension(XDIM, YDIM));
        frame.pack();

        final JPanel panel = new JPanel(new GridLayout(numberRow, numberCol, zero, panelVerticalGap));
        panel.setPreferredSize(new Dimension(panelWidthPreferred, panelHeightPreferred));
        panel.setBorder(new EmptyBorder(panelTopDim, zero, zero, zero));
        panel.setBackground(BUTTON_BACK);

        final JLabel label = new JLabel(TITLE);

        try {
            final float fontLabelDim = TITLE_SIZE + 60;
            final float fontButtonDim = 50f;

            File fontStyle = new File("./resources/ProtestStrike-Regular.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontLabelDim)
                    .deriveFont(Font.BOLD);

            fontStyle = new File("./resources/Bungee-Regular.ttf");
            fontButton = Font.createFont(Font.TRUETYPE_FONT, fontStyle)
                    .deriveFont(fontButtonDim)
                    .deriveFont(Font.CENTER_BASELINE)
                    .deriveFont(Font.PLAIN);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

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
                JFileChooser file = new JFileChooser();
                file.showSaveDialog(null);
                file.getSelectedFile();     //TODO: restituire il file ai piani superiori
            }
        });
        final JButton editor = new JButton("Editor");
        editor.setPreferredSize(BUTTON_SIZE);
        editor.setFont(fontButton);
        editor.setBorder(BorderFactory.createLineBorder(Color.white));
        editor.setBorderPainted(true);
        editor.setBackground(FOREGROUND);
        editor.setForeground(BUTTON_BACK);

        final JButton quit = new JButton("Quit");
        quit.setPreferredSize(BUTTON_SIZE);
        quit.setFont(fontButton);
        quit.setBorder(BorderFactory.createLineBorder(Color.white));
        quit.setBorderPainted(true);
        quit.setBackground(FOREGROUND);
        quit.setForeground(BUTTON_BACK);
        quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to quit",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        final int top = 100;
        final int left = 200;
        final int right = 200;

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(BUTTON_BACK);
        titlePanel.add(label);
        titlePanel.add(panel);
        titlePanel.setBorder(new EmptyBorder(new Insets(top, left, zero, right)));

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
}
