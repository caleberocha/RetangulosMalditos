package pucrs.alest2.t1;
/**
 * Retângulo representado na forma de coordenadas, e que tem uma cor.
 * @author calebe
 *
 */
public class Retangulo {
	private Coordenadas coord;
	private byte cor;
	
	public Retangulo(Coordenadas coord, byte cor) {
		this.coord = coord;
		this.cor = cor;
	}
	
	public Retangulo(Coordenadas coord, String cor) {
		this(coord, Color.byteOf(cor));
	}
	
	public Retangulo(int x1, int x2, int y1, int y2, byte cor) {
		this(new Coordenadas(x1, x2, y1, y2), cor);
	}
	
	public Retangulo(int x1, int x2, int y1, int y2, String cor) {
		this(new Coordenadas(x1, x2, y1, y2), cor);
	}
	
	/**
	 * Obtém a área do retângulo.
	 * @return
	 */
	public int area() {
		return (coord.getY2() - coord.getY1()) * (coord.getX2() - (coord.getX1()));
	}
	
	/**
	 * Obtém as coordenadas do retângulo.
	 * @return
	 */
	public Coordenadas getCoord() {
		return coord;
	}

	/**
	 * Obtém a cor do retângulo (byte).
	 * @return
	 */
	public byte getCorValue() {
		return cor;
	}
	/**
	 * Obtém a cor do retângulo (String).
	 * @return
	 */
	public String getCor() {
		return Color.descriptionOf(cor);
	}

	@Override
	public String toString() {
		return String.format("%5d %5d %5d %5d    %-15s %12d", coord.getX1(), coord.getY1(), coord.getX2(), coord.getY2(), Color.descriptionOf(cor), area());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + cor;
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
		Retangulo other = (Retangulo) obj;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		if (cor != other.cor)
			return false;
		return true;
	}

	
	
}
