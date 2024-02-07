package it.unibo.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is used to interpret the input 
 * received from the keyboard. 
 */
public class KeyHandler implements KeyListener{

    boolean wPressed, aPressed, sPressed, dPressed, spacePressed;
    /**
     * We don't need it
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int inputReceived = e.getKeyCode();

        switch(inputReceived){
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                break;
            case KeyEvent.VK_S:
                sPressed = true;
                break;
            case KeyEvent.VK_D:
                dPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int inputReceived = e.getKeyCode();

        switch(inputReceived){
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                break;
            case KeyEvent.VK_S:
                sPressed = false;
                break;
            case KeyEvent.VK_D:
                dPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            default:
                break;
        }
    }
    
}
