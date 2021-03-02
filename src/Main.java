import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        World world = new World();
        while (true) {
            world.repaint();
            world.updateWorldPhysics(); // вызываем чтобы обновить состояние физики мира (движение персонажа)
            Thread.sleep(20);
        }
    }
}
