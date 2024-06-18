package FaultHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FaultMonitor {
    private String component;
    private static final String LOGGER_PATH = "."+ File.separator +"src"+ File.separator + "Logger" + File.separator +"failure_log.txt";

    // logs failures
    public static void handleFault(String component){
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();

        FileWriter fw = null;
        try
        {
            fw = new FileWriter(LOGGER_PATH,true);
            fw.write("\n\n" + component + " component failed on : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }finally {
            try {
                fw.close();
            }catch (IOException | NullPointerException io){
                System.err.println("IOException: " + io.getMessage());
            }
        }
    }
}
