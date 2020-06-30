package £ukaszPiotrowski;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Test extends JFrame{

	private static final long serialVersionUID = 1L;

		JMenuBar menuBar;
		JMenu file,help;
		JMenuItem clear,open,close,autor;
		JButton findd,searchh;
		JTextArea editorPane;
		JPanel wordPanel,buttonPanel;
		JTextField find, search;
		String tekst, findText,replaceText;
		JScrollPane scrollPane;
		JLabel i, na;
     /*
      * Constructor frame
      */
	public Test() {
		super("Wyszukiwarka tekstów");
		setSize(600,300);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("File");
		help = new JMenu("Help");
		menuBar.add(file);
		menuBar.add(help);
		open = new JMenuItem("Open");		
		clear = new JMenuItem("New");
		close = new JMenuItem("Close");
		autor = new JMenuItem("Autor");
		file.add(clear);
		file.add(open);
		file.addSeparator();
		file.add(close);
		help.add(autor);
		/*
		 * Menuitems actionListener
		 */
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Otwieranie nowego pliku tekstowego
				 */
				editorPane.setText("");
				OpenFile();
			}	
		});	
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Clear text
				 */
				editorPane.setText("");
			}			
		});		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Exit
				 */
				System.exit(0);
			}		
		});
		autor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ICopyrights
				 */
				JOptionPane.showMessageDialog(null, "£ukasz Piotrowski 298122", "Copyrights", JOptionPane.INFORMATION_MESSAGE);
			}			
		});		
		wordPanel = new JPanel();
		/*
		 * Text Panel
		 */
		wordPanel.setLayout(new BorderLayout());
		editorPane = new JTextArea();
		scrollPane = new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		wordPanel.add(scrollPane, BorderLayout.CENTER);
		
		/*
		 * Button Panel
		 */
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		findd = new JButton("Znajdz");
		findd.addActionListener(new ActionListener() {
			/*
			 * Finding word in text
			 */
			public void actionPerformed(ActionEvent e) {
				findText = find.getText();
			}
			
		});
		searchh = new JButton("Zamieñ");
		searchh.addActionListener(new ActionListener() {
			/*
			 * Replace text
			 */
			public void actionPerformed(ActionEvent e) {
				replaceText= search.getText();
				RepalceText(findText, replaceText);
			}
			
		});
		i = new JLabel("i");
		na = new JLabel("na");
		find = new JTextField("", 10);
		search = new JTextField("", 10);
		buttonPanel.add(findd);
		buttonPanel.add(find);
		buttonPanel.add(i);
		buttonPanel.add(searchh);
		buttonPanel.add(na);
		buttonPanel.add(search);
		add(wordPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
	}
	
	
	/*
	 * Loading .txt file (UTF8 standard) with JFileChooser
	 */
	public void OpenFile() {

		JFileChooser plik = new JFileChooser();
		plik.setFileFilter(new FileNameExtensionFilter("Text Files", "txt" ));
        if(plik.showDialog(null,"Choose file") == JFileChooser.APPROVE_OPTION) {
        	
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(plik.getSelectedFile()), StandardCharsets.UTF_8))) {
            	editorPane.read(new BufferedReader(new InputStreamReader(new FileInputStream(plik.getSelectedFile()), StandardCharsets.UTF_8)), null);
  
            } catch (IOException exp) {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(Test.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	
	/*
	 * Method that replace text at JTextArea 
	 */
	public void RepalceText(String a, String b) {
		tekst = tekst.replace(a, b);
		editorPane.setText(tekst);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 30, TimeUnit.SECONDS);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
