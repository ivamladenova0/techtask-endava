package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils{

        /**
         * Reads files, going to be used in the API test when sending requests with body.
         * @param fileName
         * @return file as string.
         * @throws IOException
         */
        public static String readFile(String fileName) throws IOException {
            Path filePath = getResourcePath(fileName);
            byte[] encoded = Files.readAllBytes(filePath);
            return new String(encoded, Charset.defaultCharset());
        }


        /**
         * Gets the resource path.
         * @param fileName
         * @return
         */

        public static Path getResourcePath(String fileName) {
            try {
                return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }
}
