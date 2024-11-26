package model.infrastructure;

import java.util.ArrayList;

import model.disasters.Disaster;
import model.events.SOSListener;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import java.util.Random;

public class ResidentialBuilding implements Rescuable, Simulatable {

	private Address location;
	private int structuralIntegrity;
	private int fireDamage;
	private int gasLevel;
	private int foundationDamage;
	private ArrayList<Citizen> occupants;
	private Disaster disaster;
    private SOSListener emergencyService;
    
	public ResidentialBuilding(Address location) {

		this.location = location;
		this.structuralIntegrity = 100;
		occupants = new ArrayList<Citizen>();

	}

	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}

	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}

	public void setStructuralIntegrity(int structuralIntegrity) {
		this.structuralIntegrity = structuralIntegrity;
	}

	public int getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(int fireDamage) {
		this.fireDamage = fireDamage;
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(int gasLevel) {
		this.gasLevel = gasLevel;
	}

	public int getFoundationDamage() {
		return foundationDamage;
	}

	public void setFoundationDamage(int foundationDamage) {
		this.foundationDamage = foundationDamage;
	}

	public Address getLocation() {
		return location;
	}

	public ArrayList<Citizen> getOccupants() {
		return occupants;
	}

	public Disaster getDisaster() {
		return disaster;
	}

	@Override
	public void cycleStep() {
if(this.getFoundationDamage()>0){
	Random rand = new Random();
	int n = rand.nextInt(6);
	n +=5;
	this.setStructuralIntegrity(n);
	
}
if (this.getFireDamage()>0|this.getFireDamage()>30){
	this.setStructuralIntegrity(this.getStructuralIntegrity()-3);
}
if  (this.getFireDamage()<=30| this.getFireDamage()<70){
	this.setStructuralIntegrity(this.getStructuralIntegrity()-5);

}
if (this.getFireDamage()>=70){
	this.setStructuralIntegrity(this.getStructuralIntegrity()-7);

}
	}

	@Override
	public void struckBy(Disaster d) {
		// TODO Auto-generated method stub
		
	}

}
