package shop.data;


/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
	private final String _title;
	private final int    _year;
	private final String _director;

	/**
	 * Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @throws IllegalArgumentException if object invariant violated.
	 */
	VideoObj(String title, int year, String director) {
		_title = title;
		_director = director;
		_year = year;
	}

	public String director() {
		return _director;
	}

	public String title() {
		return _title;
	}

	public int year() {
		return _year;
	}

	public boolean equals(Object thatObject) {
		if (thatObject == null) return false;
		if (this == thatObject) return true;
		if (this.getClass() != thatObject.getClass()) return false;
		VideoObj o = (VideoObj) thatObject;
		if (!this._title.equals(o._title)) return false;
		if (this._year != o._year) return false;
		if (!this._director.equals(o._director)) return false;
		return true;
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + _title.hashCode();
		result = 37 * result + _year;
		result = 37 * result + _director.hashCode();
		return result;
	}

	public int compareTo(Object thatObject) {
		if (thatObject.getClass() != this.getClass()) throw new ClassCastException();
		VideoObj other = (VideoObj) thatObject;
		int t1 = this._title.compareTo(other._title);
		if (t1 != 0) return t1;
		int t2 = this._year - other._year;
		if (t2 != 0) return t2;
		int t3 = this._director.compareTo(other._director);   
		if (t3 != 0) return t3;
		return 0;
	}

	public String toString() {
		String s = _title + " (";
		s += _year + ") : ";
		s += _director;
		return s;
	}
}
