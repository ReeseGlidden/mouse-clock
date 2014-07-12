package main;

public class Experiment {
	///Experiment params
	public static final long DEFAULT_DURATION = 5*60*1000; //5 minutes in ms
	///Experimental Data
	int openEntries, closedEntries = 0;
	long openBase, openCur, closedBase, closedCur = 0;
	long duration = DEFAULT_DURATION;
	
	public long duration() {
		return duration;
	}
	
	public void setOpenElapsed(long ms){
		openCur = ms;
	}
	
	public void setClosedElapsed(long ms){
		closedCur = ms;
	}
	
	public void stashOpen(){
		openBase += openCur;
		openCur = 0;
	}
	
	public void stashClosed(){
		closedBase += closedCur;
		closedCur = 0;
	}
	
	
	public static String toMinSec(long ms){
		long minutes = ms / (60*1000);
		long seconds = (ms % 60000)/1000;
		long mils = ms % 1000;
		String ret = (minutes<=9?"0"+minutes:minutes)+" : "+seconds+"."+mils/10;
		return ret;
	}
	
	public static String toSec(long ms){
		long seconds = ms/1000;
		long mils = ms % 1000;
		String ret = seconds+"."+mils/10;
		return ret;
	}

	public void setElapsed(long l) {
		duration = DEFAULT_DURATION - l;
	}

	public long openTime() {
		return openBase+openCur;
	}
	
	public long closedTime(){
		return closedBase+closedCur;
	}
}
