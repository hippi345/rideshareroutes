package com.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.*;
import static java.lang.System.in;

// TODO make an MD file for this thing when you push so people know its a POC
// TODO make the distances and computations make sense for future implementation in the GUI

// Long term
// TODO make a GUI (way out there)

// a sample program to allow for poc'ing the dynamic addition of rideshare vehicles for known routes of pickup and
// drops. we are wanting to effectively crowd source busing but with ride shares
// this lowers the overhead cost of buses and allows for drivers to have known and fixed routes
// this is an ideal concept for drivers who would rather run fixed routes for a fixed number of turns for a fixed
// pay per turn on the route
// it also allows faster scaling to demand in areas like college campuses and downtown areas
// it also allows for dynamic addition of "stops" without the infrastructure physically
// a "stop" can be any location on roads
// a "bus" can be any car on the road

public class Main
{
    // class variables

    // buses have a lookup GUID and then a space separated list of bus attributes
    // including the lookup string for its route
    static HashMap<String, String> buses = new HashMap<>();
    static ArrayList<String> busesCurrent = new ArrayList<>();

    // route ID current list
    static ArrayList<String> routeIds = new ArrayList<>();

    // routes have a lookup id as well as a space separated string list of
    // its attributes including lookup to its stops
    static HashMap<String, String> routes = new HashMap<>();

    // stops have a lookup id as well as a space separated list of its attributes
    static HashMap<String, String> stops = new HashMap<>();

    // list of buses and their current stop
    static HashMap<String, String> busStops = new HashMap<>();

    // list of buses and their current passenger count
    static HashMap<String, String> busPassengers = new HashMap<>();

    // to take another command or not. initially false such as to not do false runs
    static boolean takeCommandOrNot = false;

    // we need a boolean value for circumventing the manual input on commands when a user uploads a file of commands
    static boolean continueOnBatch = false;

    // success and fail command counters
    static int successCount = 0;
    static int failCount = 0;

    public static void main(String[] args) throws IOException {
        // exit code index:
        // 1 -> no valid arguments
        // 2 -> command not valid
        // 3 -> command issue to end run
        // 4 -> improper command on continue to submit commands

        Timestamp timestamp = new Timestamp(currentTimeMillis());
        Logger logger = Logger.getLogger("Logs");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        FileHandler fh = new FileHandler("src/com/company/logs/" + sdf.format(timestamp));
        logger.addHandler(fh);

        out.println("What is the first command to do? *type -h or help --help for usage details*");
        Scanner cmd = new Scanner(in);
        // initial command to run
        String initialRun = cmd.nextLine();

        // LOGGING
        logger.log(Level.INFO, initialRun);

        if (!inputValidation.checkForHelp(initialRun.split(" "))) {
            // command cannot be zero length
            if (initialRun.split(" ").length == 0) {
                out.println("No valid arguments that represent commands.");
                failCount++;

                // opportunity to issue a new command
                Scanner scanner = new Scanner(in);
                out.println("Would you like to issue another command (y) or quit (n)?");
                String input = scanner.nextLine();
                if (input.equals("y")) {
                    out.println("What is the next command to enter?");
                    String newCmd = scanner.nextLine();
                    // LOGGING
                    logger.log(Level.INFO, newCmd);
                    readArgs.readingArgs(newCmd.split(" "));
                } else if (input.equals("n")) {
                    out.println("Exiting...");
                    logger.log(Level.INFO, "Exited at: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"));
                    exit(3);
                } else {
                    failCount++;
                    out.println("That was an invalid response: sending to help ->");
                    String[] blankArg = new String[]{"help"};
                    readArgs.readingArgs(blankArg);
                }
            }
        }

        // initial run
        readArgs.readingArgs(initialRun.split(" "));

        // initial run will set the take command or note val on completion and input from the user to go again
        while(takeCommandOrNot)
        {
            Scanner toContinue = new Scanner(in);
            out.println("What is the next command you would like to issue?");
            String input = toContinue.nextLine();

            if (!inputValidation.checkForHelp(input.split(" "))) {
                logger.log(Level.INFO, input);
                readArgs.readingArgs(input.split(" "));
            }
        }
            // if the value is set to false then the program will end with status code 3
            out.println("Exiting program on no further commands...");
            logger.log(Level.INFO, "Exited at: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"));
            exit(3);
    }
}