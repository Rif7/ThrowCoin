package mainPackage;

import java.io.Serializable;
import java.util.Random;

public class Coin implements Serializable{
	private static final long serialVersionUID = -8362331580089960563L;
	private boolean obverse;
	private int heads;
	private int overall;
	Random rnd;
	
	Coin() {
		obverse = false;
		rnd = new Random();
	}
	
	public boolean doThrow() {
		obverse = rnd.nextBoolean();
		if (obverse) {
			heads++;
		}
		overall++;
		return obverse;
	}
	
	public boolean getResult() {
		return obverse;
	}
	
	public int getHeads() {
		return heads;
	}
	
	public int getTails() {
		return overall - heads;
	}
	
	public int getOverall() {
		return overall;
	}
	
	public void reset() {
		overall = 0;
		heads = 0;
	}
	
}
