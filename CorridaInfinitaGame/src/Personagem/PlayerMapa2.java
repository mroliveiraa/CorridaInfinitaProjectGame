package Personagem;

import java.awt.*;

public class PlayerMapa2 extends Player {

    private final int distanciaChaoMapa2 = 560;

    public PlayerMapa2(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void Update() {

        // mesma lógica do Player normal
        if (estaPulando) {

            velocidadeY += 1.5;
            y += velocidadeY;

            // só muda o chão!
            if (y >= distanciaChaoMapa2) {
                y = distanciaChaoMapa2;
                estaPulando = false;
                velocidadeY = 0;
            }
        }
    }
}
