package model.people;

import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import model.disasters.Disaster;
import model.events.SOSListener;
import model.events.WorldListener;

public class Citizen implements Rescuable, Simulatable {

	private CitizenState state;
	private Disaster disaster;
	private String name;
	private String nationalID;
	private int age;
	private int hp;
	private int bloodLoss;
	private int toxicity;
	private Address location;
    private  SOSListener emergencyService;
    private WorldListener worldListener;
    
	public Citizen(Address location, String nationalID, String name, int age) {

		this.name = name;
		this.nationalID = nationalID;
		this.age = age;
		this.location = location;
		this.state = CitizenState.SAFE;
		this.hp = 100;

	}

	public CitizenState getState() {
		return state;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}

	public void setState(CitizenState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp>0|| hp<100)
		this.hp = hp;
		else 
			if(this.getBloodLoss()==100|| this.getToxicity()==100){
				this.hp=0;
			}
		if (this.getHp()==0){
		this.setState(this.getState().DECEASED);;
		}

	}

	public int getBloodLoss() {
		return bloodLoss;
	}

	public void setBloodLoss(int bloodLoss) {
		if (bloodLoss>0|| bloodLoss<100)
		this.bloodLoss = bloodLoss;

	}

	public int getToxicity() {
		return toxicity;
	}

	public void setToxicity(int toxicity) {
		if (toxicity>0|| toxicity<100)
		this.toxicity = toxicity;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public Disaster getDisaster() {
		return disaster;
	}

	public String getNationalID() {
		return nationalID;
	}

	@Override
	public void cycleStep() {
if(this.getBloodLoss()+this.getToxicity()<30){
this.setHp(this.getHp()-5);
}
if(this.getBloodLoss()+this.getToxicity()<=70){
this.setHp(this.getHp()-10);
}
	}

	@Override
	public void struckBy(Disaster d) {
		// TODO Auto-generated method stub
		
	}

}
