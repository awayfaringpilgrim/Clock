/**
 * Represents a clock (hours, minutes, and seconds).
 *
 * @author Isaac Ham
 * @version 02 10 2024
 */

public class Clock implements Comparable<Clock> {
    /**
     * Max hours in a day.
     */
    private static final int MAX_HOURS = 23;
    /**
     * Max minutes in an hour (before incrementally adding an hour).
     */
    private static final int MAX_MINUTES = 59;
    /**
     * Max seconds in a minute (before incrementally adding a minute).
     */
    private static final int MAX_SECONDS = 59;
    /**
     * Total number of hours in a day.
     */
    private static final int HOURS_IN_DAY = 24;
    /**
     * Total number of minutes in an hour.
     */
    private static final int MINUTES_IN_HOUR = 60;
    /**
     * Hour of day (0-23).
     */
    private int myHour;
    /**
     * Minute of hour (0-59).
     */
    private int myMinute;
    /**
     * Second of minute (0-59).
     */
    private int mySecond;

    /**
     * Constructs a Clock instance with the specified time.
     *
     * @param theHour the hour to be set
     * @param theMinute the minute to be set
     * @param theSecond the second to be set
     */
    public Clock(final int theHour, final int theMinute, final int theSecond) {
        setHour(theHour);
        setMinute(theMinute);
        setSecond(theSecond);
    }

    /**
     * Constructor sets the clock to 23:58:00.
     */
    public Clock() {
        this(MAX_HOURS, MAX_MINUTES - 1, 0);
    }

    /**
     * Returns a string with the specified time in hh:mm:ss format.
     *
     * @return a string that contains the hour, minute, and second
     */
    @Override
    public String toString() {
        return myHour + ":" + myMinute + ":" + mySecond;
    }

    /**
     * Retrieves the hour.
     *
     * @return an integer value of the hour
     */
    public int getHour() {
        return myHour;
    }

    /**
     * Retrieves the minute.
     *
     * @return an integer value of the minute
     */
    public int getMinute() {
        return myMinute;
    }

    /**
     * Retrieves the second.
     *
     * @return an integer value of the second
     */
    public int getSecond() {
        return mySecond;
    }

    /**
     * Sets the hour of the clock to the parameter, {@code theHour}.
     *
     * @param theHour the hour to be set that has to be within the range of (0 to {@code MAX_HOURS}).
     *                If the hour is outside this range, an {@link IllegalArgumentException} is thrown
     */
    public void setHour(final int theHour) {
        if (theHour < 0 || theHour > MAX_HOURS)
            throw new IllegalArgumentException("Hour passed to setHour out of range: " + theHour);
        myHour = theHour;
    }

    /**
     * Sets the hour of the clock to the parameter, {@code theMinute}.
     *
     * @param theMinute the hour to be set that has to be within the range of (0 to {@code MAX_MINUTES}).
     *                  If the minute is outside this range, an {@link IllegalArgumentException} is thrown
     */
    public void setMinute(final int theMinute) {
        if (theMinute < 0 || theMinute > MAX_MINUTES)
            throw new IllegalArgumentException("Minute passed to setMinute out of range: " + theMinute);
        myMinute = theMinute;
    }

    /**
     * Sets the second of the clock to the parameter, {@code theSecond}.
     *
     * @param theSecond the minute to be set that has to be within the range of (0 to {@code MAX_SECONDS}).
     *                  If the second is outside this range, an {@link IllegalArgumentException} is thrown
     */
    public void setSecond(final int theSecond) {
        if (theSecond < 0 || theSecond > MAX_SECONDS)
            throw new IllegalArgumentException("Second passed to setSecond out of range: " + theSecond);
        mySecond = theSecond;
    }

    /**
     * Advances the hour by the parameter, {@code theAmount}.
     *
     * @param theAmount the number of hours to be added. If the amount exceeds the limit, it is wrapped around.
     *                  If the amount is negative, an {@link IllegalArgumentException} is thrown
     */
    public void advanceHour(final int theAmount) {
        if (theAmount < 0)
            throw new IllegalArgumentException("Added hours cannot be negative" + theAmount);
        myHour = (myHour + theAmount) % HOURS_IN_DAY;
    }

    /**
     * Advances the minute by the parameter, {@code theAmount}.
     *
     * @param theAmount the number of minutes to be added. If the amount exceeds the limit, it is wrapped around.
     *                  A call to the {@code advanceHour()} method is then called.
     *                  If the amount is negative, an {@link IllegalArgumentException} is thrown
     */
    public void advanceMinute(final int theAmount) {
        if (theAmount < 0)
            throw new IllegalArgumentException("Added minutes cannot be negative" + theAmount);
        final int addHours = (myMinute + theAmount) / MINUTES_IN_HOUR;
        myMinute = (myMinute + theAmount) % MINUTES_IN_HOUR;
        if (addHours > 0)
            advanceHour(addHours);
    }

    /**
     * Validates if this Clock instance is equal to the other object.
     *
     * @param theObject the object to be compared
     * @return true if {@code theObject} is equal to the Clock instance and false if it isn't
     */
    @Override
    public boolean equals(final Object theObject) {
        if (theObject == null || !theObject.getClass().getSimpleName().equals("Clock"))
            return false;
        final Clock theClock = (Clock) theObject;
        return compareTo(theClock) == 0;
    }

    /**
     * Returns a hashcode value.
     *
     * @return an integer hashcode.
     */
    @Override
    public int hashCode() {
        return mySecond + myMinute + myHour;
    }


    /**
     * Compares this Clock instance with another Clock.
     *
     * @param theOtherClock the object to be compared
     * @return an integer value that is either negative, zero, or positive (less than, equal to, or greater than)
     */
    public int compareTo(final Clock theOtherClock) {
        if (myHour != theOtherClock.myHour)
            return Integer.compare(myHour, theOtherClock.myHour);
        if (myMinute != theOtherClock.myMinute)
            return Integer.compare(myMinute, theOtherClock.myMinute);
        return Integer.compare(mySecond, theOtherClock.mySecond);
    }
}
