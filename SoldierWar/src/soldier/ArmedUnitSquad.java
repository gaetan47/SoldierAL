package soldier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.AgeFactory;
import utils.ObservableAbstract;
import utils.VisitorClassicForArmedUnit;
import utils.VisitorFunForArmedUnit;

public class ArmedUnitSquad extends ObservableAbstract<ArmedUnit> implements
		ArmedUnit {
	protected List<ArmedUnit> armedUnitList = new ArrayList<ArmedUnit>();
	protected String name;
	protected AgeFactory age;

	public ArmedUnitSquad(AgeFactory factory, String name) {
		this.age = factory;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addUnit(ArmedUnit s) {
		if (s.getAge() == age) // historical coherence checked
			armedUnitList.add(s);
		else
			throw new BadAgeException();
	}

	public float getHealthPoints() {
		float points = 0;
		for (ArmedUnit s : armedUnitList) {
			points += s.getHealthPoints();
		}
		return points;
	}

	public AgeFactory getAge() {
		return age;
	}

	public boolean alive() {
		boolean alive = false;
		for (ArmedUnit s : armedUnitList) {
			alive = alive || (s.alive());
		}
		return alive;
	}

	public int getStillAliveSoldiers() {
		int stillAlive = 0;
		for (ArmedUnit s : armedUnitList) {
			if (s.alive())
				stillAlive += 1;
		}
		return stillAlive;
	}

	public void heal() {
		for (ArmedUnit s : armedUnitList) {
			s.heal();
		}
	}

	public void addEquipment(String equipmentType) {
		if (alive()) {
			// Every soldier of a unit gets the same equipment
			for (ArmedUnit s : armedUnitList) {
				if (s.alive())
					s.addEquipment(equipmentType);
			}
		}
	}

	public boolean parry(float force) {
		boolean result = false;
		if (alive()) {
			float forcePart = force / getStillAliveSoldiers();
			// Each alive soldier takes an equal part in each strike
			for (ArmedUnit s : armedUnitList) {
				result = (s.parry(forcePart)) || result;
			}
		}
		notify(this);
		return result;
	}

	public float strike() {
		float result = 0;
		if (alive()) {
			for (ArmedUnit s : armedUnitList) {
				result += s.strike();
			}
		}
		return result;
	}

	public void accept(VisitorClassicForArmedUnit v) {
		v.visit(this);
		for (ArmedUnit s : armedUnitList) {
			s.accept(v);
		}
	}

	public <T> T accept(VisitorFunForArmedUnit<T> v) {
		T result = v.visit(this);
		for (ArmedUnit s : armedUnitList) {
			result = v.compos(result, s.accept(v));
		}
		return result;
	}

	public float getMaxHealthPoints() {
		return 0;
	}

	public void addHealthPoints(float f) {
		for (ArmedUnit s : armedUnitList)
			s.addHealthPoints(f);
	}

	@Override
	public int numberOfFriend() {
		
		return armedUnitList.size();
	}
	
	public HashMap<String, Float> getHealthAndName(){
		HashMap<String, Float> map = new HashMap<String, Float>();
		for (ArmedUnit a : armedUnitList){
			map.put(a.getName(), a.getHealthPoints());
		}
		return map;
	}

	public float getTotalStrength() {
		float totalStrength = 0;
		for (ArmedUnit a : armedUnitList){
			totalStrength += a.getTotalStrength();
		}
		return totalStrength;
	}

	public float getSwordStrength() {
		if (armedUnitList.size() == 0) return 0;
		float swordStrength = armedUnitList.get(0).getSwordStrength();
		
		return swordStrength;
	}

	public float getShieldDefense() {
		if (armedUnitList.size() == 0) return 0;
		float shieldDefense = armedUnitList.get(0).getShieldDefense();

		return shieldDefense;
	}
	
}
