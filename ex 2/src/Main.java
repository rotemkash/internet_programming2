package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class contains the main entry point of the program.
 * It orchestrates the retrieval and processing of image URLs.
 */
@SuppressWarnings("StringTemplateMigration")
public class Main {
    /**
     * Main entry point of the program.
     *
     * @param args Command-line arguments:
     *             args[0] - Output format string (e.g., "sut")
     *             args[1] - Pool size for the thread pool
     *             args[2] - Input file path containing URLs
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Main <output_format> <pool_size> <input_file>");
            System.exit(1);
        }

        String outputFormat = args[0];
        int poolSize = Integer.parseInt(args[1]);
        String inputFile = args[2];

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        BufferedReader fileReader = null;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            fileReader = new BufferedReader(new FileReader(inputFile));
            String line;

            // Read from file
            while ((line = fileReader.readLine()) != null) {
                executorService.execute(new ImageCrawler(line, outputFormat));
            }

            // Read from terminal
            System.out.println("Enter URLs (press Enter on an empty line to stop):");
            while ((line = terminalReader.readLine()) != null && !line.isEmpty()) {
                executorService.execute(new ImageCrawler(line, outputFormat));
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            System.exit(1);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }

        executorService.shutdown();
    }
}
