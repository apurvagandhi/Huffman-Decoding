/*****************************************
 *Huffman.java
 *Name: Apurva Gandhi
 *Assignment: Project 3
 *Email: ahgand22@g.holyross.edu
 *Date Written: 10/26/2018
 *Program: Takes encoded file text and
 *outputs a file with decoded text
 *by decoding from binary numbers
 *Example: java Huffman
 *******************************************/
public class Huffman
{
    //Main function
    public static void main(String[] args)
    {
        System.out.println();
        System.out.println("Welcome to the Huffman Decompression program.");
        //prompts for input file name
        System.out.print("Input file: ");
        String inputPath = StdIn.readLine();
        //prompts for output file name
        System.out.print("Output file: ");
        String outputPath = StdIn.readLine();

        int[] finalCount = huffmanDecode(inputPath, outputPath);
        System.out.println("Number of bits read from input file: " + finalCount[0]);
        System.out.println("Number of characters decoded: " + finalCount[1]);
        //Calculates the average
        double average = (finalCount[0] + 0.0) / finalCount[1];
        System.out.printf("Average bits per compressed character: %.3f \n", average);
        
        System.out.println("\nThank you and have a nice day!\n");
    } //main

    public static int[] huffmanDecode (String inputFileName, String outPutFileName)
    {
        int[] summary = new int[2];//Keeps the summary of the character decoded
        In inputFile = new In(inputFileName); //Open the encoded message
        Out outputFile = new Out (outPutFileName);//Open the output file

        String targetString = "";// Creates an empty target string
        char binaryCh; //To read the binary character from input file
        char decodedCh = 0;// To hold the decoded character
        while(inputFile.hasNextLine())
        {
            //reads the characetr form the input file
            binaryCh = inputFile.readChar();
            if (binaryCh == '0' || binaryCh == '1')
            {
                targetString = targetString + binaryCh;
                // decodes the binary character if possible
                decodedCh = lookupCode("code-table.txt", targetString);
                summary[0] ++;//increments the bit character counter
                //will print out to the output file if the character is found
                if(decodedCh != Character.UNASSIGNED)
                {
                    outputFile.print(decodedCh);
                    //Resets the target string
                    targetString = "";
                    summary[1]++;//increments the decoded character counter 
                }
            }//end of if condition
        }//end of while
        //Error checking for invalid data
        if(!(targetString.isEmpty()))
        {
            System.out.println("Input file contains invalid data");
            System.exit(1);
        }
        return summary;
    }//end of huffmanDecode
    
    //Returns the character value from an int value given a string 
    public static char lookupCode (String codeFilename, String target)
    {
        //Reads from the file
        In inFile = new In(codeFilename);// importing a file given its location
        char ch = Character.UNASSIGNED;
        //It will read untill the file has next line
        while(inFile.hasNextLine())
        {
            String s = inFile.readString();//Reads the string from the file
            int i = inFile.readInt();//Reads the integer from the file
            //Checking if the string from file equals the target
            if(s.equals(target))
            {
                //Convert integer value to Ascii character
                return (char)i;
            }
            s = inFile.readLine();
        }//end of while loop
        return ch; 
    }//end of lookupCode function
    
}// end of class Huffman
