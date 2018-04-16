package pucrs.alest2.t1;

public class Coordenadas {
	private int x1, y1, x2, y2;

	public Coordenadas(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	@Override
	public String toString() {
		return "Coordenadas [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x1;
		result = prime * result + y1;
		result = prime * result + x2;
		result = prime * result + y2;
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
		Coordenadas other = (Coordenadas) obj;
		if (x1 != other.x1)
			return false;
		if (y1 != other.y1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}
	
	
}
