package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.people.Citizen;
import model.people.CitizenState;


public class Infection extends Disaster {

	public Infection(int startCycle, Citizen target) {
		super(startCycle, target);
	}
	@Override
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException 
	{
		Citizen target = (Citizen)getTarget();
		if(target.getState().equals(CitizenState.DECEASED)) {
			throw new CitizenAlreadyDeadException(this, "Citizen Already Dead");
		}
		target.setToxicity(target.getToxicity()+25);
		super.strike();
	}
	@Override
	public void cycleStep() {
		Citizen target = (Citizen)getTarget();
		target.setToxicity(target.getToxicity()+15);

	}

}
