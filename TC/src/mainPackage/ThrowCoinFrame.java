package mainPackage;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.Font;

public class ThrowCoinFrame {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblHeadsnr;
	private JLabel lblTailsnr;
	private JLabel lblOverallnr;
	private Coin c;
	private JPanelThrowCoin panelThrowCoin;


	/**
	 * Create the application.
	 */
	public ThrowCoinFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		c = new Coin();
		frame = new JFrame();
		this.frame.setVisible(true);
		frame.setBounds(100, 100, 553, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnThrow = new JButton("Throw");
		btnThrow.setBounds(428, 11, 99, 300);
		btnThrow.addActionListener(new BtnThrowListener());
		
		lblNewLabel = new JLabel("Result");
		lblNewLabel.setBounds(118, 322, 299, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(428, 322, 99, 55);
		btnReset.addActionListener(new BtnResetListener());
		
		JLabel lblHeads = new JLabel("Heads");
		lblHeads.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeads.setBounds(10, 11, 62, 14);
		
		lblHeadsnr = new JLabel("0");
		lblHeadsnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeadsnr.setBounds(10, 31, 62, 14);
		
		JLabel lblTails = new JLabel("Tails");
		lblTails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTails.setBounds(10, 51, 62, 14);
		
		lblTailsnr = new JLabel("0");
		lblTailsnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTailsnr.setBounds(10, 71, 62, 14);
		
		JLabel lblOverall = new JLabel("Overall");
		lblOverall.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOverall.setBounds(10, 91, 62, 14);
		
		lblOverallnr = new JLabel("0");
		lblOverallnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOverallnr.setBounds(10, 111, 62, 14);
		
		panelThrowCoin = new JPanelThrowCoin();
		panelThrowCoin.setBounds(118, 11, 300, 300);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblOverall);
		frame.getContentPane().add(lblTailsnr);
		frame.getContentPane().add(lblTails);
		frame.getContentPane().add(lblHeadsnr);
		frame.getContentPane().add(lblHeads);
		frame.getContentPane().add(lblOverallnr);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(panelThrowCoin);
		frame.getContentPane().add(btnThrow);
		frame.getContentPane().add(btnReset);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new BtnSaveListener());
		btnSave.setBounds(10, 165, 99, 37);
		frame.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new BtnLoadListener());
		btnLoad.setBounds(10, 213, 98, 37);
		frame.getContentPane().add(btnLoad);
	}
	
	// Result after pressed btnThrow
	class BtnThrowListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			setResult(c.doThrow());
		}
		
		private void setResult(boolean result) {
			panelThrowCoin.setResult(result);
			if (result) {
				lblNewLabel.setText("HEAD !");
				lblHeadsnr.setText(String.valueOf(c.getHeads()));
			} else {
				lblNewLabel.setText("TAIL !");
				lblTailsnr.setText(String.valueOf(c.getTails()));
			}
			lblOverallnr.setText(String.valueOf(c.getOverall()));
			frame.repaint();
		}
	}
	// Reseting statistics
	class BtnResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			c.reset();
			lblHeadsnr.setText("0");
			lblTailsnr.setText("0");
			lblOverallnr.setText("0");
			lblNewLabel.setText("RESETED STATS");
			panelThrowCoin.resetImg();
			frame.repaint();
		}
	}
	// Saving and loading
	class BtnSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				FileOutputStream fs = new FileOutputStream("data\\throwsStat.ser");
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(c);
				os.close();
				lblNewLabel.setText("SAVED STATS");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class BtnLoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				FileInputStream fs = new FileInputStream("data\\throwsStat.ser");
				ObjectInputStream os = new ObjectInputStream(fs);
				c = (Coin) os.readObject();
				os.close();
				lblHeadsnr.setText(String.valueOf(c.getHeads()));
				lblTailsnr.setText(String.valueOf(c.getTails()));
				lblOverallnr.setText(String.valueOf(c.getOverall()));
				frame.repaint();
				lblNewLabel.setText("LOADED STATS");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
