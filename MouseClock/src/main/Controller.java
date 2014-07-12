package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
	private static ClockFrame cf;
	private static Controller control;
	
	public static void main(String[] args) throws InterruptedException {
		Experiment data = new Experiment();
		control = new Controller(data);
		cf = new ClockFrame(data);
		cf.addKeyListener(control.getKeyListener());
		control.go();
		
	}
	
	/////////
	private boolean started = false;
	private boolean inOpen = false;
	private boolean inClosed = false;
	private KeyListener kl;
	Experiment ex;
	
	
	private Controller (Experiment ex){
		this.ex = ex;
		kl = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!started && e.getKeyCode() == KeyEvent.VK_S){
					started = true;
					inClosed = true;
					System.out.println("Starting!!");
				}else if (!started){
					return;
				}else if (e.getKeyCode() == KeyEvent.VK_C){
					inClosed = !inClosed;
				}else if (e.getKeyCode() == KeyEvent.VK_O){
					inOpen = !inOpen;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		};
	}
	
	public void go () throws InterruptedException{
		cf.setVisible(true);
		while (!started){
			System.out.print("");
		};
		long startTime = System.currentTimeMillis();
		long closedEntry = startTime;
		long openEntry = -1;
		System.out.println(ex.duration());
		long loopTime;
		while (ex.duration() > 0){
			loopTime = System.currentTimeMillis();
			ex.setElapsed(loopTime - startTime);
			///
			//Closed
			if (closedEntry != -1){
				if (inClosed){
					ex.setClosedElapsed(loopTime-closedEntry);
				}else {
					ex.stashClosed();
					closedEntry = -1;
				}
			} else {
				if (inClosed){
					closedEntry = loopTime;
					ex.closedEntries++;
				}
			}
			//Open
			if (openEntry != -1){
				if (inOpen){
					ex.setOpenElapsed(loopTime-openEntry);
				}else {
					ex.stashOpen();
					openEntry = -1;
				}
			} else {
				if (inOpen){
					openEntry = loopTime;
					ex.openEntries++;
				}
			}
			///
			cf.repaint();
			
		}
	}
	
	public KeyListener getKeyListener(){
		return kl;
	}

}
