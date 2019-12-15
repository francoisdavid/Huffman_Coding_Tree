// --------------------------------------------------------
// June 16th, 2018
// Written by: Francois David
// For COMP 352 – Summer 2018
// Purpose - Create a Huffman Coding Tree with a given text file to train and encode a specific String. Hash tables were not allowed in this assignment.
// --------------------------------------------------------
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanMain {

    public static void main(String[] args) {
        // Declare the file.
        FileReader file;
        try {
            // Create a FileReader with the text file inputed in the command line.
            file = new FileReader(args[0]);
            Scanner scan = new Scanner(file);
            // Hash table to hold the frequency of characters.
            int[] frequTable = new int[127];
            // Hash table to hold the first occurrence of each characters.
            int[] firstOccuTable = new int[127];
            // Integer to hold the index of the first occurrence.
            int occurrence = 0;
            int letters = 0;
            while(scan.hasNextLine()) {
                String temp = scan.nextLine();
                for(int j=0; j < temp.length();j++) {
                    frequTable[temp.charAt(j)]++;
                    // If the entry is null with the key, enter the first occurrence.
                    if(frequTable[temp.charAt(j)]==1) {
                        firstOccuTable[temp.charAt(j)] = occurrence;
                        letters++;
                    }
                    // Increment the occurrences.
                    occurrence++;
                }
            }
            // Create an array of HuffmanNode that is the size of the table recorded previously.
            Node[] nodeArray = new Node[letters];
            // Integer used to address the index of the nodeArray.
            int i = 0;
            for(int j = 0 ; j < frequTable.length; j++) {
                if(frequTable[j] > 0) {
                    nodeArray[i] = new HuffmanLeaf(null, null, (char) j, frequTable[j], firstOccuTable[j]);
                    i++;
                }
            }

            // Build the tree based on the node array obtained.
            HuffmanTree ht = new HuffmanTree(nodeArray);
            ht.buildTree();
            ht.getCodes();
            // Print the code obtained by the Huffman Coding tree.
            ht.printCode();

            // Prompt the user for a string to be encoded.
            System.out.print("\nProgram is ready to encode a string of your choice (with characters used to create tree) : ");
            Scanner keyBoard = new Scanner(System.in);
            String toEncode =  keyBoard.nextLine();
            // Encode the String in both versions (Huffman and ASCII)
            String codedHVersion = ht.encode(toEncode);
            String codedAVersion = convertToAsciiBin(toEncode);
            // Display the results.
            System.out.println("Huffman encoded version has "+codedHVersion.length()+" bits : "+ codedHVersion);
            System.out.println("ASCII encoded version has "+codedAVersion.length()+" bits   : "+ codedAVersion);
            System.out.println("In this case, Huffman used " +((double)codedHVersion.length()/codedAVersion.length()*100)+ "% of the bits used by the ASCII.");
            // Close the file.
            file.close();
        }catch(IOException e) {
            System.out.println("Error Occur !! IOEXCEPTION");
            e.printStackTrace();
        }catch(Exception e) {
            System.out.println("General Exception !!");
            e.printStackTrace();
        }
    }

    // Method to convert the Specific string into ASCII for comparisons purposes.
    public static String convertToAsciiBin(String s){
        String encoded = "";
        for(int i = 0 ; i < s.length();i++){
            if(s.charAt(i)> 64)
                encoded +=  Integer.toBinaryString((int) s.charAt(i));
            else
                encoded += "0"+ Integer.toBinaryString((int) s.charAt(i));
        }
        return encoded;
    }
}
