package algo;

import model.Node;
import model.Grid;

import java.util.ArrayList;
import java.util.Arrays;

public class Astar {
    private final Grid grid;
    private final ArrayList<Node> openList;
    private final ArrayList<Node> closedList;

    private final Node startNode;
    private final Node endNode;

    public Astar(Grid grid, Node startNode, Node endNode) {
        this.grid = grid;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.startNode = startNode;
        this.endNode = endNode;

        this.openList.add(startNode);  // la première node est celle d'où on part
    }

    public ArrayList<int[]> run() throws InterruptedException {
        while (!this.openList.isEmpty()) {  // si la liste se vide, il n'y a pas de solution
            Node studiedNode;
            /* Soit la liste fermée contient une node, soit on prend la première de la liste ouverte */
            if (this.closedList.size() != 0) {
                studiedNode = this.closedList.get(this.closedList.size()-1);
            } else {
                studiedNode = this.openList.get(0);
            }

            Node[] successors = this.grid.getVoisines(studiedNode.getPos());  // on définit les successeurs d'une node
            for (Node successor : successors) {
                /* Simple vérification, si le successeur est intraversable(==1) soit elle est déjà dans la liste fermée, elle est ignorée */
                if (successor.getState() == 1 || isInList(successor, this.closedList)) {
                    continue;
                }
                /* Si la node est déjà dans la liste ouverte, et que le successeur a un meilleur score, on le remplace */
                if (this.isInList(successor, this.openList) && successor.getF() < this.openList.get(this.openList.indexOf(successor)).getF()) {
                    successor.setParent(studiedNode);
                } else {
                    Node aAjouter = this.grid.getNode(successor.getPos());
                    aAjouter.setParent(studiedNode);
                    this.openList.add(aAjouter);
                    this.grid.setNodeState(successor.getPos(), 3);  // juste pour l'affichage
                }
            }
            Node best = this.findMinScore();  // on cherche la node ayant le meilleur score dans la liste ouverte
            this.openList.removeIf(node -> node.getPos() == best.getPos());  // retire cette node de la liste ouverte
            this.closedList.add(best);  // et l'ajoute à la liste fermée
            this.grid.setNodeState(best.getPos(), 2);  // juste pour l'affichage
            /* Beaucoup de texte pour dire: "si la meilleure node est la node de fin, on retourne un résultat" */
            if (Arrays.equals(this.closedList.get(this.closedList.size() - 1).getPos(), this.endNode.getPos())) {
                return this.reconstruct();
            }
        }
        return null;  // pas de résultat
    }

    private Node findMinScore() {
        Node res = this.openList.get(0);
        for (Node node : this.openList) {
            node.calcScores(this.endNode);
            if (node.getF() < res.getF()) {
                res = node;
            }
        }
        return res;
    }

    private boolean isInList(Node zeNode, ArrayList<Node> list) {
        for (Node node : list) {
            if (zeNode.getPos() == node.getPos()) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<int[]> reconstruct() {
        /* Check tous les parents en partant de la dernière node de la liste fermée(celle qui est à destination donc) */
        ArrayList<int[]> res = new ArrayList<>(){};
        Node actualNode = this.closedList.get(this.closedList.size()-1);
        while (actualNode.getPos() != this.startNode.getPos()) {
            res.add(actualNode.getPos());
            this.grid.setNodeState(actualNode.getPos(), 4);
            actualNode = actualNode.getParent();
        }
        return res;
    }
}
