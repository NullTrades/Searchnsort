/**
*
*    Title: ProgramTimer Class
*    <p>
*    Author: Osy Okocha and Simon Huang
*    <p>
*    Source: ObjectOrientedTextBasedRPG Project
*    <p>
*    I used the GameTimer class to create pauses in this code because I found that the start screen would be hidden and
*    feel static without a clear break.
*
*/

public class ProgramTimer {
    // wait function that takes in a time in seconds and "pauses" the console for that period of time
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // convert seconds to milliseconds - long data type
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
