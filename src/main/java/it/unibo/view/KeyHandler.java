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
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                wPressed = true;
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                aPressed = true;
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                sPressed = true;
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                dPressed = true;
                break;
            case KeyEvent.VK_SPACE:         //Space is equal to VK_W e VK_UP
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
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                wPressed = false;
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                aPressed = false;
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                sPressed = false;
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
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
