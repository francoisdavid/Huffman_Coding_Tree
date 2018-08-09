
public class HuffmanLeaf extends Node implements Comparable<HuffmanLeaf>{
    private char element;
    private int firstO;
    private String huffmanCode;

    public HuffmanLeaf(Node leftChildNode, Node rightChildNode, char character, int weight, int firstO) {
        super(leftChildNode, rightChildNode, weight);
        element = character;
        this.firstO = firstO;
    }


    public char getElement() {
        return this.element;
    }

    public int getFirstOccurrence() {
        return this.firstO;
    }

    public boolean isLeaf() {
        return true;
    }

    public String toString() {
        if(this.element < 64)
            return "'"+ this.element +"'" +"\t ASCII :0" + Integer.toBinaryString((int) this.element) + " \t Huffman Code: "+ this.huffmanCode;
        else
            return "'"+ this.element +"'" +"\t ASCII :" + Integer.toBinaryString((int) this.element) + " \t Huffman Code: "+ this.huffmanCode;
    }

    public int compareTo(HuffmanLeaf otherNode) {
        if(this.getWeight() < otherNode.getWeight())
            return -1;
        else if(this.getWeight() > otherNode.getWeight())
            return 1;
        else
            return 0;
    }

    public void setCode(String s){
        huffmanCode = s;
    }

    public String getCode(){
        return huffmanCode;
    }

}
