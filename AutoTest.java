import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 * Write a description of class AutoHarness here.
 * 
 * @author Stephan Jamieson	
 * @version 7/3/2016
 */
public abstract class AutoTest {

    protected AutoTest() {}	
    
    protected abstract HashTable makeTable(final int size);
    
			
    public void run() {
		final Scanner scanner = new Scanner(System.in);
        assert(scanner.hasNextLine());
		final int size = Integer.parseInt(scanner.nextLine().trim());
		
        final HashTable table = makeTable(size);

        while (scanner.hasNextLine()) {
            Scanner parse = new Scanner(scanner.nextLine());
            assert(parse.hasNext());
            final String command = parse.next().trim().toUpperCase();
            if (command.equals("INSERT")) {
                assert(parse.hasNext());
                try {
                    table.insert(parse.nextLine().trim());
                }
                catch (IllegalStateException illExcep) {
                    System.out.println("Insert failed. Table full?");
                }
            }
            else if (command.equals("ISEMPTY")) {
				//System.out.print("isEmpty(): ");
				System.out.println(table.isEmpty());
            }
            else if (command.equals("SIZE")) {
				//System.out.print("Size: ");
				System.out.println(table.size());
            }
            else if (command.equals("DUMP")) {
				table.dump();
				System.out.println();
			}
            else if (command.equals("CONTAINS")) {
                assert(parse.hasNext());
                final String username = parse.next();
                //System.out.print("Contains \'"+word+"\': ");
                System.out.println(table.contains(username));
            }
            else if (command.equals("PROBECOUNT")) {
                System.out.println(table.getProbeCount());
            }
            else if (command.equals("RESETCOUNT")) {
                    table.resetProbeCount();
            }
            else if (command.equals("QUIT")) {
                System.exit(0);
            }
            else if (command.equals("LOAD")) {
				assert(parse.hasNext());
                try {
                    final Scanner fileIn = new Scanner(new File(parse.next()));
                    assert(parse.hasNextInt());
                    int quantity = parse.nextInt();
                    while (quantity>0) {
                        table.insert(fileIn.nextLine());
                        quantity--;
                    }
                    fileIn.close();
                }
                catch (FileNotFoundException fnfExep) {
                    System.out.println("File not found.");
                }
                catch (IllegalStateException illExcep) {
                    System.out.println("Insert failure. Table full?");
                }
			}
        }
    }

}
