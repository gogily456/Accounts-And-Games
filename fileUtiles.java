import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class fileUtiles
{
    // checks if the given file exists and return true is it does exist
    public static boolean exists(String path)
    {
        File f = new File(path);
        return f.exists();

    }

    /* creating a file in your computer
    for ex - C:\\Games1
    it will be stored in C: and the file name will be Games1
    param path - the path of the file
    param createParent - give the value true for the file to be created
     */
    public static boolean create(String path, boolean createParent)
    {
        File f = new File(path);

        if(createParent)
        {
            return f.mkdirs();
        }
        return f.mkdir();
    }

    /*
    deleting a file from your computer
     */
    public static boolean delete(String path)
    {
        File f = new File(path);
        return f.delete();
    }

    /*
    write in the file in this case
    it will get a username and a password and it will write it in the file
     */
    public static void writeFile(String path, String userName, String password) throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(path, true);
        outputStream.write(userName.getBytes());
        outputStream.write(password.getBytes());
        outputStream.close();
    }

    /*
    checking if a word exist in the file
    for ex it this case if a username and a password are found in the file
     */
    public static boolean found(String userName, String password, String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        boolean word1Found = false;
        boolean word2Found = false;

        while((line = br.readLine()) != null)
        {
            if(line.contains(userName))
            {
                word1Found = true;

            }
            if(line.contains(password))
            {
                word2Found = true;
            }
            if(word1Found && word2Found)
            {
                break;
            }
        }

        if(word1Found && word2Found)
        {
            System.out.println("you logged in");
            return true;
        }
        else
        {
            System.out.println("password or username are incorrect \n try again, or sigh in");
            return false;
        }

    }
}


