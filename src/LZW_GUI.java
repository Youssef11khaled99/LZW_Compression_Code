package LZ78;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.ScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import javafx.util.Pair;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Form1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1 window = new Form1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Form1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(40, 45, 278, 195);
		frame.getContentPane().add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(386, 42, 291, 198);
		frame.getContentPane().add(editorPane_1);
		
		JLabel lblEnterText = new JLabel("Enter Text : ");
		lblEnterText.setBounds(41, 25, 115, 14);
		frame.getContentPane().add(lblEnterText);
		
		JLabel lblCompressedText = new JLabel("Compressed Text : ");
		lblCompressedText.setBounds(386, 25, 115, 14);
		frame.getContentPane().add(lblCompressedText);
		
		JCheckBox chckbxDealWithFiles = new JCheckBox("Deal with files");
		chckbxDealWithFiles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxDealWithFiles.isSelected())
				{
					File originalFile = new File();
			        File textFile = new File();
					String record="";
		            textFile.openoutputFile("TextFile.txt");
		            record += textFile.readText();
		            editorPane.setText(record);
					
				}				
				
			}
		});
		chckbxDealWithFiles.setBounds(48, 313, 142, 23);
		frame.getContentPane().add(chckbxDealWithFiles);
		
		JCheckBox chckbxDcompressText = new JCheckBox("Decompress text after Compression");
		chckbxDcompressText.setBounds(272, 331, 213, 23);
		frame.getContentPane().add(chckbxDcompressText);
		
		JButton btnNewButton = new JButton("Compress Text");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// write your code here
			        File originalFile = new File();
			        File textFile = new File();
			        File fileTags = new File();
			        LZ x = new LZ();

//			        System.out.println("1- To Enter String:");
//			        System.out.println("2- To Get the string from file:");
//			        System.out.println("3- Decompress from file:");

			        Scanner input = new Scanner(System.in);
			        Vector Dictionary = new Vector();
			        Vector decompress_Vector = new Vector();
			        Vector<Pair<Integer, Character>> tags = new Vector<Pair<Integer, Character>>(0);
			        System.out.println(chckbxDealWithFiles.isSelected());
			        if(chckbxDealWithFiles.isSelected()==false)
			        {
			            originalFile.openInputFile("OriginalFile.txt");
			            fileTags.openInputFile("TagsFile.txt");
			            //System.out.print("Enter string: ");
			            String text=editorPane.getText();
			            originalFile.addtext(text);
			            originalFile.closeFile();


			            x.compressed(fileTags,text, Dictionary, tags);
			            x.decompressd(tags, decompress_Vector);
			        }

			        else if(chckbxDealWithFiles.isSelected()==true)
			        {
			            String record="";
			            textFile.openoutputFile("TextFile.txt");
			            fileTags.openInputFile("TagsFile.txt");
			            record += textFile.readText();
			            System.out.println(record);
			            editorPane.setText(record);
			            x.compressedFromFile(fileTags,record, Dictionary, tags);
			            x.decompressd(tags, decompress_Vector);
			            fileTags.closeFile();
			            
			        }
			       fileTags.openoutputFile("TagsFile.txt");
                   String text = fileTags.readText();
                   System.out.println(text);
                   editorPane_1.setText(text);

//			        else if(chckbxDcompressText.isSelected())
//			        {
//			            fileTags.openoutputFile("TagsFile.txt");
//			            String text = fileTags.readText();
//			            System.out.println(text);
//			            x.decompressdFromFile(text, decompress_Vector);
//			        }



			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(272, 274, 197, 55);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
	}
}
