package Game;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


/**
 * A Player class.
 * @author Yuval Rotman
 * @version 1.2
 * 
 *
 */
public final class Player {
	
	/**
	 * The current player's ID.
	 */
	public String id;
	/**
	 *The current player's score.
	 */
	public int score;
	/**
	 * The player's bot's name.
	 */
	protected String botName;
	
	
	
	
	public Player(String botName) {
		this.botName = botName;
		id = UUID.randomUUID().toString();
		score = 0;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(botName, id, score);
		return result;
	}
	
	/**
	 * @param obj
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return True if the param is equals to that current Player object.for else false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(botName, other.botName) && id == other.id && score == other.score;
	}
	
	
	/**
	 * @see java.lang.Object#toString()
	 * @return Player's String details.
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", botName=" + botName + "]";
	}
	
	
	
	
	
	
	

}
