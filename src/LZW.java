
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author FCI Student
 */
public class LZW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int choice;
        String text;
        System.out.println("-To use Files press 1 \n-To enter the string press 2");
        System.out.print("Please enter your choice:");
        Scanner var = new Scanner(System.in);
        choice = var.nextInt();
        if (choice == 1)
        {
            text = new Scanner (new File("input.txt"))
                    .useDelimiter("\n").next();
            System.out.println("Your text is: " + text);
        }
        else
        {
            System.out.print("Please enter a text to compress: ");
            Scanner scanner = new Scanner(System.in);
            text = scanner.nextLine();
        }
        
        Vector<String> dectionary = new Vector<String>();
        Vector<Integer> compTags = new Vector<Integer>();
        
        // filling the dictionary to 127 symbol
        String decTmp1="";
        for (int i = 0; i < 128; i++)
        {
            decTmp1="";
            decTmp1+=(char)i;
            dectionary.add(decTmp1);
        }
        // Printing the dectionary "right"
//        for (int i = 0; i < 128; i++)
//        {
//            System.out.print(dectionary.get(i) + "-");
//        }
        
        // Compression code
        String compTmp = "";
        
        for (int i = 0; i < text.length(); i++)
        {
            compTmp += text.charAt(i);
            
            if (dectionary.contains(compTmp) && i == text.length() -1)
            {
                compTags.add(dectionary.indexOf(compTmp));
            }
            else if (!dectionary.contains(compTmp))
            {
                dectionary.add(compTmp);
                i--;
               compTmp = compTmp.substring(0, compTmp.length()-1);
//                String seprate = compTmp;
//                compTmp="";
//                for ( int w = 0; w < compTmp.length() - 1; w++)
//                {
//                    compTmp += seprate.charAt(w);
//                }
                compTags.add(dectionary.indexOf(compTmp));
                compTmp = "";
            }
        }
        
        //Printing the dectionary
        for (int i = 128; i < dectionary.size(); i++)
        {
            System.out.print(dectionary.get(i) + "-");
        }
        
        // Printing the tags
        for (int i = 0; i < compTags.size(); i++)
        {
            System.out.print(compTags.get(i) + " -- ");
        }
        System.out.println("\n\n");
//        
        
        
        // Decompression code
        String compResult = "";
        Vector<String> newDectionary = new Vector<String>();
        // filling the new dictionary to 127 symbol
        String decTmp2;
        Vector<String> pointer = new Vector<String>();
        for (int i = 0; i < 128; i++)
        {
            decTmp2 = "";
            decTmp2+=(char)i;
            newDectionary.add(decTmp2);
        }
        
        String prev = "", current = "", decompTmp = "";
        for (int i = 0; i < compTags.size(); i++)
        {
            if (compTags.get(i) > newDectionary.size())
            {
                compResult += pointer.get(i-1) + pointer.get(i-1).charAt(0);
                newDectionary.add(pointer.get(i-1) + pointer.get(i-1).charAt(0));
                pointer.set(i-1, compResult);
            }
            else
            {
                compResult += newDectionary.get(compTags.get(i));
                pointer.add( newDectionary.get(compTags.get(i)) );
                if (i == 0)
                {
                	decompTmp = "" + pointer.get(i).charAt(0);
                }
                else
                {
                	decompTmp = pointer.get(i-1) + pointer.get(i).charAt(0);
                }
                
            }
            if(!newDectionary.contains(decompTmp))
            {
                newDectionary.add(decompTmp);
            }
            
        }
        System.out.println(compResult);
    }
}
