package Game;

import java.util.*;



/**
 * Game object class, a general class that describes a tool in the game.
 * @author Yuval Rotman
 * @version 1.1
 * 
 *
 */

public abstract class GameObject {
	
	/**
	 * The ID of the object that relates to the player from all objects belongs to it.
	 */
	public int id;
	/**
	 * The unique ID of this object.
	 */
	public String uniqueId;
	/**
	 * The object's current location.
	 */
	protected Location location;
	/**
	 * The player who own that GameObject.
	 */
	public Player owner;
	
	public boolean alreadyActed;
	
	
	/**
	 * Just for game's graphic motor
	 */
	
	/**
	 * GameObject picture details
	 * 
	 */
	
	public int High = 40;
	public int Width = 40;
	
	protected String PicPath = "";
	
	/**
	 * Defult constructor
	 */
	public GameObject() {
		id = 0;
		uniqueId = UUID.randomUUID().toString();
		location = new Location(0, 0);
	}
	
	protected GameObject(int id) {
		this.id = id;
		uniqueId = UUID.randomUUID().toString();
		location = new Location(0, 0);
	}
	
	protected GameObject(String picPath, int id, Location location) {
		this.id = id;
		uniqueId = UUID.randomUUID().toString();
		this.location = location;
	}
	
	protected GameObject(String picPath, Location location, Player player) {
		this.id = id;
		uniqueId = UUID.randomUUID().toString();
		this.location = location;
		this.owner = player;
	}
	
	/**
	 * This method returns the current location of that object.
	 * @return Object's current location.
	 */
	public Location getLocation() {
		return location;
	}

	protected void setPlayer(Player player) {
		if(owner == null) {
			owner = player;
		}
	}

	/** 
	 * This method checks if that object had been programed.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, location, uniqueId, owner);
	}


	/** 
	 * This method is able to compare another object to that GameObject.
	 * @param obj.
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return True if they are equals for else false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GameObject)) {
			return false;
		}
		GameObject other = (GameObject) obj;
		return id == other.id && Objects.equals(location, other.location) && uniqueId == other.uniqueId && Objects.equals(other.owner, owner);
	}


	/**
	 * This method is making an String which describe to current Object.
	 * @return toString value
	 */
	@Override
	public String toString() {
		return "GameObject [id=" + id + ", uniqueId=" + uniqueId + ", owner=" + owner + "]";
	}
	
	public String getPicPath() {
		return PicPath;
	}
	
	/**
	 * Responsible on the drawing action method.
	 */
	public abstract void update();
	
	
	

}
