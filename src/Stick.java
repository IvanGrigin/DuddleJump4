import java.awt.*;

public class Stick {
    int x;
    double y;
    int h;
    int w;
    double speedY;

    public Stick(int x ,int y){
        this.x = x;
        this.y = y;
        this. w = 30;
        this.h = 5;
        this.speedY = 0.4;
    }

    public void update(int hight, int weight){
        y = y + speedY;
        if(y > hight){
            y = 0;
            x = (int) (Math.random() * weight);
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, (int) y, w ,h);
    }

}
