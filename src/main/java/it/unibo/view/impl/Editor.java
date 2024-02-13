package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

//Link che mi aiutato
//https://stackoverflow.com/questions/13040747/resize-components-on-frame-resize-java
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://
//  docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/Utils.java

//https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton
/**
 * Class used to build the editor GUI.
 */
public final class Editor {

    private static final int WIDTH_FRAME = 1280;
    private static final int HEIGHT_FRAME = 960;
    private static final int MENUBAR_ORIZZONTAL_GAP = 20;
    private static final int MENUBAR_VERTICAL_GAP = 0;
    private static final int MENUBAR_TOP = 0;
    private static final int MENUBAR_LEFT = 9;
    private static final int MENUBAR_BOTTOM = 0;
    private static final int MENUBAR_RIGHT = 10;
    private static final Dimension BUTTON_DIM = new Dimension(125, 35);
    private static final Dimension REMOVE_BUTTON_DIM = new Dimension(254, 35);
    private static final int THICKNESS = 4;
    private final JFrame frame = new JFrame();
    private final JPanel panelView = new JPanel();              //TODO: sostituire con il DrawPanel
    private final Controller controller;
    private Optional<EntityType> type = Optional.empty();
    private Optional<Point> mousePosition = Optional.empty();
    private Font font;
    private Font fontButton;

    /**
     * Constructor of the Editor class.
     * @param controller the controller of the game
     */
    public Editor(final Controller controller) {

        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));     //1600, 900
        this.frame.setMinimumSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.frame.setLocationRelativeTo(null);
        //final JPanel box = new JPanel(new GridLayout(3, 2, 30, 20));
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);
        menuBar.setLayout(new BorderLayout(MENUBAR_ORIZZONTAL_GAP, MENUBAR_VERTICAL_GAP));
        menuBar.setBorder(new EmptyBorder(MENUBAR_TOP, MENUBAR_LEFT, MENUBAR_BOTTOM, MENUBAR_RIGHT));


        JPanel menuButtons = new JPanel(new GridBagLayout());
        //menuBar.setAlignmentX(SwingConstants.NORTH);
        //menuBar.setBackground(Color.BLACK);
        //menuBar.setPreferredSize(new Dimension(1000, 40));
        /*final JMenu menu = new JMenu("Quit");
        //final JButton menu = new JButton("Quit");
        final JMenu save = new JMenu("Save");
        final JMenu load = new JMenu("Load");*/
        try {
            final float fontLabelDim = 30f;
            final float fontButtonDim = 25f;

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

        final JButton menu = new JButton("Quit");
        menu.setFont(fontButton);
        final JButton save = new JButton("Save");
        save.setFont(fontButton);
        final JButton load = new JButton("Load");
        load.setFont(fontButton);
        final JButton reset = new JButton("Reset");
        reset.setFont(fontButton);
        final JButton remove = new JButton("Remove");
        remove.setFont(fontButton);
        //final JButton button6 = new JButton("LABEL");

        /*final JMenu empty = new JMenu();
        final JMenu empty1 = new JMenu();
        final JMenu empty2 = new JMenu();*/
        //final JMenu empty3 = new JMenu();

        /*empty.setSize(new Dimension(10, 20));
        empty.setEnabled(false);
        empty1.setSize(new Dimension(20, 20));
        empty1.setEnabled(false);
        empty2.setSize(new Dimension(5, 20));
        empty2.setEnabled(false);*/
        //empty.setOpaque(false);
        //menu.setBorder(new EmptyBorder(0, 50, 0, 50));
        //menu.setHorizontalTextPosition(SwingConstants.CENTER);
        //invalidate();
        //menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //menuBar.add(empty2);
        menu.setPreferredSize(BUTTON_DIM);
        menu.setBackground(Color.white);
        menu.setForeground(Color.black);
        menu.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        //menuBar.add(menu, BorderLayout.EAST);
        menuButtons.add(menu);
        menuButtons.add(Box.createHorizontalStrut(10));
        //menuBar.add(empty);

        panelView.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                mousePosition = Optional.of(e.getPoint());
                /*controller.getEditor().addEntity(new SimpleEntity() {

                    @Override
                    public EntityType getType() {
                        return type.get();
                    }

                    @Override
                    public double getX() {
                        return mousePosition.getX();
                    }

                    @Override
                    public double getY() {
                        return mousePosition.getY();
                    }
                    
                });*/
                System.out.println("Added " +  type.get() + " at Pos:" + e.getPoint());
            }

            @Override
            public void mousePressed(final MouseEvent e) {
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }
        });

        menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
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
        save.setPreferredSize(BUTTON_DIM);
        save.setBackground(Color.white);
        save.setForeground(Color.black);
        save.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        //save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //save.setForeground(new Color(255, 255, 0));

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                /*String name = JOptionPane.showInputDialog(frame, "Write the filename", "Saving", JOptionPane.PLAIN_MESSAGE);
                if (name.contains(".json")) {
                    File file = new File(name);
                    controller.getEditor().saveLevel(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "The type of file is not .json",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE);
                }*/
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                if (file.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    if (file.getSelectedFile().getName().endsWith(".json")) {
                        controller.getEditor().saveLevel(file.getSelectedFile());
                    } else {
                        JOptionPane.showMessageDialog(frame, "The type of file is not .json",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE); 
                    }
                }
            }
        });
        //menuBar.add(save);
        menuButtons.add(save);
        menuButtons.add(Box.createHorizontalStrut(10));
        //menuBar.add(empty1);

        //load.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        load.setPreferredSize(BUTTON_DIM);
        load.setBackground(Color.white);
        load.setForeground(Color.black);
        load.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //JFileChooser file = new JFileChooser("src/main/resources/it/unibo");      //controllare se può andare
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                //file.setCurrentDirect;
                if (file.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.getEditor().loadLevel(file.getSelectedFile());
                }
            }
        });
        //menuBar.add(load);
        menuButtons.add(load);
        menuButtons.add(Box.createHorizontalStrut(10));

        reset.setPreferredSize(BUTTON_DIM);
        reset.setBackground(Color.white);
        reset.setForeground(Color.black);
        reset.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //frame.setVisible(false);
                //controller.getEditor().start();
                if (JOptionPane.showConfirmDialog(frame,
                         "Confirm to reset?", "Resetting",
                         JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            //controller.getEditor().reset();
                            //frame.setVisible(false);
                            controller.getEditor().reset();
                        }
            }
        });
        //menuBar.add(reset);
        menuButtons.add(reset);

        remove.setPreferredSize(REMOVE_BUTTON_DIM);
        remove.setBackground(Color.white);
        remove.setForeground(Color.black);
        remove.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (mousePosition.isPresent()) {
                    controller.getEditor().removeEntity(mousePosition.get().getX(), mousePosition.get().getY());
                }
            }
        });
        menuBar.add(menuButtons, BorderLayout.WEST);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(remove, BorderLayout.LINE_END);

        //menuBar.add(remove);
        //menuBar.add(empty);
        //menuBar.setSize((int) (frame.getSize().getWidth_FRAME() / 1.5), 20);
        //frame.add(menuBar);


        final JPanel box = new JPanel();
        box.setBackground(Color.GRAY);
        //box.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        //box.setBackground(Color.BLUE);
        //button.setPreferredSize(new Dimension(50, 120));
        box.setLayout(new GridLayout(3, 2, 10, 10));

        final ImageIcon imageIcon = new ImageIcon("src/main/resources/it/unibo/images/Owlet_Monster.png");
        final ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        final JButton button = new JButton();
        button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
        final JLabel textP = new JLabel("Character"); //SwingConstants.
        textP.setFont(new Font("Verdana", Font.BOLD, 20));
        textP.setForeground(Color.WHITE);
        //textP.setSize(50, 100);
        //final JPanel pn = new JPanel(new GridLayout(2, 1));
        final JLabel iconP = new JLabel(img);
        iconP.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        //pn.add(textP);
        //pn.setOpaque(false);
        button.add(textP);
        iconP.setOpaque(false);
        button.add(iconP);
        //pn.add(iconP);
        //pn.setOpaque(false);
        //button.add(pn);
        //final JButton button = new JButton("Character", imageIcon);

        //final Button button = new Button("src/main/resources/it/unibo/images/Owlet_Monster.png", "Character");
        //button.setBorder(BorderFactory.createLineBorder(Color.white, THICKNESS-1));
        //button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.DARK_GRAY));
        
        //button.setFont(new Font("Verdana", Font.BOLD, 15));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        addEntityFromButton(button, EntityType.CHARACTER);
        box.add(button);

        final JButton button1 = new JButton();
        final JLabel iconP1 = new JLabel(img);
        iconP1.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        addEntityFromButton(button1, EntityType.SIMPLE_ENEMY);
        //button1.setBorder(BorderFactory.createLineBorder(Color.white, THICKNESS-1));
        button1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true),
                 "Simple Enemy", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, font.deriveFont(20f)));
        button1.setFont(new Font("Verdana", Font.BOLD, 15));
        button1.setBackground(Color.WHITE);
        button1.setForeground(Color.BLACK);
        button1.add(iconP1);
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button1);

        final JButton button2 = new JButton("Enemy");
        addEntityFromButton(button2, EntityType.ENEMY);
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button2);

        final JButton button3 = new JButton("FinishLocation");
        addEntityFromButton(button3, EntityType.FINISH_LOCATION);
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button3);

        final JButton button4 = new JButton("Trap");
        addEntityFromButton(button4, EntityType.TRAP);
        //button.setPreferredSize(new Dimension(10, 60));
        box.add(button4);

        final JButton button5 = new JButton("MapElement");
        addEntityFromButton(button5, EntityType.MAP_ELEMENT);
        //button5.setVerticalTextPosition(SwingConstants.TOP);          //Possono servire per mettere in altro il testo
        //button5.setVerticalAlignment(SwingConstants.TOP);
        //button.setPreferredSize(new Dimension(30, 60));
        box.add(button5);
        /*GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        //c.gridheight = 3;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 2;
        c.insets = new Insets(12, 400, 10, 0);*/
        //JComponent component = new JPanel(new GridBagLayout());
        JComponent component = new JPanel(new BorderLayout());
        component.setBackground(Color.GRAY);;
        component.setBorder(new EmptyBorder(10, 10, 10, 10));
        //JPanel contentPane = new JPanel();
        //box.setForeground(Color.BLACK);

        //JPanel pane = new JPanel(new GridLayout(3, 2));
        //contentPane.setBorder(new EmptyBorder(50, 100, 50, 20));
        //contentPane.setBounds(400,200, (int)contentPane.getSize().getWidth_FRAME(), 
        //(int)contentPane.getSize().getHeight_FRAME());


        //contentPane.setLayout(new GridBagLayout());       da rimettere
        //contentPane.add(box, BorderLayout.EAST);
        //component.add(contentPane, BorderLayout.WEST);
        component.add(box, BorderLayout.EAST);
        /*c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        //c.gridheight_FRAME = 3;
        //c.gridwidth_FRAME = GridBagConstraints.RELATIVE;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(20, 0, 20, 400);*/
        //final JPanel panl = new JPanel();
        panelView.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS));
        //panelView.setBackground(Color.BLACK);
        //button6.setSize(500, 600);
        //component.add(button6, BorderLayout.CENTER);
        component.add(panelView, BorderLayout.CENTER);
        //component.setMinimumSize(new Dimension(700, 700));
        this.frame.setContentPane(component);
        this.frame.setJMenuBar(menuBar);
    }

    /**
     * Shows the Editor GUI.
     */
    public void show() {
        this.frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "If you want to add something, first press the button\n that represents" +
                " the Game Entity you want to add" + "\nand then click on the central panel to add it.",
         "Let's start with the Editor!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Hides the Editor GUI.
     */
    public void hide() {
        this.frame.setVisible(false);
    }

    /**
     * Checks if the GUI is visible or not.
     * @return true if it's visible, false otherwise
     */
    public boolean isShown() {
        return this.frame.isVisible();
    }

    private void addEntityFromButton(final JButton button, final EntityType typeInput) {
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {      //settare una variabile type -> tipo
                                                    //in mouse listener controlla il tipo e aggiunge
                //System.out.println(typeInput + "-> Pos: " + mousePosition);
                type = Optional.of(typeInput);
                /*controller.getEditor().addEntity(new SimpleEntity() {             //TODO: da decommentare

                    @Override
                    public EntityType getType() {
                        return type;
                    }

                    @Override
                    public double getX() {
                        return mousePosition.getX();   
                    }

                    @Override
                    public double getY() {
                        return mousePosition.getY();    
                    }
                    
                });*/
            }
        });
    }

    private static class Button extends JButton {

        Image image;
        ImageObserver observer;
        ImageIcon imageIcon;

        Button(final String filePathName, final String text) {
            super();
            setText(text);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setVerticalTextPosition(SwingConstants.BOTTOM);
            ImageIcon imageIcon = new ImageIcon(filePathName);
            image = imageIcon.getImage();
            observer = imageIcon.getImageObserver();
        }

        public void paint(final Graphics g) {
            super.paint(g);
            g.drawImage(image, (int) (getWidth() / 5), (int) (getHeight() / 3), 100, 120, observer);
        }
    }
    /*private void buttonGraphic(final JButton button, final String fileNamePath, final String entityType) {
        final ImageIcon imageIcon = new ImageIcon(fileNamePath);
        //final JPanel o = new JPanel(new GridLayout(2, 1));
        final JLabel textP = new JLabel(entityType, SwingConstants.CENTER);
        final ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(110, 120, Image.SCALE_DEFAULT));
        final JLabel iconP = new JLabel(img, SwingConstants.CENTER);
        
        //button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
        textP.setFont(fontButton.deriveFont(18f));
        textP.setForeground(Color.DARK_GRAY);

        button.add(textP);
        //o.setOpaque(false);
        //button.add(Box.createVerticalStrut(40));
        iconP.setOpaque(false);
        button.add(iconP);
    } */
    /*
     * button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelView.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(EntityType.SIMPLE_ENEMY + "-> Pos: " + mousePosition);
                        controller.getEditor().addEntity(new SimpleEntity() {

                    @Override
                    public EntityType getType() {
                        return EntityType.SIMPLE_ENEMY;
                    }

                    @Override
                    public double getX() {
                        return mousePosition.getX();   
                    }

                    @Override
                    public double getY() {
                        return mousePosition.getY();    
                    }
                    
                });
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                    
                });
            }
            
        });
     */

    /*private String getFileExtension(final File f) {
        //String fileName = f.getName();
        //return fileName.substring(fileName.lastIndexOf("."), fileName.length() - 2);
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
        return "null";
    }*/
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
        contentPane.setBounds(400,200, (int)contentPane.getSize().getWidth_FRAME(), (int)contentPane.getSize().getHeight_FRAME());
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(box, c);
        contentPane.setOpaque(false);
        frame.setContentPane(contentPane);
        frame.setJMenuBar(menuBar);
 */
