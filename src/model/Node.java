package model;

import static java.lang.Math.abs;

public class Node {
    private final int[] pos;
    private int state;
    private Node parent;
    private int f = 0;

    public Node(int x, int y, int state) {
        this.pos = new int[]{x, y};
        this.state = state;
    }
    public Node(int x, int y) {this(x, y, 0); }

    public void setState(int state) {this.state = state; }

    public void setParent(Node parent) {this.parent = parent; }

    public int getState() {return state; }

    public int[] getPos() {return this.pos; }

    public int getF() {return f; }

    public Node getParent() {return this.parent; }

    public void calcScores(Node endNode) {
        if (this.parent != null) {
            int g = abs(this.parent.getPos()[0] - this.pos[0]) + abs(this.parent.getPos()[1] - this.pos[1]);
            int h = abs(endNode.getPos()[0] - this.pos[0]) + abs(endNode.getPos()[1] - this.pos[1]);
            this.f = g + h;
        }
    }

    @Override
    public String toString() {return String.format("Node(%d, %d)=%d", this.pos[0], this.pos[1], this.state); }
}
