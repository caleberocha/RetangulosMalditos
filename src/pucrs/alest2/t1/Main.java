package pucrs.alest2.t1;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	/**
	 * Uso:<br>
	 * <ul>
	 * <li><b>RetangulosMalditos arquivo</b> - Processa o arquivo especificado.
	 * <li><b>RetangulosMalditos arquivo -useMatrix 1000</b> - Utiliza uma matriz para processar os dados. O último parâmetro é o tamanho da matriz.
	 */
	public static void main(String[] args) {
		Canvas quadro = null;
		CanvasMatrix matrix = null;
		boolean useMatrix = false;
		int matrixSize = 0;
		boolean timeShowed = false;
		
		if(args.length == 0) {
			System.err.println("Arquivo não especificado." + System.lineSeparator()
							 + "USO:" + System.lineSeparator()
							 + "java -jar retangulos.jar nome_do_arquivo" + System.lineSeparator()
							 + "java -jar retangulos.jar nome_do_arquivo -useMatrix tamanho_matriz");
			return;
		}
		
		if(args.length > 2) {
			if(args[1].equals("-useMatrix") && args[2].matches("[0-9]+")) {
				useMatrix = true;
				matrixSize = Integer.parseInt(args[2]);
			}
		}
		
		String file = args[0];
		Path path = Paths.get(file);
		
		//String file = "teste100000";
		//Path path = Paths.get(System.getProperty("user.dir"), "data", file);
		if(!useMatrix)
			quadro = new Canvas();
		else
			matrix = new CanvasMatrix(matrixSize);
		
		try(Scanner sc = new Scanner(path.toFile())) {
			int n = 0;
			long startTime = System.currentTimeMillis();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				Pattern p = Pattern.compile("^([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([A-Za-z\\-]+)");
				Matcher m = p.matcher(line);
				if(m.matches()) {
					//System.out.println(line);
					n++;
					Retangulo r = new Retangulo(new Coordenadas(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))), m.group(5));
					if(!useMatrix)
						quadro.insereRetangulo(r);
					else
						matrix.paint(r);
				}
				if(n > 0 && n % 1000 == 0) {
					timeShowed = true;
					System.out.print(String.format("\r%d linhas processadas em %d ms", n, (System.currentTimeMillis() - startTime)));
					//System.out.print("\r" + n + " linhas processadas em " + (System.currentTimeMillis() - startTime) + " ms");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(timeShowed)
			System.out.println(System.lineSeparator());
		
		//System.out.println(quadro);
		//System.out.println(quadro.size() + " retângulos inseridos.");
		if(!useMatrix)
			quadro.getCores().entrySet().forEach(e -> System.out.println(String.format("%-20s %12d", e.getKey(), e.getValue())));
		else
			matrix.getCores().entrySet().forEach(e -> System.out.println(String.format("%-20s %12d", e.getKey(), e.getValue())));
	}

}
