package Game;


/**
 * A Location class(x,y) to (y,x) to (row, col).
 * @author Yuval Rotman
 * @version 1.1
 * 
 *
 */

public class Location {

	/**
	 * Col on the game map.
	 */
	public int col;
	/**
	 * Row on the game map.
	 */
	public int row;

	
	/**
	 * Initialize constructor for that Location object.
	 * @param row
	 * @param col
	 */
	public Location(int row, int col) {
		this.col = col;
		this.row = row;
	}
	
	
	/**
	 * This method adds the other location component to the current location.
	 * @param other
	 */
	public void add(Location other) {
		col += other.col;
		row += other.row;
	}
	
	
	/**
	 * This method calculates the distance between that location to other location.
	 * @param other
	 * @return The distance in integer number.
	 */
	public int distance(Location other) {
		return (int)Math.sqrt(Math.pow(other.col-col, 2)+Math.pow(other.row-row, 2));
	}
	
	/**
	 * @return That current location.
	 */
	public Location getLocation() {
		return this;
	}
	
	/**
	 *This method check if another location is in range from our current location.
	 * @param other
	 * @param range
	 * @return True if is in range and false if not.
	 */
	public boolean inRange(Location other, int range) {
		return this.distance(other) <= range;
	}
	
	/**
	 * This method subtract the other location component from current location.
	 * @param other
	 */
	public void substract(Location other) {
		col -= other.col;
		row -= other.row;
	}
	
	
	/**
	 * This method is super duper important method which calculate new location data in range towards a particular location.
	 * @param destenation
	 * @param length
	 * @return New location.
	 */
	public Location towards(Location destenation, int length) {
		final int distance = this.distance(destenation);
		double sinAngle, cosAngle, high, width;
		
		if(this.equals(destenation)) {
			return this;
		}
		
		try {
			sinAngle = (double)(destenation.row - row)/distance;
			cosAngle = Math.cos(Math.asin(sinAngle));
			
			width = cosAngle*length;
			high = sinAngle*length;
			
			if(destenation.col<col) {
				width *=-1;
			}
			
			/*if(destenation.row> row) {
				double temp;
				temp = width;
				width = high;
				high = temp;
			}*/
			
			return new Location((int)(row+high),(int)(col+width));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj
	 * @return True if the param is completely equals to the current object and false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return Location description String.
	 */
	@Override
	public String toString() {
		return "Location [col=" + col + ", row=" + row + "]";
	}
	
	
	
	

}
