package particle;

import java.applet.Applet;

public class ParticleApplet extends Applet {

    protected Thread[] threads = null;

    protected final ParticleCanvas particleCanvas = new ParticleCanvas(100);

    @Override
    public void init() {
        add(this.particleCanvas);
    }

    private Thread makeThread(final Particle particle) {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        particle.move();
                        particleCanvas.repaint();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };


        return new Thread(runnable);
    }

    public synchronized void start() {

        int numParticle = 10;


        if (this.threads == null) {
            Particle[] particles = new Particle[numParticle];
            for (int i = 0 ; i < particles.length; ++i) {
                particles[i] = new Particle(50, 50);
            }
            this.particleCanvas.setParticles(particles);

            this.threads = new Thread[particles.length];
            for (int i =0; i < particles.length; ++i) {
                this.threads[i] = this.makeThread(particles[i]);
                this.threads[i].start();
            }
        }
    }

    public synchronized void stop() {
        if (this.threads != null) {
            for (int i = 0; i < this.threads.length; ++i) {
                this.threads[i].interrupt();
            }

            this.threads = null;
        }
    }

}
