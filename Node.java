
public class Node {
    // Variable, the two children of the internal node.
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int weight;

    // Copy Constructor.
    public Node(Node c) {
        this.leftChild = c.getLeftChild();
        this.rightChild = c.getRightChild();
        this.parent = c.getParent();
        this.weight = c.getWeight();
    }

    // Constructor.
    public Node(Node l, Node r, int weight) {
        this.leftChild = l;
        this.rightChild = r;
        this.weight = weight;
        this.parent = null;
    }

    public Node(int weight, Node p) {
        this.weight = weight;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = p;
    }

    // Constructor of leaf node.
    public Node() {
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // Return false, since it is not a leaf.
    public boolean isLeaf() {
        return false;
    }

    public void setWeight(int v) {
        this.weight = v;
    }

    // Return the right child leaf.
    public Node getRightChild() {
        return this.rightChild;
    }


    // Return the left child leaf.
    public Node getLeftChild() {
        return this.leftChild;
    }


    public void setLeftChild(Node l) {
        if(l!= null) {
            this.leftChild = l;
            l.setParent(this);
        }else
            this.leftChild = null;
    }

    public void setRightChild(Node r) {
        if(r != null) {
            this.rightChild = r;
            r.setParent(this);
        }else
            this.rightChild = null;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getGrandParent() {
        if(this.parent != null)
            return this.parent.parent;
        else return null;
    }

    public void setParent(Node n) {
        this.parent  = n ;
    }
    public String print() {
        return " ";
    }

    public String toString() {
        return "Node: "+this.weight ;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getFirstOccurrence() {return 0;}


}
