package Game;


/**
 * A Pirate class, general game's soldier.
 * @author Yuval Rotman
 * @version 1.2
 * 
 *
 */

public final class Pirate extends GameObject {
	/**
	 * A final for pirate's max sped.
	 */
	public static final int MAX_SPEED = 20;
	/**
	 * A final for pirate's spwan turns num.
	 */
	public static final int SPAWN_TURNS = 40;
	
	// Variables
	
	/**
	 * The pirate's initial Location(final).
	 */
	public final Location initialLocation;
	/**
	 * The pirate's max speed. 
	 */
	public final int maxSpeed;
	/**
	 * The pirate's spwan turns num.
	 */
	public final  int spawnTurns;
	/**
	 * In how many turns the pirate will return to the game.
	 */
	protected int turnToRevive;
	
	/**
	 * The object's constructor to initial the pirate's values.
	 */
	
	public Pirate() {
		maxSpeed = MAX_SPEED;
		spawnTurns = SPAWN_TURNS;
		turnToRevive = 0;
		
		initialLocation = new Location((int)(Math.random()*600)+20, (int)(Math.random()*800)+1);
		this.location =initialLocation;
		
		this.Width = 30;
		this.High = 15;
		this.PicPath = "/images/pirate.png";
	}
	
	public Pirate(Location initLocation) {
		maxSpeed = MAX_SPEED;
		spawnTurns = SPAWN_TURNS;
		turnToRevive = 0;
		
		initialLocation = initLocation;
		this.location =initialLocation;
		
		this.Width = 30;
		this.High = 15;
		this.PicPath = "/images/pirate.png";
	}
	
	
	// for clearly using in demo mode for killing pirate situation
	public void outOfTheGame() {
		turnToRevive = spawnTurns;
	}
	
	/**
	 * This method calculate if the pirates live or dead base on turnToRevive.
	 * @return True if in life and for else false.
	 */
	public boolean isAlive() {
		return turnToRevive == 0;
	}
	
	/**
	 * This method instead of known method sail, moving the pirate in max speed to the dest's direction.
	 * @param destenation to sail
	 * 
	 */
	public void go(Location destenation) {
		if(alreadyActed == false && isAlive()) {
		if(location.distance(destenation)<maxSpeed) {
			Location l = location.towards(destenation, location.distance(destenation));
			this.location = l;
		}
		else {
			Location l = location.towards(destenation, maxSpeed);
			this.location = l;
		}
		alreadyActed = true;
		}
		else if(isAlive() == false) System.out.println("You cant act a dead object.[pirate : "+this.uniqueId+"]");
		else System.out.println("you cant act already acted object.[pirate : "+this.uniqueId+"]");
	}
	
	/**
	 * This method instead of known method sail, moving the pirate in max speed to the dest's direction.
	 * @param destenation to sail as GameObject
	 * 
	 */
	public void go(GameObject obj) {
		this.go(obj.getLocation());
	}
	
	

	
	/**
	 * @param obj.
	 * @see Game.GameObject#equals(java.lang.Object)
	 * @return True in case they are equals false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		
		Pirate other1 = (Pirate) obj;
		if (initialLocation == null) {
			if (other1.initialLocation != null)
				return false;
		} else if (!initialLocation.equals(other1.initialLocation))
			return false;
		
		return super.equals(obj);
	}


	/**
	 * Returns pirate's addition for toString.
	 * @see Game.GameObject#toString()
	 */
	@Override
	public String toString() {
		return "Pirate [id=" + id + ", uniqueId=" + uniqueId + ", owner=" + owner + "]";
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	protected void kill() {
		this.turnToRevive = SPAWN_TURNS;
	}
	
	
}
