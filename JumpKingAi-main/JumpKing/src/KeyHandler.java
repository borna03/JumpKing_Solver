import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.SimpleTimeZone;

public class KeyHandler implements KeyListener{

    public boolean SpacePressed, LeftPressed, RigthPressed, generate = false;
    public String Faceing = "null";
    boolean Break = false;
    int counter = 0;
    int counterr = 0;
    boolean test = false, foreal = true;

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
                Faceing = "left";
                break;
            case 'd':
                Faceing = "right";
                break;
            case 'u':
                generate= true;
                break;
            case 'w':
                counter+=1;
                break;
            case 's':
                counter-=1;
                break;
            case 't':
                foreal = false;
                break;
            case 'f':
                foreal= true;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D){
            RigthPressed = true;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            SpacePressed = true;
        }
        if (keyCode == KeyEvent.VK_A){
            LeftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D){
            RigthPressed = false;
        }
        if (keyCode == KeyEvent.VK_A){
            LeftPressed = false;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            SpacePressed = false;
        }
    }
}
