import algo.Astar;
import gui.MainGui;
import model.Grid;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Grid laGrille = new Grid();  // définition de la grille

        /* On fait quelques obstacles */
        for (int i = 0; i < 20; i++) {
            laGrille.setNodeState(i, 50, 1);
        }
        for (int i = 0; i < 20; i++) {
            laGrille.setNodeState(70, i, 1);
        }

        MainGui gui = new MainGui(laGrille);  // définition de l'interface utilisateur
        // TimeUnit.SECONDS.sleep(5);  // juste pour record, étant donné que j'ai un léger délai avec mon logiciel

        /* On fait travailler l'algo jusqu'à la fin des temps! */
        while (true) {
            Astar algo = new Astar(laGrille, laGrille.getNode(randint(0, 99), randint(0, 99)), laGrille.getNode(randint(0, 99), randint(0, 99)));
            algo.run();
        }
    }

    public static int randint(int min, int max) {
        /* Simple méthode pour avoir un nombre aléatoire compris entre min(inclu) et max(exclu) */
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
}