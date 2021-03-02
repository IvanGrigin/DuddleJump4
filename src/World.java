import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class World  extends JFrame implements KeyEventDispatcher{

    private Man man;
    private long previousWorldUpdateTime; // Храним здесь момент времени когда физика мира обновлялась в последний раз
    ArrayList<Stick> sticks = new ArrayList<>();

    public World(){
        setSize(350, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        sticks.add(new Stick(100, 0));
        sticks.add(new Stick(30, 100));
        sticks.add(new Stick(170, 600));
        sticks.add(new Stick(200, 200));
        sticks.add(new Stick(300, 300));
        sticks.add(new Stick(150, 400));
        sticks.add(new Stick(270, 500));
        sticks.add(new Stick(100, 450));


        this.man = new Man((int) getWidth()/2, getHeight());
        this.previousWorldUpdateTime = System.currentTimeMillis();


        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) { // Если кнопка была нажата (т.е. сейчас она зажата)
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                man.speedX = -4;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                man.speedX = 4;

            }
        }

        if(e.getID() == KeyEvent.KEY_RELEASED){
            man.speedX = 0;
        }
        return false;
    }

    void updateWorldPhysics() {
        long currentTime = System.currentTimeMillis();
        long dt = currentTime - previousWorldUpdateTime; // нашли сколько миллисекунд прошло с предыдущего обновления физики мира
        previousWorldUpdateTime = currentTime;

        for(int i = 0; i < sticks.size(); i = i + 1) {
            sticks.get(i).update(getHeight(),getWidth());
        }
        man.update(dt,getWidth());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0,0,getWidth(),getHeight());

        checkCollision(man);
        man.draw(g);

        for(int i = 0; i < sticks.size(); i = i + 1) {
            sticks.get(i).draw(g);
        }
    }

    public void checkCollision(Man m){
        if(m.speedY > 0) {
            boolean jump = false;

            for(int i = 0; i < sticks.size(); i = i + 1) {
                jump = (isJump(man,sticks.get(i))||jump);
            }
            if (m.y + m.h > getHeight()) {
                jump = true;
            }

            if(jump){
                m.speedUp();
            }

        }
    }

    public boolean isJump(Man man1, Stick stick1){
        if(((man1.x + man1.w > stick1.x)&&(man1.x < stick1.x + stick1.w))&&(man1.y + man1.h < stick1.y + stick1.h)&&(man1.y + man1.h > stick1.y)){
             return true;
        }else{
            return false;
        }
    }
}
