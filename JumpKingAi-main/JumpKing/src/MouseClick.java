import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.MouseInfo;
public class MouseClick implements MouseListener{
    int counter = 0;

    boolean state = false;
    @Override
    public void mouseClicked(MouseEvent e) {


        double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - 485;
        double mouseY = MouseInfo.getPointerInfo().getLocation().getY() - 171;
        counter += 1;
        System.out.println(mouseX + " X " + mouseY + " Y ");
        if(counter % 3 == 0){
            System.out.println("______________________________");
        }
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
}
