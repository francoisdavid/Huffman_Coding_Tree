
public class HuffmanTree {
    // Main variables.
    private Node Root;
    private Node[] nodes;
    private int queueSize;
    // Code array (since a Hash Map) could not be used in this assignment.
    private HuffmanLeaf[] codeArray = new HuffmanLeaf[127];

    public HuffmanTree(Node[] nodes){
        //super(null);
        this.nodes = nodes;
        queueSize = nodes.length-1;
        Heapify();
    }

    // Build the tree using the Heapify function invoked inside the the add and removeSmallest methods.
    public void buildTree() {
        Node temporary1, temporary2, temporary3;
        while(queueSize > 1 ) {
            temporary1 = removeSmallest();
            temporary2 = removeSmallest();
            temporary3 = new HuffmanInternalNode(temporary1, temporary2);
            add(temporary3);
            if(queueSize == 1)
                Root = new HuffmanInternalNode(removeSmallest(), removeSmallest());
        }
    }
    public void printCode() {
        for (int k = 0; k < codeArray.length; k++) {
            if (codeArray[k] != null)
                System.out.println(codeArray[k]);
        }
    }

    // Encode the String passed by the user.
    public String encode(String s){
        String encodedVersion = "";
        for(int i = 0 ; i < s.length() ; i++){
            encodedVersion +=  codeArray[s.charAt(i)].getCode();
        }
        return encodedVersion;
    }


    // To invoke the codes
    public void getCodes() {
        createCodes(Root, "");
    }

    public void createCodes(Node n, String s) {
        if(n.isLeaf()) {
            HuffmanLeaf h = (HuffmanLeaf) n;
            h.setCode(s);
            codeArray[h.getElement()] = h;

        }
        else {
            createCodes(n.getLeftChild(), s+"0");
            createCodes(n.getRightChild(), s+"1");
        }
    }

    public void print() {
        System.out.println();
        for(int i = 0 ; i < queueSize;i++) {
            System.out.println(i+"\t "+nodes[i]);
        }
    }

    public Node getRoot() {
        return this.Root;
    }

    public void print(Node t) {
        if(!(t instanceof HuffmanLeaf) ) {
            System.out.println("L " + t.getLeftChild());
            System.out.println("R "+t.getRightChild());
            if(t.getLeftChild() != null) {
                print(t.getLeftChild());
            }
            if(t.getRightChild() != null) {
                print(t.getRightChild());
            }

        }
    }

    // Add a node to the priority queue and reHeapify.
    public void add(Node n) {
        queueSize++;
        nodes[queueSize] = n;
        Heapify();
    }

    // Remove the smallest element in the priority queue and reHeapify.
    public Node removeSmallest() {
        Node temp = nodes[0];
        nodes[0] = nodes[queueSize];
        queueSize--;
        Heapify();
        return temp;
    }


    // I used my Heapify method from assignment 2, with a few modifications.
    public void Heapify() {
        for(int i = (queueSize) / 2 ; i >= 0; i--) {
            int index = i;
            // If one of the children is larger, find the largest and swap it & increment the index.
            while( (index*2+1 <= queueSize)) {
                // Check if 2 children.
                if(2*index+2 <= queueSize) {
                    if (nodes[index].getWeight() > nodes[2 * index + 1].getWeight() | nodes[index].getWeight() > nodes[2 * index + 2].getWeight()) {
                        if (nodes[2 * index + 1].getWeight() == nodes[2 * index + 2].getWeight()) {
                            if (nodes[2 * index + 1].getFirstOccurrence() > nodes[2 * index + 2].getFirstOccurrence()) {
                                Node temp = nodes[index];
                                nodes[index] = nodes[2 * index + 1];
                                nodes[2 * index + 1] = temp;
                                index = 2 * index + 1;
                            } else {
                                Node temp = nodes[index];
                                nodes[index] = nodes[2 * index + 2];
                                nodes[2 * index + 2] = temp;
                                index = 2 * index + 2;
                            }
                        } else if (nodes[2 * index + 1].getWeight() < nodes[2 * index + 2].getWeight()) {
                            Node temp = nodes[index];
                            nodes[index] = nodes[2 * index + 1];
                            nodes[2 * index + 1] = temp;
                            index = 2 * index + 1;
                        } else {
                            Node temp = nodes[index];
                            nodes[index] = nodes[2 * index + 2];
                            nodes[2 * index + 2] = temp;
                            index = 2 * index + 2;
                        }

                        // Clause to check if the parent and child are equal, check if the firstOccurence is lower, if so, change them.
                    } else if (nodes[index].getWeight() == nodes[2 * index + 1].getWeight() || nodes[index].getWeight() == nodes[2 * index + 2].getWeight()) {
                        // If both are the children are equal take the one with the lowest occurrence;
                        if (nodes[2 * index + 1].getWeight() == nodes[2 * index + 2].getWeight()) {
                            if (nodes[2 * index + 2].getFirstOccurrence() > nodes[2 * index + 1].getFirstOccurrence() && nodes[2 * index + 2].getFirstOccurrence() > nodes[index].getFirstOccurrence()) {
                                Node temp = nodes[index];
                                nodes[index] = nodes[2 * index + 2];
                                nodes[2 * index + 2] = temp;
                                index = 2 * index + 2;
                            } else if (nodes[2 * index + 1].getFirstOccurrence() > nodes[index].getFirstOccurrence()) {
                                Node temp = nodes[index];
                                nodes[index] = nodes[2 * index + 1];
                                nodes[2 * index + 1] = temp;
                                index = 2 * index + 1;
                            } else
                                break;


                        } else if (nodes[index].getFirstOccurrence() <= nodes[2 * index + 1].getFirstOccurrence() && nodes[index].getWeight() == nodes[2 * index + 1].getWeight()) {
                            Node temp = nodes[index];
                            nodes[index] = nodes[2 * index + 1];
                            nodes[2 * index + 1] = temp;
                            index = 2 * index + 1;
                        } else if (nodes[index].getFirstOccurrence() <= nodes[2 * index + 2].getFirstOccurrence() && nodes[index].getWeight() == nodes[2 * index + 2].getWeight()) {
                            Node temp = nodes[index];
                            nodes[index] = nodes[2 * index + 2];
                            nodes[2 * index + 2] = temp;
                            index = 2 * index + 2;

                        } // If the children are smaller, break from the while loop.
                        else break;

                    }else break;


                    // If only one children, check if the value of the children is not larger, if so swap it.
                } else{
                    if (nodes[2 * index + 1].getWeight() < nodes[index].getWeight()) {
                        Node temp = nodes[index];
                        nodes[index] = nodes[2 * index + 1];
                        nodes[2 * index + 1] = temp;
                    }
                    // Check if equal and lower first occurrence.
                    else if (nodes[index].getFirstOccurrence() <= nodes[2 * index + 1].getFirstOccurrence() && nodes[index].getWeight() == nodes[2 * index + 1].getWeight()) {
                        Node temp = nodes[index];
                        nodes[index] = nodes[2 * index + 1];
                        nodes[2 * index + 1] = temp;
                        index = 2 * index + 1;
                    }
                    // Break since it is the last children.
                    else break;

                }
            }
        }
    }
}
