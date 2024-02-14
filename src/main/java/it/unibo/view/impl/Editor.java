package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import it.unibo.common.EntityType;
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
    private static final int BUTTON_TEXT_SIZE = 20;
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
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);
        menuBar.setLayout(new BorderLayout(MENUBAR_ORIZZONTAL_GAP, MENUBAR_VERTICAL_GAP));
        menuBar.setBorder(new EmptyBorder(MENUBAR_TOP, MENUBAR_LEFT, MENUBAR_BOTTOM, MENUBAR_RIGHT));


        JPanel menuButtons = new JPanel(new GridBagLayout());

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

        menu.setPreferredSize(BUTTON_DIM);
        menu.setBackground(Color.white);
        menu.setForeground(Color.black);
        menu.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));

        menuButtons.add(menu);
        menuButtons.add(Box.createHorizontalStrut(10));


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

        save.setPreferredSize(BUTTON_DIM);
        save.setBackground(Color.white);
        save.setForeground(Color.black);
        save.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));

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
        menuButtons.add(save);
        menuButtons.add(Box.createHorizontalStrut(10));

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
                if (file.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.getEditor().loadLevel(file.getSelectedFile());
                }
            }
        });
        menuButtons.add(load);
        menuButtons.add(Box.createHorizontalStrut(10));

        reset.setPreferredSize(BUTTON_DIM);
        reset.setBackground(Color.white);
        reset.setForeground(Color.black);
        reset.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS));
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame,
                         "Confirm to reset?", "Resetting",
                         JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            controller.getEditor().reset();
                        }
            }
        });
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

        final JPanel box = new JPanel();
        box.setBackground(Color.GRAY);
        box.setLayout(new GridLayout(3, 2, 10, 10));

        final ImageIcon imageIcon = new ImageIcon("src/main/resources/it/unibo/images/Owlet_Monster.png");
        final ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        final JButton button = new JButton();
        button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));

        final JLabel textP = new JLabel("Character"); //SwingConstants.
        textP.setFont(new Font("Verdana", Font.BOLD, BUTTON_TEXT_SIZE));
        textP.setForeground(Color.WHITE);

        final JLabel iconP = new JLabel(img);
        final int topIconP = 20;
        final int leftIconP = 10;
        iconP.setBorder(BorderFactory.createEmptyBorder(topIconP, leftIconP, 0, 0));

        button.add(textP);
        iconP.setOpaque(false);
        button.add(iconP);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        addEntityFromButton(button, EntityType.CHARACTER);
        box.add(button);

        final JButton button1 = new JButton();
        final JLabel iconP1 = new JLabel(img);
        final int leftIconP1 = 15;
        iconP1.setBorder(BorderFactory.createEmptyBorder(0, leftIconP1, 0, 0));
        addEntityFromButton(button1, EntityType.SIMPLE_ENEMY);
        button1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true),
                 "Simple Enemy", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, font.deriveFont((float) BUTTON_TEXT_SIZE)));

        final int button1TextSize = 15;        
        button1.setFont(new Font("Verdana", Font.BOLD, button1TextSize));
        button1.setBackground(Color.WHITE);
        button1.setForeground(Color.BLACK);
        button1.add(iconP1);
        box.add(button1);

        final JButton button2 = new JButton("Enemy");
        addEntityFromButton(button2, EntityType.ENEMY);
        box.add(button2);

        final JButton button3 = new JButton("FinishLocation");
        addEntityFromButton(button3, EntityType.FINISH_LOCATION);
        box.add(button3);

        final JButton button4 = new JButton("Trap");
        addEntityFromButton(button4, EntityType.TRAP);
        box.add(button4);

        final JButton button5 = new JButton("MapElement");
        addEntityFromButton(button5, EntityType.MAP_ELEMENT);
        //button5.setVerticalTextPosition(SwingConstants.TOP);          //Possono servire per mettere in alto il testo
        //button5.setVerticalAlignment(SwingConstants.TOP);
        box.add(button5);

        JComponent component = new JPanel(new BorderLayout());
        component.setBackground(Color.GRAY);
        component.setBorder(new EmptyBorder(10, 10, 10, 10));

        component.add(box, BorderLayout.EAST);
        panelView.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS));
        component.add(panelView, BorderLayout.CENTER);
        this.frame.setContentPane(component);
        this.frame.setJMenuBar(menuBar);
    }

    /**
     * Shows the Editor GUI.
     */
    public void show() {
        this.frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "If you want to add something, first press the button\n that represents"
                 + " the Game Entity you want to add" + "\nand then click on the central panel to add it.",
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

    /*private static class Button extends JButton {

        private Image image;
        private ImageObserver observer;
        private ImageIcon imageIcon;

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
    }*/

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
}

