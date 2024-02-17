package it.unibo.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import it.unibo.view.api.View;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.Toolkit;

//Link che mi aiutato
//https://stackoverflow.com/questions/13040747/resize-components-on-frame-resize-java
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://
//  docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/Utils.java

//https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton
/**
 * Class used to build the editor GUI.
 */
public final class EditorGUI {

    private static final int WIDTH_FRAME = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT_FRAME = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
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
    //private final JPanel panelView = new JPanel();

    private final PaintPanel panelView;
    private Font font;
    private Font fontButton;

    /**
     * Constructor of the Editor class.
     * @param controller the controller of the game
     * @param width the width of the game level
     * @param height the heigth of the game level
     * @param view the main view of the game
     */
    public EditorGUI(final Controller controller, final double width, final double height, final View view) {

        this.panelView = new PaintPanel(controller, WIDTH_FRAME, HEIGHT_FRAME, Optional.of(this));
        //this.panelView.setPreferredSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));     //1600, 900

        this.frame.setMinimumSize(new Dimension(WIDTH_FRAME / 3, HEIGHT_FRAME / 3));
        this.frame.setLocationRelativeTo(null);
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);
        menuBar.setLayout(new BorderLayout(MENUBAR_ORIZZONTAL_GAP, MENUBAR_VERTICAL_GAP));
        menuBar.setBorder(new EmptyBorder(MENUBAR_TOP, MENUBAR_LEFT, MENUBAR_BOTTOM, MENUBAR_RIGHT));


        final JPanel menuButtons = new JPanel(new GridBagLayout());

        //System.out.println(panelView.getSize());
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

            fontStyle.close();

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

        /*panelView.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                mousePosition = Optional.of(e.getPoint());
                if (removeEntity) {
                    System.out.println("Removed at Pos:" + mousePosition.get().x 
                        + " boolean return " + panelView.removeEntity(mousePosition.get().x, mousePosition.get().y));
                } else {
                    System.out.println("Added at Pos:" + mousePosition.get().x 
                        + " boolean return " + panelView.addEntity(type.get(), mousePosition.get().x, mousePosition.get().y));
                }
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
        });*/

        menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Do you want to return to the Title Screen",
                         "Quitting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    //new ViewImpl(controller, width, height).displayStart();
                    view.displayStart();
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
                if (!controller.getEditor().canBeSaved()) {
                    JOptionPane.showMessageDialog(frame, "The type of file cannot be saved",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE);
                } else {
                    JFileChooser file = new JFileChooser();
                    file.setAcceptAllFileFilterUsed(false);
                    file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                    if (file.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        if (file.getSelectedFile().getName().endsWith(".json")) {
                            if (!controller.getEditor().saveLevel(file.getSelectedFile())) {
                                JOptionPane.showMessageDialog(frame, "An error during the saving",
                             "Error has occurred", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "The type of file is not .json",
                                    "Error has occurred", JOptionPane.ERROR_MESSAGE); 
                        }
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
                //JFileChooser file = new JFileChooser("src/main/resources/it/unibo");      //TODO: controllare se può andare
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));
                if (file.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    if (!controller.getEditor().loadLevel(file.getSelectedFile())) {
                        JOptionPane.showMessageDialog(frame, "The level could not be loaded",
                            "Loading error", JOptionPane.ERROR_MESSAGE);
                    }
                    panelView.render(controller.getEditor().getCurrentEntities());
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
                            panelView.render(controller.getEditor().getCurrentEntities());
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
                //removeEntity = true;
                //type = Optional.empty();
                panelView.setRemove();
            }
        });
        menuBar.add(menuButtons, BorderLayout.WEST);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(remove, BorderLayout.LINE_END);

        final JPanel box = new JPanel();
        box.setBackground(Color.GRAY);
        box.setLayout(new GridLayout(3, 2, 10, 10));

        /*final ImageIcon character = new ImageIcon("src/main/resources/it/unibo/images/Owlet_Monster.png");
        final ImageIcon imgCharacter = new ImageIcon(character.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        final JButton button = new JButton();
        button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));

        final JLabel textP = new JLabel("Character"); //SwingConstants.
        textP.setFont(new Font("Verdana", Font.BOLD, BUTTON_TEXT_SIZE));
        textP.setForeground(Color.WHITE);

        final JLabel iconP = new JLabel(imgCharacter);
        final int topIconP = 20;
        final int leftIconP = 10;
        iconP.setBorder(BorderFactory.createEmptyBorder(topIconP, leftIconP, 0, 0));

        button.add(textP);
        iconP.setOpaque(false);
        button.add(iconP);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);*/
        final JButton button = new JButton();
        addImageToButton(button, "Character", "src/main/resources/it/unibo/images/Owlet_Monster.png");
        addEntityFromButton(button, EntityType.CHARACTER);
        box.add(button);


        final JButton button1 = new JButton();
        addImageToButton(button1, "SimpeEnemy", "src/main/resources/it/unibo/images/Pink_Monster/Pink_Monster.png");
        addEntityFromButton(button1, EntityType.SIMPLE_ENEMY);
        box.add(button1);


        final JButton button2 = new JButton();
        addImageToButton(button2, "Enemy", "src/main/resources/it/unibo/images/Dude_Monster/Dude_Monster.png");
        addEntityFromButton(button2, EntityType.ENEMY);
        box.add(button2);

        final JButton button3 = new JButton();
        addImageToButton(button3, "End", "src/main/resources/it/unibo/images/R.png");
        addEntityFromButton(button3, EntityType.FINISH_LOCATION);
        box.add(button3);

        final JButton button4 = new JButton();
        addImageToButton(button4, "Trap", "src/main/resources/it/unibo/images/piggy3.png");
        addEntityFromButton(button4, EntityType.TRAP);
        box.add(button4);

        final JButton button5 = new JButton();
        addImageToButton(button5, "MapElement", "src/main/resources/it/unibo/images/block.png");
        addEntityFromButton(button5, EntityType.LONG_MAP_ELEMENT);      //TODO: used to be EntityType.MAP_ELEMENT
        //button5.setVerticalTextPosition(SwingConstants.TOP);          //Possono servire per mettere in alto il testo
        //button5.setVerticalAlignment(SwingConstants.TOP);
        box.add(button5);

        JComponent component = new JPanel(new BorderLayout());
        component.setBackground(Color.GRAY);
        component.setBorder(new EmptyBorder(10, 10, 10, 10));

        component.add(box, BorderLayout.EAST);
        panelView.setBorder(BorderFactory.createLineBorder(Color.BLACK, THICKNESS));
        component.add(panelView, BorderLayout.CENTER);
        //this.frame.setContentPane(component);
        this.frame.add(component);
        this.frame.setJMenuBar(menuBar);
    }

    /**
     * Shows a dialog message telling the user a entity could not be added. 
     */
    public void youCannotAdd() {
        JOptionPane.showMessageDialog(frame, "You cannot add an element in this position",
                "Addition error!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows the Editor GUI.
     */
    public void show() {
        this.frame.setVisible(true);
        this.frame.repaint();
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

    private void addImageToButton(final JButton button, final String type, final String filePathName) {
        final ImageIcon simpleEnemy = new ImageIcon(filePathName); //TODO: new ImageIcon(ClassLoader.getSystemResource(filePathName));
        final ImageIcon imgEnemy = new ImageIcon(simpleEnemy.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        final JLabel iconP1 = new JLabel(imgEnemy);
        final int leftIconP1 = 15;
        iconP1.setBorder(BorderFactory.createEmptyBorder(0, leftIconP1, 0, 0));
        button.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true),
                 type, TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, font.deriveFont((float) BUTTON_TEXT_SIZE - 2)));

        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.add(iconP1);
    }

    private void addEntityFromButton(final JButton button, final EntityType typeInput) {
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //removeEntity = false;
                //type = Optional.of(typeInput);
                panelView.setSelectedEntity(typeInput);
            }
        });
    }
}

