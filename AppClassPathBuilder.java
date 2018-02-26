import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Auto build class path where the system(or application) class loader search
 * classes. The path is a folder which stores all jars.
 * 
 * @author YangYong
 * @version 1.0.0 2018/02/25
 *
 */
public class AppClassPathBuilder {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:java AppClassPathBuilder jarPath");
			System.exit(1);
		}

		Path path = Paths.get(args[0]);
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(path, "*.jar")) {
			String separator = System.getProperty("path.separator");
			StringBuilder classPath = new StringBuilder();

			for (Path entry : entries) {
				classPath.append(entry + separator);
			}

			System.out.println(classPath.substring(0, classPath.length() - 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
