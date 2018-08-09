
public class HuffmanInternalNode extends Node{

    // Constructors of multiple node tree.
    public HuffmanInternalNode (Node l, Node r) {
        super(l, r, (l.getWeight() + r.getWeight()));
    }

    public boolean isLeaf() {
        return false;
    }

    // Return the largest integer value possible to be larger than any other node (that is not internal).
    public int getFirstOccurrence() {
        return 2147483647;
    }

    public String toString() {
        return "Internal Node with weight: " + this.getWeight();
    }

    public String print() {
        return "Internal Node with weight: "+ this.getWeight() +" \t Left Child: "+this.getLeftChild()+ " \t Right Child: "+this.getRightChild();
    }
}
