import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Handles Clock objects and manipulates them.
 *
 * @author Isaac Ham
 * @version 02 10 2024
 */

public class ClockShop {
    /**
     * Ensures that newlines ("\n") are portable.
     */
    private static final String NEW_LINE = System.lineSeparator();
    /**
     * Stores all {@code Clock} objects that are read from user input (and/or file).
     */
    private ArrayList<Clock> myClocks;

    /**
     * Instantiates an ArrayList of {@code Clock}.
     */
    public ClockShop() {
        myClocks = new ArrayList<>();
    }

    /**
     * Reads input from a specified file and populates {@code myClocks}.
     *
     * @param theInputFileName file to be read from
     */
    public void fillClockShop(final String theInputFileName) {
        try {
            final Scanner inputFile = new Scanner(new File(theInputFileName));
            inputFile.useDelimiter("[:" + NEW_LINE + "]+");
            while (inputFile.hasNext()) {
                final int hour = inputFile.nextInt();
                final int minute = inputFile.nextInt();
                final int second = inputFile.nextInt();
                myClocks.add(new Clock(hour, minute, second));
            }
            inputFile.close();
        } catch (final FileNotFoundException e) {
            throw new RuntimeException("File was not found: " + theInputFileName);
        }
    }

    /**
     * Adds a {@code Clock} object to {@code myClocks}.
     *
     * @param theClock {@code Clock} to be added
     */
    public void addClock(final Clock theClock) {
        myClocks.add(theClock);
    }

    /**
     * Sorts the objects in {@code myClocks} linearly by value (ascending order).
     *
     */
    public void sortClocks() {
        for (int i = 0; i < myClocks.size() - 1; i++)
            for (int j = 0; j < myClocks.size() - i - 1; j++)
                if (myClocks.get(j).compareTo(myClocks.get(j + 1)) > 0) {
                    final Clock temp = myClocks.get(j);
                    myClocks.set(j, myClocks.get(j + 1));
                    myClocks.set(j + 1, temp);
                }
    }

    /**
     * Creates and sets a {@code Clock} object to the current time.
     */
    public void setToCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        final int second = calendar.get(Calendar.SECOND);
        myClocks.add(new Clock(hour, minute, second));
    }

    /**
     * Creates and sets a {@code Clock} object to the current time. Validates {@code setToCurrentTime}.
     */
    public void checkCurrentTime() {
        LocalTime time = LocalTime.now();
        int hour = time.getHour() % 12;
        int minute = time.getMinute();
        int second = time.getSecond();
        myClocks.add(new Clock(hour, minute, second));
    }

    /**
     * Figures out where an item is in {@code myClocks}.
     *
     * @param theClock {@code Clock} object to be searched for
     * @return the position of {@code theClock}
     */
    public int findClock(final Clock theClock) {
        int index = 0;
        for (Clock clock:myClocks) {
            if (clock.compareTo(theClock) == 0)
                return index;
            index++;
        }
        return -1;
    }

    /**
     * Returns a list with the specified time for each and every {@code Clock} object in {@code myClock}.
     *
     * @return strings that contain the hour, minute, and second
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (Clock clock:myClocks)
            result.append(clock.toString()).append(NEW_LINE);
        return result.toString();
    }

    /**
     * Retrieves a {@code Clock} object in {@code myClocks}.
     *
     * @param theIndex specifies the location of the {@code Clock} object
     * @return the searched {@code Clock}
     */
    public Clock getClock(final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size())
            throw new IllegalArgumentException("Index is outside the range of the array" + theIndex);
        return myClocks.get(theIndex);
    }

    /**
     * Puts a {@code Clock} object at the specific index within {@code myClocks}.
     *
     * @param theClock the {@code Clock} object to be placed
     * @param theIndex where the {@code Clock} is added
     */
    public void setClock(final Clock theClock, final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size())
            throw new IllegalArgumentException("Index is out of range for the array" + theIndex);
        myClocks.set(theIndex, theClock);
    }

    /**
     * Writes {@code Clock} objects to the specified file.
     *
     * @param theFilename the file to be populated
     */
    public void writeClocksToFile(final String theFilename) {
        try (FileWriter writer = new FileWriter(theFilename)) {
            writer.write(this.toString());
        } catch (final IOException e) {
            System.out.println("File not found: " + theFilename);
        }
    }
}
