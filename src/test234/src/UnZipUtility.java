import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *  * This utility extracts files and directories of a standard zip file to
 *  * a destination directory.
 *  * @author www.codejava.net
 *  *
 *  
 */
public class UnZipUtility {


    /**
     *  * Size of the buffer to read/write data
     *  
     */
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        System.out.println(args[0] +","+ args[1]+","+args[2]);

        File zipFile = new File(args[0]);
        File xmlOutFile = new File(args[1], args[2]);
/*
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
*/

        new UnZipUtility().unzip(zipFile, xmlOutFile);
    }
    /**
     *  * Extracts a zip file specified by the zipFilePath to a directory specified by
     *  * destDirectory (will be created if does not exists)
     *  * @param zipFilePath
     *  * @param destDirectory
     *  * @throws IOException
     *  
     */
    public void unzip(File zipFile, File xmlOutFile)  {

        try {
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            if (entry != null) {
                extractFile(zipIn, xmlOutFile); //we just need the first entry;
                zipIn.closeEntry();
            }
            zipIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }

    /**
     *  * Extracts a zip entry (file entry)
     *  * @param zipIn
     *  * @param filePath
     *  * @throws IOException
     *  
     */
    private void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}