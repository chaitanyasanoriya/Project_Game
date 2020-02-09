package filehanlding;

public class FileHandlingException extends Exception
{
    private final String error = "File may not be initialized";

    @Override
    public String getMessage()
    {
        return this.error;
    }
}
