package pucrs.alest2.t1;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Arquivo não especificado.");
			return;
		}
		
		Canvas quadro = new Canvas();
		//CanvasMatrix matrix = new CanvasMatrix(1000);
		
		String file = args[0];
		Path path = Paths.get(file);
		
		//String file = "teste100000";
		//Path path = Paths.get(System.getProperty("user.dir"), "data", file);
		
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
					quadro.insereRetangulo(r);
					//matrix.paint(r);
				}
				if(n > 0 && n % 1000 == 0) {
					System.out.print(String.format("\r%d linhas processadas em %d ms", n, (System.currentTimeMillis() - startTime)));
					//System.out.print("\r" + n + " linhas processadas em " + (System.currentTimeMillis() - startTime) + " ms");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n");
		
		//System.out.println(quadro);
		//System.out.println(quadro.size() + " retângulos inseridos.");
		System.out.println("Canvas");
		quadro.getCores().entrySet().forEach(e -> System.out.println(String.format("%-20s %12d", e.getKey(), e.getValue())));
		//System.out.println("\nMatrix");
		//matrix.getCores().entrySet().forEach(e -> System.out.println(String.format("%-20s %12d", e.getKey(), e.getValue())));
	}

}
