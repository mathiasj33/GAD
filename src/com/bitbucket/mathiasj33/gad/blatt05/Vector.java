package com.bitbucket.mathiasj33.gad.blatt05;

import java.util.ArrayList;

public class Vector {
	private ArrayList<Integer> values = new ArrayList<>();
	
	public Vector() {}
	
	public Vector(int... values) {
		for(int v : values) addValue(v);
	}
	
	public void addValue(int value) {
		values.add(value);
	}
	
	public int get(int i) {
		return values.get(i);
	}
	
	public void set(int i, int value) {
		values.set(i, value);
	}
	
	public int size() {
		return values.size();
	}
	
	/**
	 * Returns a vector consisting of the components in [startIndex, endIndex)
	@param	startIndex	the inclusive startIndex
	@param	endIndex	the exclusive endIndex
	@return				the sliced version of the vector
	**/
	public Vector slice(int startIndex, int endIndex) {
		Vector sliced = new Vector();
		for(int i = startIndex; i < endIndex; i++) {
			sliced.addValue(this.get(i));
		}
		return sliced;
	}
	
	public int scalarMult(Vector other) {
		if(this.size() != other.size()) throw new IllegalArgumentException("The scalar product "
				+ "can only be computed on two vectors of equal size");
		int sum = 0;
		for(int i = 0; i < size(); i++) {
			sum += this.get(i) * other.get(i);
		}
		return sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vector [values=" + values + "]";
	}
}
