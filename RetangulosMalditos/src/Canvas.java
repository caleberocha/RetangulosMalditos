import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Canvas {
	
	private RedBlackBST<Integer, LinkedHashSet<Retangulo>> mengaoTree;
	private RedBlackBST<Integer, LinkedHashSet<Retangulo>> intervalTree;
	
	public Canvas() {
		mengaoTree = new RedBlackBST<>();
		intervalTree = new RedBlackBST<>();
	}
	
	/**
	 * Obtém a quantidade de retângulos.
	 * @return
	 */
	public int size() {
		return getRetangulos().size();
	}
	
	/**
	 * Retorna as áreas ocupadas por cada cor, em ordem decrescente.
	 * @return
	 */
	public HashMap<String, Long> getCores() {
		HashMap<String, Long> cores = new HashMap<>();
		mengaoTree.values().forEach(rr -> {
			rr.forEach(r -> {
				Long area = cores.get(r.getCor());
				if(area == null)
					area = (long) 0;
				cores.put(r.getCor(), r.area() + area);
			});
		});
		
		return cores.entrySet()
				.stream()
				.sorted((r1, r2) -> r2.getValue().compareTo(r1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (r1, r2) -> r1, LinkedHashMap::new));
	}
	
	/**
	 * Obtém os retângulos em uma lista.
	 * @return
	 */
	public List<Retangulo> getRetangulos() {
		return getRetangulos(mengaoTree);
	}
	
	/**
	 * Obtém os retângulos da árvore especificada em uma lista.
	 * @param tree
	 * @return
	 */
	private List<Retangulo> getRetangulos(RedBlackBST<Integer, LinkedHashSet<Retangulo>> tree) {
		List<Retangulo> retangulos = new LinkedList<>();
		tree.values().forEach(r -> retangulos.addAll(r));
		return retangulos;
	}
	
	/**
	 * Insere um retângulo. A cada inserção, é feita a verificação de sobreposições.
	 * @param r Retângulo
	 */
	public void insereRetangulo(Retangulo r) {
		putRetangulo(r, mengaoTree);
		procuraIntersecoes(r);
	}
	
	/**
	 * Insere um retângulo na árvore especificada.
	 * @param r Retângulo
	 * @param tree Árvore
	 */
	private void putRetangulo(Retangulo r, RedBlackBST<Integer, LinkedHashSet<Retangulo>> tree) {
		LinkedHashSet<Retangulo> ret = mengaoTree.get(r.getCoord().getX1());
		if(ret == null)
			ret = new LinkedHashSet<>();
		else
			ret = cloneRetangulos(ret);
		
		ret.add(r);
		tree.put(r.getCoord().getX1(), ret);
	}
	
	/**
	 * Remove um retângulo.
	 * @param r Retângulo
	 */
	private void removeRetangulo(Retangulo r) {
		removeRetangulo(r, mengaoTree);
	}
	
	private void removeRetangulo(Retangulo r, RedBlackBST<Integer, LinkedHashSet<Retangulo>> tree) {
		LinkedHashSet<Retangulo> ret = tree.get(r.getCoord().getX1());
		if(ret == null)
			return;
		
		ret.remove(r);
		if(ret.isEmpty())
			tree.delete(r.getCoord().getX1());
	}
	
	private void procuraIntersecoes(Retangulo r) { 
		for(int key : mengaoTree.keys(r.getCoord().getX1(), r.getCoord().getX2()))
			intervalTree.put(key, cloneRetangulos(mengaoTree.get(key)));
		
		for(int key : intervalTree.keys()) {
			LinkedHashSet<Retangulo> ret = intervalTree.get(key);
			if(ret != null) {
				ret.removeIf(rt -> rt.getCoord().getX2() < key);
				if(ret.isEmpty())
					intervalTree.delete(key);
			}
		}
		
		//System.out.println("Retângulo:  " + r);
		
		for(int k : intervalTree.keys()) {
			LinkedHashSet<Retangulo> newRets = new LinkedHashSet<>();
			for(Retangulo rr : intervalTree.get(k)) {
				if(haIntersecao(rr, r)) {
					newRets.addAll(removeIntersecao(rr, r));
					removeRetangulo(rr);
				}
			}

			intervalTree.get(k).removeIf(rr -> haIntersecao(r, rr));

			newRets.forEach(nr -> {
				putRetangulo(nr, intervalTree);
				putRetangulo(nr, mengaoTree);
			});
		}
	}
	
	/**
	 * Verifica se há interseção (sobreposição) entre os retângulos especificados.
	 * @param r1
	 * @param r2
	 * @return
	 */
	private boolean haIntersecao(Retangulo r1, Retangulo r2) {
		return r1 != r2 && ( (
					r1.getCoord().getX1() <= r2.getCoord().getX1() && r1.getCoord().getX2() > r2.getCoord().getX1() ||
					r1.getCoord().getX1() > r2.getCoord().getX1() && r1.getCoord().getX1() < r2.getCoord().getX2()
				) && (
					r1.getCoord().getY1() <= r2.getCoord().getY1() && r1.getCoord().getY2() > r2.getCoord().getY1() ||
					r1.getCoord().getY1() > r2.getCoord().getY1() && r1.getCoord().getY1() < r2.getCoord().getY2()
				) );
	}
	
	/**
	 * Retorna um retângulo formado pela interseção entre os dois retangulos especificados.
	 * @param r1
	 * @param r2
	 * @return
	 */
	private Retangulo calculaIntersecao(Retangulo r1, Retangulo r2) {
		if(!haIntersecao(r1, r2))
			return null;
		
		return new Retangulo(
						   Math.max(r1.getCoord().getX1(), r2.getCoord().getX1()),
						   Math.max(r1.getCoord().getY1(), r2.getCoord().getY1()),
						   Math.min(r1.getCoord().getX2(), r2.getCoord().getX2()),
						   Math.min(r1.getCoord().getY2(), r2.getCoord().getY2()),
						   r1.getCor()
						   );
	}
	
	/**
	 * Remove do primeiro retângulo a parte que faz interseção com o segundo. Esta operação pode gerar até 4 subretângulos.
	 * @param r1
	 * @param r2
	 * @return
	 */
	private List<Retangulo> removeIntersecao(Retangulo r1, Retangulo r2) {
		Retangulo intersec = calculaIntersecao(r1, r2);
		if(intersec == null)
			return null;
		
		//System.out.println("Interseção: " + "\n\t    " + r1 + "\n\t    " + r2 + "\n\t    " + intersec);
		List<Retangulo> newRets = new LinkedList<>();
		
		int[] intX = subtraiIntervalo(r1.getCoord().getX1(), r1.getCoord().getX2(), intersec.getCoord().getX1(), intersec.getCoord().getX2());
		if(intX[0] != -1)
			newRets.add(new Retangulo(intX[0], r1.getCoord().getY1(), intX[1], r1.getCoord().getY2(), r1.getCor()));
		if(intX[2] != -1)
			newRets.add(new Retangulo(intX[2], r1.getCoord().getY1(), intX[3], r1.getCoord().getY2(), r1.getCor()));
		
		int[] intY = subtraiIntervalo(r1.getCoord().getY1(), r1.getCoord().getY2(), intersec.getCoord().getY1(), intersec.getCoord().getY2());
		if(intY[0] != -1)
			newRets.add(new Retangulo(intersec.getCoord().getX1(), intY[0], intersec.getCoord().getX2(), intY[1], r1.getCor()));
		if(intY[2] != -1)
			newRets.add(new Retangulo(intersec.getCoord().getX1(), intY[2], intersec.getCoord().getX2(), intY[3], r1.getCor()));
		
		/*
		System.out.println("Remoção:");
		newRets.forEach(n -> System.out.println("\t    " + n));
		if(r1.area() - intersec.area() == newRets.stream().mapToInt(Retangulo::area).sum())
			System.out.println("\tÁrea correta");
		else
			System.out.println("\tERRO no cálculo de interseção!!!");
		System.out.println();
		*/
		return newRets;
	}
	
	/**
	 * Calcula a diferença entre dois intervalos.
	 * @param i1x1 Ínicio do primeiro intervalo
	 * @param i1x2 Final do primeiro intervalo
	 * @param i2x1 Ínicio do segundo intervalo
	 * @param i2x2 Final do segundo intervalo
	 * @return Primeiro intervalo, excluindo a parte que faz interseção com o segundo.<br>
	 * O início do intervalo está na posição 0, e o final na posição 1.<br>
	 * No caso do segundo intervalo estar completamente dentro do primeiro, serão retornados 2 intervalos (posições 0,1 e 2,3).
	 */
	private int[] subtraiIntervalo(int i1x1, int i1x2, int i2x1, int i2x2) {
		int[] intervalos = new int[] {-1, -1, -1,- 1};
		
		if(i1x1 <= i1x2) {
			intervalos[0] = i1x1;
			if(i1x2 <= i2x2)
				intervalos[1] = Math.min(i1x2, i2x1);
			else {
				intervalos[1] = i2x1;
				intervalos[2] = i2x2;
				intervalos[3] = i1x2;
			}
		} else {
			intervalos[0] = Math.max(i1x1 + 1, i2x2 + 1);
			intervalos[1] = i1x2;
		}
		
		if(intervalos[0] == intervalos[1]) {
			intervalos[0] = -1;
			intervalos[1] = -1;
		}
		
		if(intervalos[2] == intervalos[3]) {
			intervalos[2] = -1;
			intervalos[3] = -1;
		}
		
		return intervalos;
	}
	
	private LinkedHashSet<Retangulo> cloneRetangulos(LinkedHashSet<Retangulo> lista) {
		LinkedHashSet<Retangulo> novaLista = new LinkedHashSet<>();
		lista.forEach(r -> novaLista.add(r));
		return novaLista;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		mengaoTree.values().forEach(rList -> rList.forEach(r -> sb.append(r.toString()).append(System.lineSeparator())));
		
		return sb.toString();
	}
}
