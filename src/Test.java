import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

class CricketData extends Observable {
	int runs;
	int wickets;
	float overs;
	
	public CricketData() {
	}

	// This method is used update displays
	// when data changes
	public void dataChanged() {
		//update data: runs, wickets, overs
		this.runs = 90;
		this.wickets = 2;
		this.overs = 10;
		setChanged();
		notifyObservers(this);
	}
}

class AverageScoreDisplay implements Observer {
	private float runRate;
	private int predictedScore;

	public void display() {
		System.out.println(
				"\nAverage Score Display: \n" + "Run Rate: " + runRate + "\nPredictedScore: " + predictedScore);
	}

	@Override
	public void update(Observable arg0, Object data) {
		//extract runs and overs from data and calculate runRate and
		//predictedScore to the following formula:
		//runRate =runs/overs
		//predictedScore = runRate * 50
		CricketData cricketData = (CricketData) data;
		runRate = cricketData.runs / cricketData.overs;
		predictedScore = Math.round(runRate * 50);
		display();
	}
}

class CurrentScoreDisplay implements Observer {
	private int runs, wickets;
	private float overs;

	public void display() {
		System.out
				.println("\nCurrent Score Display:\n" + "Runs: " + runs + "\nWickets:" + wickets + "\nOvers: " + overs);
	}

	@Override
	public void update(Observable o, Object data) {
		//update runs, wickets, overs from data
		CricketData cricketData = (CricketData) data;
		this.runs = cricketData.runs;
		this.wickets = cricketData.wickets;
		this.overs = cricketData.overs;
		display();
	}
}

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// create objects for testing
		AverageScoreDisplay averageScoreDisplay = new AverageScoreDisplay();
		CurrentScoreDisplay currentScoreDisplay = new CurrentScoreDisplay();

		// pass the displays to Cricket data
		CricketData cricketData = new CricketData();

		// register display elements
		cricketData.addObserver(averageScoreDisplay);
		cricketData.addObserver(currentScoreDisplay);

		// in real app you would have some logic to
		// call this function when data changes
		cricketData.dataChanged();
	}
}