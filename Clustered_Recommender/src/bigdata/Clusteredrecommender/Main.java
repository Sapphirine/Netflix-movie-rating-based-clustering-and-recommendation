package bigdata.Clusteredrecommender;

import java.awt.EventQueue;


public class Main {

	public static void main(String[] args) {
		new Framework();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Framework window = new Framework();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
