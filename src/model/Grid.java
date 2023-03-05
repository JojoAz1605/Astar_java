package model;

import utils.AbstractModeleEcoutable;

import java.util.ArrayList;

public class Grid extends AbstractModeleEcoutable {
    public static final int GRID_SIZE = 100;  // Le nombre de cases de la grille(elle est carrée)
    private final ArrayList<Node> grid = new ArrayList<>(GRID_SIZE * GRID_SIZE); // création de l'array qui va stocker la grille

    public Grid() {
        /* Initialisation à des nœuds null */
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                this.grid.add(new Node(x, y));
            }
        }
    }

    public Node getNode(int x, int y) {
        /* Simple récupération d'une node, avec vérification du dépassement de la grille */
        if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
            return this.grid.get((y * GRID_SIZE) + x);
        }
        return new Node(x, y, 1);
    }

    public Node getNode(int[] pos) {return this.getNode(pos[0], pos[1]); }

    public Node[] getVoisines(int x, int y) {
        return new Node[]{
                this.getNode(x + 1, y),
                this.getNode(x - 1, y),
                this.getNode(x, y + 1),
                this.getNode(x, y -1)
        };
    }

    public void setNodeState(int x, int y, int state) {
        this.getNode(x, y).setState(state);
        this.notifChangement();
    }

    public void setNodeState(int[] pos, int state) {this.setNodeState(pos[0], pos[1], state);}

    public Node[] getVoisines(int[] pos) {return this.getVoisines(pos[0], pos[1]); }
}
