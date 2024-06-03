import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class DataMaker {

    private DataMaker() {}

    final static String FILE_NAME = "students.txt";
    private static boolean isInteger(final String string) {
        for(int i=0; i<string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static void main(final String[] args) {
        if (args.length!=2 || args[0].length()!=9 || !isInteger(args[1])) {
            System.out.println("java DataMaker <username> <quantity>");
        }
        else {
            final String username = args[0];

            final List<String> allNames = new ArrayList<String>();
            try {
                final Scanner scanner = new Scanner(new File(FILE_NAME));
                while (scanner.hasNextLine()) { allNames.add(scanner.nextLine().trim()); }
                scanner.close();
            }
            catch (FileNotFoundException fnfE) {
                System.out.printf("File '%s' not found.\n", FILE_NAME);
                System.exit(-1);
            }

            // Output personalised list
            int index = allNames.indexOf(username);
            if (index == -1) {
                System.out.printf("Username '%s' not found.", username);
                System.exit(-1);
            }

            for(int size = Integer.parseInt(args[1]); size>0; size--) {
                System.out.println(allNames.get(index));
                index = (index+1)%allNames.size();
            }
        }
    }

}
