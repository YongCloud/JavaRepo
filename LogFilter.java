import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

/**
 * Log filter utility
 * 
 * @author xingjian
 * @since 2019/12/23
 */
public class LogFilter {
    public static void main(String[] args) {
        filter("signalStatus_1.log", 10, "filtered.log");
    }

    /**
     * filter log with start line number.
     * 
     * @param logFileName
     * @param startLineNumber
     * @param outFileName
     */
    public static void filter(String logFileName, int startLineNumber, String outFileName) {
        checkFileName(logFileName);
        checkFileName(outFileName);

        File logFile = new File(logFileName);
        if (!logFile.exists()) {
            throw new IllegalArgumentException("log file doesn't exist.");
        }

        try (LineNumberReader reader = new LineNumberReader(new FileReader(logFile));
            PrintStream ps = new PrintStream(outFileName)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                int number = reader.getLineNumber();
                if (number >= startLineNumber) {
                    ps.println(line);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkFileName(String fileName) {
        if (null == fileName || fileName.isEmpty()) {
            throw new IllegalArgumentException("logFileName is empty.");
        }
    }
}
