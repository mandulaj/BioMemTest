import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.io.*;



public class MainFrame{
	
	int score = 0;
	public int Level = 3;
	int lives = 5;
	String text;
	int maxLevel = 3;
	
	JLabel label;
	JLabel scoring;
	JTextField field;
	JPanel p;
	JPanel p1;
	JFrame window;
	JButton b1;

	public static void main(String[] args) {
		newQuestionc newQuestionm = new newQuestionc();
		MainFrame frame = new MainFrame();
		frame.window();
		
		}
	
	
	public void window(){
		//Font
		Font big = new Font("serif",Font.BOLD,20);
		//JLabel
		label = new JLabel();
		label.setText("");
		label.setFont(big);
		scoring = new JLabel();
		
		//JFrame
		window = new JFrame("Memory Test");
		window.setVisible(true);
		window.setSize(300,300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		//JPanel
		p = new JPanel();
		p.setBackground(Color.gray);
		
		p1 = new JPanel();
		p.setBackground(Color.white);
		//JButton
		b1 = new JButton("New");
		b1.addActionListener(new b1());
		
		//JMenuBar
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(new saveAction());
		
		fileMenu.add(saveMenu);
		menuBar.add(fileMenu);
		window.setJMenuBar(menuBar);
		
		field = new JTextField(10);
		
		
		
		p1.add(BorderLayout.NORTH,label);
		p.add(BorderLayout.NORTH,field);
		p.add(BorderLayout.SOUTH,b1);
		
		field.addActionListener(new ActionText());
		
		window.add(BorderLayout.NORTH,p1);
		window.add(BorderLayout.SOUTH,p);
 	}
	
	public void dispQuest(int level1){
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.print("Yes");
					b1.requestFocus();
					Thread.sleep(3500);
					field.requestFocus();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				label.setText("");
				field.setText("");
				
			}
			
		});
		
		text = newQuestionc.newQuestion(level1);
		label.setText(text);
		t1.start();
	}
		
	public class b1 implements ActionListener {
		public void actionPerformed(ActionEvent a){
			Level = 3;
			score = 0;
			lives = 5;
			maxLevel = 3;
			dispQuest(Level);
			field.requestFocus();
			
		}
	}
	
	public class ActionText implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String input = field.getText();
			field.setText("");
			boolean res = check(input);
			if (lives > 0){
				if(maxLevel < Level){
					maxLevel = Level;
				}
				dispQuest(Level);
				field.requestFocus();
			}else{
				String sco = Integer.toString(score);
				label.setText("Game Over: " + sco+" Max Level "+maxLevel);
				System.out.println(score);
			}
		}
	}
	
	public class saveAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent a) {
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(window);
			saveFile(fileSave.getSelectedFile());
			
		}
		
		
	}
	
	public boolean check(String in){
		if (text.equals(in)){
			score += Level*5+10;
			Level += 1;
			return true;
		}else{
			Level -= 1;
			lives -= 1;
			return false;
		}
	}


	public void saveFile(File file) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(score+", "+maxLevel+"\n");
			writer.close();
		}catch(IOException ex){
			System.out.print("Could not write file");
			ex.printStackTrace();
		}
		
	}	
}



