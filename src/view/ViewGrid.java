package view;

import model.Grid;
import utils.EcouteurModele;
import utils.ModeleEcoutable;

import javax.swing.*;
import java.awt.*;

public class ViewGrid extends JPanel implements EcouteurModele {
    private static final int TAILLE_CASE = 5;  // la taille d'une case en pixels

    private final Grid grid;  // la vue d'une grid, donc on a une grid en attribut
    public ViewGrid(Grid grid) {
        this.grid = grid;
        this.grid.ajoutEcouteur(this);
        this.setPreferredSize(new Dimension(Grid.GRID_SIZE * TAILLE_CASE, Grid.GRID_SIZE * TAILLE_CASE));  // pour avoir la taille définitive de la fenêtre
    }

    @Override
    public void paintComponent(Graphics g) {
        /* Affichage, probablement moyen de simplifier mais flemme */
        super.paintComponent(g);
        for (int x = 0; x < Grid.GRID_SIZE; x++) {
            for (int y = 0; y < Grid.GRID_SIZE; y++) {
                if (this.grid.getNode(x, y).getState() == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                } else if (this.grid.getNode(x, y).getState() == 2) {
                    g.setColor(Color.RED);
                    g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                } else if (this.grid.getNode(x, y).getState() == 3) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                } else if (this.grid.getNode(x, y).getState() == 4) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                }
            }
        }
    }

    @Override
    public void modeleMisAJour(ModeleEcoutable source) {
        this.repaint();
    }
}
