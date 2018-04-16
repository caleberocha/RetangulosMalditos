package pucrs.alest2.t1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CanvasMatrix {
	private byte[][] matrix;
	
	public CanvasMatrix(int size) {
		matrix = new byte[size][size];
	}
	
	public byte[][] getMatrix() {
		return matrix;
	}
	
	public void paint(Retangulo retangulo) {
		for(int i = retangulo.getCoord().getX1()+1; i <= retangulo.getCoord().getX2(); i++)
			for(int j = retangulo.getCoord().getY1()+1; j <= retangulo.getCoord().getY2(); j++)
				matrix[i][j] = Color.byteOf(retangulo.getCor());
	}
	
	public Map<String, Long> getCores() {
		Map<String, Long> cores = new HashMap<>();
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[i].length; j++)
				cores.compute(Color.descriptionOf(matrix[i][j]), (k, v) -> v == null ? 1 : v+1);
		
		return cores.entrySet().stream()
				.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
