import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Man {
    double speedX;
    double speedY;
    double G;
    double x;
    double y;
    int h = 10;
    int w = 10;

    public Man(double x, double y){
        this.x = x;
        this.y = y;
        speedX = 0;
        speedY = -4;
        G = 0.002;
    }
    public void update(long dt, int wOfFrame) {
        if(x < 0){
            x = x + wOfFrame;
        }
        x = (x + speedX)%wOfFrame;
        y = y + speedY;
        speedY = speedY + G * dt;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect((int)x, (int)y, 10,10);
    }
    public void speedUp(){
        speedY = -4;
    }
}
