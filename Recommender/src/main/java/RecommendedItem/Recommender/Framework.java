package RecommendedItem.Recommender;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Framework {
	private JFrame mainFrame;
	private JRadioButton[] scores = new JRadioButton[6];
	private JLabel movieMsg;
	private JButton next;
	private JTextArea systemout;
	private ButtonGroup btGroup;
	private boolean start=false,selectYear=false;
	private short count=0;
	//private JButton clear_all;
	//private JMenuBar menuBar;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Framework window = new Framework();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				App.loadmoviedata();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Framework() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame("Recommender");
		mainFrame.setBounds(100, 100, 450, 420);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(3,1,5,5));
		btGroup = new ButtonGroup();
		JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		scores[0]=new JRadioButton("Don't know");
		btGroup.add(scores[0]);
		ButtonPanel.add(scores[0]);
		scores[0].setVisible(false);
		for(int i=1;i<6;i++){
			scores[i]=new JRadioButton(Integer.toString(i));
			scores[i].setVisible(false);
			btGroup.add(scores[i]);
			ButtonPanel.add(scores[i]);
		}
		movieMsg = new JLabel("Please rate five movies. press \"Start\" to start:");
		next = new JButton("Start");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!start){
					start=true;
					selectYear=true;
					movieMsg.setText("Please select on-screen year of the movies to be recommended.");
					scores[0].setText("Before 1990");
					scores[1].setText("1991-2000");
					scores[2].setText("2001-2005");
					next.setText("Next");
					for(int x=0;x<3;x++)
						scores[x].setVisible(true);
				}
				else if(selectYear){
					for(int i=0;i<3;i++)
						if(scores[i].isSelected())
							try {
								App.setSelectSet(i);
								selectYear=false;
								btGroup.clearSelection();
								scores[0].setText("Don't know");
								for(int j=1;j<6;j++)
									scores[j].setText(Integer.toString(j));
								for(int x=3;x<6;x++)
									scores[x].setVisible(true);
								movieMsg.setText("Please rate: \""+App.getRandomMovieName()+"\" in scale (1-5).");
								break;
							} catch (Exception e) { System.out.println(e.getMessage()); }
				}
				else{
				for(int i=0;i<6;i++)
					if(scores[i].isSelected()){
						if(i!=0){
							App.storeUserInfo(i);
							count++;
							//System.out.println("No."+count+" rate info has been recorded.");
						}
						btGroup.clearSelection();
						if(count!=5)
							movieMsg.setText("Please rate: \""+App.getRandomMovieName()+"\" in scale (1-5).");
						else{
							next.setVisible(false);
							(new Runanalysis()).start();
						}
						break;
					}
				}
				
			}
		});
		systemout = new JTextArea();
		systemout.setLineWrap(true);
		systemout.setWrapStyleWord(true);
		systemout.setEditable(false);
		DefaultCaret caret = (DefaultCaret)systemout.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		PrintStream printStream = new PrintStream(new CustomOutputStream(systemout));
		System.setOut(printStream);
		JScrollPane scroll = new JScrollPane(systemout);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//System.setErr(printStream);
		ButtonPanel.add(next);
		mainFrame.add(movieMsg);
		mainFrame.add(ButtonPanel);
		mainFrame.add(scroll);
	}
}

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}

class Runanalysis extends Thread {

    public void run() {
        try {
			App.recmain();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    }

}

