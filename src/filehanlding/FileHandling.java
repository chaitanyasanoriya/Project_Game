package filehanlding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandling
{
    private static File mFile = null;
    private static FileWriter mFileWriter = null;

    public static boolean createNewFile() throws IOException
    {
        boolean result = false;
        String file_name = "/Users/chaitanyasanoriya/Desktop/GameFile.txt";
        File file = new File(file_name);
        if (file.exists())
        {
            file.delete();
        }
        if (file.createNewFile())
        {
            result = true;
            FileHandling.mFile = file;
        }
        return result;
    }

    public static void createFileWriterObject() throws IOException
    {
        FileHandling.mFileWriter = new FileWriter(FileHandling.mFile,true);
    }

    public static void writeToFile(String str) throws FileHandlingException, IOException
    {
        if(FileHandling.mFileWriter==null)
        {
            throw new FileHandlingException();
        }
        else
        {
            FileHandling.mFileWriter.append(str);
            FileHandling.mFileWriter.append("\n");
        }
    }

    public static void closeFileWriterObject() throws IOException
    {
        if(FileHandling.mFileWriter!=null)
        {
            FileHandling.mFileWriter.close();
        }
    }
}
