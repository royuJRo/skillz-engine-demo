package Game;

import java.util.ArrayList;

public class Units{
	
}


class PiratesUnit{
	private int num;
	private Location point;
	
	private ArrayList<Pirate> unit;

	public PiratesUnit(int num, Location point) {
		super();
		this.num = num;
		this.point = point;
		
		unit = new ArrayList<Pirate>();
		
		if(num == 1) {
			unit.add(new Pirate(new Location(point.row, point.col)));
		}
		
		if(num == 4) {
			unit.add(new Pirate(new Location(point.row, point.col)));
			unit.add(new Pirate(new Location(point.row+20, point.col)));
			unit.add(new Pirate(new Location(point.row+40, point.col)));
			unit.add(new Pirate(new Location(point.row+60, point.col)));
		}
		
		if(num == 8) {
			unit.add(new Pirate(new Location(point.row, point.col)));
			unit.add(new Pirate(new Location(point.row+20, point.col)));
			unit.add(new Pirate(new Location(point.row+40, point.col)));
			unit.add(new Pirate(new Location(point.row+60, point.col)));
			
			unit.add(new Pirate(new Location(point.row, point.col+35)));
			unit.add(new Pirate(new Location(point.row+20, point.col+35)));
			unit.add(new Pirate(new Location(point.row+40, point.col+35)));
			unit.add(new Pirate(new Location(point.row+60, point.col+35)));
			
		}
		
		int count = 0;
		for(Pirate p:unit) {
			p.id = count;
			count++;
		}
	}
	
	public PiratesUnit(int num, Location point, Player owner) {
		super();
		this.num = num;
		this.point = point;
		
		unit = new ArrayList<Pirate>();
		

		if(num == 1) {
			unit.add(new Pirate(new Location(point.row, point.col)));
		}
		
		if(num == 4) {
			unit.add(new Pirate(new Location(point.row, point.col)));
			unit.add(new Pirate(new Location(point.row+20, point.col)));
			unit.add(new Pirate(new Location(point.row+40, point.col)));
			unit.add(new Pirate(new Location(point.row+60, point.col)));
		}
		
		if(num == 8) {
			unit.add(new Pirate(new Location(point.row, point.col)));
			unit.add(new Pirate(new Location(point.row+20, point.col)));
			unit.add(new Pirate(new Location(point.row+40, point.col)));
			unit.add(new Pirate(new Location(point.row+60, point.col)));
			
			unit.add(new Pirate(new Location(point.row, point.col+35)));
			unit.add(new Pirate(new Location(point.row+20, point.col+35)));
			unit.add(new Pirate(new Location(point.row+40, point.col+35)));
			unit.add(new Pirate(new Location(point.row+60, point.col+35)));
			
		}
		
		int count = 0;
		for(Pirate p:unit) {
			p.id = count;
			p.owner = owner;
			count++;
		}
	}
	
	Pirate[] getPiratesArray() {
		Pirate[] arr = new Pirate[unit.size()];
		arr = unit.toArray(arr);
		return arr;
	}
	
	
}
