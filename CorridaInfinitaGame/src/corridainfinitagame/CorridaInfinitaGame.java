package corridainfinitagame;

public class CorridaInfinitaGame {

    public static void main(String[] args) {

        //ativa OpenGL para remover micro travamento no Linux
        System.setProperty("sun.java2d.opengl", "true");

        //inicia a janela normal
        Janela jp = new Janela();
    }
}
