import java.awt.*;

public class Platform extends Item{

    public int x,y,width,height;
    public Color c;

    public Platform(byte ID,Color c, int x , int y,int width , int height ){
        super(ID);

        this.c = c;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void Render(Graphics g) {
//            g.setColor(c);
//            g.fillRect(x,y,width,height);
    }
}
