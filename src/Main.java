import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Canvas quadro = new Canvas();
		
		String file = "teste000100";
		
		Path path = Paths.get(System.getProperty("user.dir"), "data", file);
		
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
				}
				if(n > 0 && n % 1000 == 0) {
					System.out.println(n + " - " + (System.currentTimeMillis() - startTime));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(quadro);
		//System.out.println(quadro.size() + " retângulos inseridos.");
		quadro.getCores().entrySet().forEach(e -> System.out.println(String.format("%-20s %12d", e.getKey(), e.getValue())));
	}

}
