package bigdata.Clusteredrecommender;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.net.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Framework {
	public JFrame mainFrame;
	private JButton Search, Recom;
	private JTextField Inputbox;
	private JTextArea movieinfo;
	private String movie_name;
	private imagePanel posterPanel;
	private JPanel recmovies;
	private Recommender recommender;
	public Framework() {
		initialize();
	}
	
	private void initialize(){
		mainFrame = new JFrame("Recommender");
		mainFrame.setBounds(100, 100, 520, 520);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(2,1,5,5));
		recommender = new Recommender();
		JPanel temp1 = new JPanel();
		temp1.setSize(500, 500);
		temp1.setLayout(null);
		Inputbox = new JTextField();
		Inputbox.setBounds(10, 10, 300, 30);
		Search = new JButton("Search");
		Search.setBounds(310, 10, 70, 30);
		Recom = new JButton("Recommend");
		Recom.setBounds(370, 10, 120, 30);
		Recom.setEnabled(false);
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(recommender.isExist(Inputbox.getText())){
						movie_name=Inputbox.getText();
						Recom.setEnabled(true);
						omdbAPI test = new omdbAPI(movie_name);
						changeInfo(test);
					}
					else{
						Recom.setEnabled(false);
						JOptionPane.showMessageDialog(mainFrame, "Movie not found.");
					}
					
				}catch(NotfindExcetion e){System.err.println(e.toString());}
			}
		});
		Recom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(movie_name != null){
					recmovies.removeAll();
					ArrayList<String> reclist = (ArrayList<String>)recommender.getRec(movie_name);
					for(String mn : reclist)
						buildrecPanel(mn);
				}
			}
		});
		temp1.add(Inputbox);
		temp1.add(Search);
		temp1.add(Recom);

		movieinfo = new JTextArea("Movie name:\n\nYear:\n\nimdbRating:\n\nDescription:\n\nimdbID:");
		movieinfo.setBackground(mainFrame.getBackground());
		movieinfo.setLineWrap(true);
		movieinfo.setWrapStyleWord(true);
		movieinfo.setEditable(false);
		DefaultCaret caret = (DefaultCaret)movieinfo.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		//movieinfo.setBounds(10, 50, 300, 500);
		JScrollPane scroll = new JScrollPane(movieinfo);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.setBounds(10, 50, 300, 180);
		//JPanel temp2 = new JPanel(new BorderLayout());
		temp1.add(scroll);
		posterPanel = new imagePanel(125,180);
		posterPanel.setBounds(350, 50, 125, 180);
		temp1.add(posterPanel);
		mainFrame.add(temp1);
		recmovies = new JPanel(new GridLayout(1,5,5,5));
		mainFrame.add(recmovies);
	}
	
	private void changeInfo(omdbAPI test){
		posterPanel.changeImage(test.getPoster(),test.getIMDBID());
		movieinfo.setText("Movie name: "+test.getName()+"\n\nYear: "+test.getYear()
		+"\n\nimdbRating: "+test.getIMDBScore()+"\n\nDescription: "+test.getPlot()+
		"\n\nimdbID: "+test.getIMDBID());
	}
	
	private void buildrecPanel(String movie){
		//recmovies.removeAll();
		JPanel temp = new JPanel();
		temp.setLayout(null);
		imagePanel ip = new imagePanel(100,144);
		try{
			omdbAPI tempAPI = new omdbAPI(movie);
			ip.changeImage(tempAPI.getPoster(),tempAPI.getIMDBID());
		}
		catch(NotfindExcetion e){}
		ip.setBounds(0, 0, 100, 144);
		temp.add(ip);
		JLabel tempLabel = new JLabel(movie);
		tempLabel.setBounds(0, 150, 100, 30);
		temp.add(tempLabel);
		recmovies.add(temp);
		recmovies.updateUI();
	}

}

class imagePanel extends JPanel{
	BufferedImage currentImage;
	URL imdbAddress;
	int imgWidth,imgHeight;
	public imagePanel(int width,int height){
		imgWidth=width;
		imgHeight=height;
		currentImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		try {
			imdbAddress = new URL("");
		} catch (MalformedURLException e) {}
		repaint();
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg){
	            try{
	                if(!imdbAddress.getProtocol().equals(""))
	                	if (Desktop.isDesktopSupported())
	                		Desktop.getDesktop().browse(imdbAddress.toURI());
	            } catch (Exception e)
	            {
	            }
	        }
			
			public void mouseEntered(MouseEvent arg){
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg){
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	
	public void changeImage(BufferedImage newImage,String imdbID){
		try {
			imdbAddress = new URL("http://www.imdb.com/title/"+imdbID+"/");
			currentImage=newImage;
			Image image = currentImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
			currentImage = new BufferedImage(imgWidth, imgHeight, Image.SCALE_SMOOTH);
			currentImage.getGraphics().drawImage(image, 0, 0 , null);
			repaint();
		} catch (MalformedURLException e) {}
	}
	  @Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(currentImage, 0, 0, null);
	}
	 
}

