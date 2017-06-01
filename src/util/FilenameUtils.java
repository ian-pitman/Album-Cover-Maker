package util;

public class FilenameUtils {

    public static String getExtension(String s) {
        String extension = "";
        
        int i = s.lastIndexOf('.');
        int p = Math.max(s.lastIndexOf('/'), s.lastIndexOf('\\'));
        
        extension = s.substring(i + 1, p);
        
        return extension;
    }
    
}
