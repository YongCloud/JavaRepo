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
        filter("signalStatus_1.log", "[pool-12-thread-1]", "filtered.log");
    }

    /**
     * filter log with start line number.
     * 
     * @param logFileName
     * @param startLineNumber
     * @param outFileName
     */
    public static void filter(String logFileName, int startLineNumber, String outFileName) {
        checkParam(logFileName, outFileName);
        try (LineNumberReader reader = new LineNumberReader(new FileReader(logFileName));
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

    /**
     * filter log with start keyword.
     * 
     * @param logFileName
     * @param keyword
     * @param outFileName
     */
    public static void filter(String logFileName, String keyword, String outFileName) {
        checkParam(logFileName, outFileName);
        try (LineNumberReader reader = new LineNumberReader(new FileReader(logFileName));
            PrintStream ps = new PrintStream(outFileName)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    ps.println(line);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParam(String logFileName, String outFileName) {
        if (null == logFileName || logFileName.isEmpty()) {
            throw new IllegalArgumentException("logFileName is empty.");
        }
        File logFile = new File(logFileName);
        if (!logFile.exists()) {
            throw new IllegalArgumentException("log file doesn't exist.");
        }
        if (null == outFileName || outFileName.isEmpty()) {
            throw new IllegalArgumentException("outFileName is empty.");
        }
    }
}
