package project01;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.log4j.Logger;
public class ConcordanceMain {
	final static Logger logger = Logger.getLogger(ConcordanceMain.class);
	public static void main(String[] args) throws IOException {
		ArrayList<String> stopWordsList = null;
		ArrayList<String> contentsList = null;
		ArrayList<String> resultList = null;
		HashMap<String, ArrayList<String>> indexMap = null;
		logger.info("Program execution started ");
		if (args.length == 0) {
			
			System.err.println("No Path specified");
			logger.error("No Path specified");
			System.exit(0);
		}

		//Path inputPath = ;

		Path fullPath = Paths.get(args[0]).toAbsolutePath();

		Concordance cd = new Concordance();
		String fileName = fullPath.toString() + "\\" + "english_stop.txt";

		System.out.println("Stop words file:" + fileName);
		cd.setFileName(fileName);

		stopWordsList = cd.readFile(cd.getFileName());
		if (stopWordsList.isEmpty()) {
			System.err.println("Stop Words List is Empty");
			logger.error("Stop Words List is Empty");
			System.exit(0);
		}

		System.out.println("Enter the filename:");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();

		if (!name.endsWith("txt")) {

			System.err.println("FileName incorrect");
			logger.error("Incorrect filename entered");
			System.exit(0);
		}

		fileName = fullPath.toString() + "\\" + name;

		// System.out.println("Stop words file:" + fileName);
		cd.setFileName(fileName);

		contentsList = cd.readFile(cd.getFileName());
		logger.info("Input File loaded ");
		indexMap = cd.buildIndex(contentsList, stopWordsList);

		// for (Entry<String, ArrayList<String>> entry :indexMap.entrySet())
		// {
		// System.out.println("key :" +entry.getKey());
		// System.out.println("Values:"+entry.getValue());
		// }
		logger.info("Index build completed");
		while (true) {
			System.out.println("Enter keyword :Press x to exit");
			String searchkey = scanner.next().toLowerCase();

			if (searchkey.equals("x"))
				break;

			if (indexMap.containsKey(searchkey)) {
				resultList = indexMap.get(searchkey);

				System.out.println(searchkey + ":" + resultList.size());
				int line_count = 0;
				for (String results : resultList) {
					line_count = contentsList.indexOf(results) + 1;
					System.out.println("Line "
							+ line_count
							+ ": "
							+ results.replaceAll(searchkey,
									searchkey.toUpperCase()));

				}
			} else {
				System.out
				.println("keyword not in document or its a stop word");
			}

		}
		logger.info("Program execution stopped ");
		scanner.close();
		System.exit(0);
	}

}
