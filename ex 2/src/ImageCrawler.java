package src;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents an image crawler that processes a single image URL.
 * It retrieves image data and outputs information based on the specified format.
 */
@SuppressWarnings({"StringTemplateMigration", "deprecation"})
public class ImageCrawler implements Runnable {
    private static final Set<String> processedUrls = new HashSet<>();

    private final String url;
    private final String outputFormat;

    /**
     * Constructs an ImageCrawler object with the specified URL and output format.
     *
     * @param url          The URL of the image to process.
     * @param outputFormat The format string specifying the output format.
     */
    public ImageCrawler(String url, String outputFormat) {
        this.url = url;
        this.outputFormat = outputFormat;
    }

    @Override
    public void run() {
        synchronized (processedUrls) {
            if (processedUrls.contains(url)) {
                return;
            }
            processedUrls.add(url);
        }

        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String contentType = conn.getContentType();
                if (contentType != null && contentType.startsWith("image/")) {
                    processedUrls.add(url);
                    long contentLength = conn.getContentLengthLong();
                    long elapsedTime = getResponseTime(url);
                    conn.getInputStream().close();

                    String output = formatOutput(contentLength, url, elapsedTime, contentType);
                    System.out.println(output);
                }
            }
        } catch (MalformedURLException e) {
            System.err.println(url + " malformed");
        } catch (IOException e) {
            System.err.println(url + " failed");
        }
    }

    /**
     * Formats the output string based on the specified parameters.
     *
     * @param contentLength The length of the image content.
     * @param url           The URL of the image.
     * @param elapsedTime   The elapsed time for the HTTP request.
     * @param contentType   The content type of the image.
     * @return The formatted output string.
     */
    private String formatOutput(long contentLength, String url, long elapsedTime, String contentType) {
        StringBuilder output = new StringBuilder();

        if (outputFormat != null) {
            if (outputFormat.contains("s")) {
                output.append(contentLength).append(" ");
            }
            if (outputFormat.contains("u")) {
                output.append(url).append(" ");
            }
            if (outputFormat.contains("t")) {
                output.append(elapsedTime).append(" ");
            }
            if (outputFormat.contains("m")) {
                output.append(contentType).append(" ");
            }
        }

        return output.toString().trim();
    }
    /**
     * Retrieves the response time for the specified URL.
     *
     * @param url The URL for which to retrieve the response time.
     * @return The response time in milliseconds.
     */
    private long getResponseTime(String url) {
        long startTime = System.currentTimeMillis();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();
        } catch (IOException e) {
            System.err.println("Error getting response time for URL: " + url + ". " + e.getMessage());
        }
        return System.currentTimeMillis() - startTime;
    }
}
