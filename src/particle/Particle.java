package particle;

import java.awt.*;
import java.util.Random;

public class Particle {
    private int x;

    private int y;

    protected final Random rng = new Random();

    public Particle(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
    }

    public synchronized void move() {
        this.x += rng.nextInt(10) - 5;
        this.y -= rng.nextInt(20) - 10;
    }

    public void draw(Graphics g) {
        int lx, ly;
        synchronized (this) {
            lx = this.x;
            ly = this.y;
        }

        g.drawRect(lx, ly, 10, 10);
    }
}
