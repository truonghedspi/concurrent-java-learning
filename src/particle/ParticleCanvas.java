package particle;

import java.awt.*;

public class ParticleCanvas extends Canvas {

    private Particle[] particles = new Particle[0];

    ParticleCanvas(int size) {
        super.setSize(new Dimension(size, size));
    }

    protected synchronized void setParticles(Particle[] ps) {
        if (ps == null) {
            throw new IllegalArgumentException("Particles array should not be null.");
        }

        this.particles = particles;
    }

    protected synchronized Particle[] getParticles() {
        return this.particles;
    }

    @Override
    public void paint(Graphics g) {
        Particle[] ps = getParticles();

        for (int i = 0; i < ps.length; ++i) {
            ps[i].draw(g);
        }
    }

}
