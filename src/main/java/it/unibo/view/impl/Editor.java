package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagLayoutInfo;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import it.unibo.controller.api.Controller;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

//Link che mi aiutato
//https://stackoverflow.com/questions/13040747/resize-components-on-frame-resize-java
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/Utils.java

public class Editor {

    private final JFrame frame = new JFrame();

    public Editor(final Controller controller) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1280, 960));     //1600, 900
        frame.setMinimumSize(new Dimension(1280, 960));
        frame.setLocationRelativeTo(null);
        //final JPanel box = new JPanel(new GridLayout(3, 2, 30, 20));
        final JMenuBar menuBar = new JMenuBar();
        //menuBar.setAlignmentX(SwingConstants.NORTH);
        //menuBar.setBackground(Color.BLACK);
        //menuBar.setPreferredSize(new Dimension(1000, 40));
        /*final JMenu menu = new JMenu("Quit");
        //final JButton menu = new JButton("Quit");
        final JMenu save = new JMenu("Save");
        final JMenu load = new JMenu("Load");*/
        final JButton menu = new JButton("Quit");
        final JButton save = new JButton("Save");
        final JButton load = new JButton("Load");
        final JMenu empty = new JMenu();
        final JMenu empty1 = new JMenu();
        empty.setSize(new Dimension(20, 20));
        empty.setEnabled(false);
        empty1.setSize(new Dimension(20, 20));
        empty1.setEnabled(false);
        //empty.setOpaque(false);
        //menu.setBorder(new EmptyBorder(0, 50, 0, 50));
        //menu.setHorizontalTextPosition(SwingConstants.CENTER);
        //invalidate();
        //menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        menu.setSize(new Dimension(100, 40));
        menuBar.add(menu);
        menuBar.add(empty);
        /*menu.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to return to the Title Screen",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                } 
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
            
        });*/
        menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to return to the Title Screen",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    new ViewImpl(controller).displayStart();
                }
            }
            
        });
        //menuBar.add(new JSeparator(JSeparator.HORIZONTAL));
        //menu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        //save.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        save.setPreferredSize(new Dimension(200, 30));
        //save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //save.setForeground(new Color(255, 255, 0));
        /*save.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Write the filename", "Saving", JOptionPane.PLAIN_MESSAGE);
                if (name.contains(".json")) {
                    File file = new File(name);
                    controller.getEditor().saveLevel(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "The type of file is not .json",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'menuDeselected'");
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'menuCanceled'");
            }
            
        });*/
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Write the filename", "Saving", JOptionPane.PLAIN_MESSAGE);
                if (name.contains(".json")) {
                    File file = new File(name);
                    controller.getEditor().saveLevel(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "The type of file is not .json",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        menuBar.add(save);
        menuBar.add(empty1);

        //load.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        load.setPreferredSize(new Dimension(100, 20));
        /*load.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                file.showSaveDialog(null);
                controller.getEditor().loadLevel(file.getSelectedFile());
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
            
        });*/
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                file.showSaveDialog(null);
                controller.getEditor().loadLevel(file.getSelectedFile());
            }
            
        });
        menuBar.add(load);

        //menuBar.add(empty);
        //menuBar.setSize((int) (frame.getSize().getWidth() / 1.5), 20);
        //frame.add(menuBar);
        final JPanel box = new JPanel();
        final JButton button = new JButton("Character");
        //button.setPreferredSize(new Dimension(50, 120));
        box.setLayout(new GridLayout(3, 2, 10, 10));
        box.add(button);
        final JButton button1 = new JButton("EnemySimple");
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button1);
        final JButton button2 = new JButton("Enemy");
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button2);
        final JButton button3 = new JButton("FinishLocation");
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button3);
        final JButton button4 = new JButton("Trap");
        //button.setPreferredSize(new Dimension(10, 60));
        box.add(button4);
        final JButton button5 = new JButton("MapElement");
        button5.setVerticalTextPosition(SwingConstants.TOP);
        button5.setVerticalAlignment(SwingConstants.TOP);
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button5);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        //c.gridheight = 3;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 2;
        c.insets = new Insets(12, 400, 10, 0);
        //JComponent component = new JPanel(new GridBagLayout());
        JComponent component = new JPanel(new BorderLayout());
        component.setBorder(new EmptyBorder(5, 5, 5, 5));
        //JPanel contentPane = new JPanel();
        //box.setForeground(Color.BLACK);

        //JPanel pane = new JPanel(new GridLayout(3, 2));
        //contentPane.setBorder(new EmptyBorder(50, 100, 50, 20));
        //contentPane.setBounds(400,200, (int)contentPane.getSize().getWidth(), (int)contentPane.getSize().getHeight());
        
        
        //contentPane.setLayout(new GridBagLayout());       da rimettere
        //contentPane.add(box, BorderLayout.EAST);
        //component.add(contentPane, BorderLayout.WEST);
        component.add(box, BorderLayout.EAST);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        //c.gridheight = 3;
        //c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(20, 0, 20, 400);
        //final JPanel panl = new JPanel();
        final JButton button6 = new JButton("LABEL");
        final JPanel panelView = new JPanel();
        panelView.setBackground(Color.BLACK);
        //button6.setSize(500, 600);
        //component.add(button6, BorderLayout.CENTER);
        component.add(panelView);
        //component.setMinimumSize(new Dimension(700, 700));
        frame.setContentPane(component);
        frame.setJMenuBar(menuBar);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public boolean isShown() {
        return frame.isVisible();
    }

    private String getFileExtension(final File f) {
        //String fileName = f.getName();
        //return fileName.substring(fileName.lastIndexOf("."), fileName.length() - 2);
        /*String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
        */
        return "null";
    }
}

/*
 * GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.7;
        c.weighty = 7;
        JPanel contentPane = new JPanel();
        //contentPane.setBorder(new EmptyBorder(50, 100, 50, 20));
        contentPane.setBounds(400,200, (int)contentPane.getSize().getWidth(), (int)contentPane.getSize().getHeight());
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(box, c);
        contentPane.setOpaque(false);
        frame.setContentPane(contentPane);
        frame.setJMenuBar(menuBar);
 */
