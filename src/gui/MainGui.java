package gui;

import model.Grid;
import view.ViewGrid;

import javax.swing.*;

public class MainGui extends JFrame {
    public MainGui(Grid grid) {
        super("ASTAR");

        this.add(new ViewGrid(grid));

        /* Toujours à c/c! */
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
