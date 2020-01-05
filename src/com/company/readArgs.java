package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Main.*;

class readArgs
{
    // reading in the args and determining whether to keep running or quit
    static void readingArgs(String[] args)
    {
        // format of args should be as follows
        // command <args for that command>
        ArrayList<Object> suitableCommands = new ArrayList<>();
        // adding the commands
        suitableCommands.add("add_bus");
        suitableCommands.add("add_stop");
        suitableCommands.add("add_route");
        suitableCommands.add("add_route_for_bus");
        suitableCommands.add("add_stop_to_route");
        suitableCommands.add("upload_commands");
        suitableCommands.add("start_sim");
        suitableCommands.add("sys_report");
        suitableCommands.add("help");
        suitableCommands.add("-h");
        suitableCommands.add("--help");

        // check that command is a valid command
        if (!(suitableCommands.contains(args[0])))
        {
            // if its a batch then run the next line without quitting
            if (Main.continueOnBatch)
            {
                System.out.println("Command invalid: " + args[0] + " is not a valid command.");
                Main.failCount++;
                return;
            }
            else
            {
                System.out.println("Command invalid: " + args[0] + " is not a valid command.");
                Main.failCount++;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Would you like to issue another command (y) or quit (n)?");
                String input = scanner.nextLine();
                if (input.equals("y"))
                {
                    System.out.println("What is the next command to enter?");
                    String newCmd = scanner.nextLine();

                    if (!inputValidation.checkForHelp(newCmd.split(" "))) {
                        readingArgs(newCmd.split(" "));
                    }
                }
                else if (input.equals("n"))
                {
                    System.out.println("Exiting...");
                    System.exit(3);
                }
                else
                {
                    Main.failCount++;
                    System.out.println("That was an invalid repsonse: sending to help -> ");
                    String[] blankArg = new String[]{"help"};
                    readingArgs(blankArg);
                }
            }
        }
        else
        {

            // adding a bus
            switch (args[0]) {
                case "add_bus":
                    if (args.length != 5)
                    {
                        Main.failCount++;
                        System.out.println("Improper args provided...returning...");
                    }
                    else {
                        String blankRoute = "blank_route";
                        String busId = args[1];
                        String busName = args[2];
                        String capacity = args[3];
                        String speed = args[4];

                        // check to see if the bus already exists
                        if (Main.buses.get(busId) != null)
                        {
                            Main.failCount++;
                            System.out.println("Bus already exists");
                            break;
                        }
                        else {
                            Main.successCount++;
                            Main.buses.put(busId, busName + " " + blankRoute + " " + capacity + " " + speed);
                        }
                        Main.busesCurrent.add(busId + " " + busName);
                        break;
                    }
                    break;

                // adding a stop
                case "add_stop":
                    if (args.length != 5)
                    {
                        Main.failCount++;
                        System.out.println("Improper args provided...returning...");
                    }
                    else {
                        if (Main.stops.get(args[1]) == null) {
                            createMethods.addStop(args[1], args[2], args[3], args[4], Main.stops);
                            Main.successCount++;
                            break;
                        }
                        else
                        {
                            Main.failCount++;
                            System.out.println("Stop already exists");
                        }
                    }
                    break;

                // adding route to bus
                case "add_route_for_bus":
                    if (args.length != 3)
                    {
                        Main.failCount++;
                        System.out.println("Improper args...returning");
                    }
                    else {
                        String routeToAdd = args[1];
                        String busToAddTo = args[2];
                        String[] busArgs = Main.buses.get(busToAddTo).split(" ");
                        Main.successCount++;
                        Main.buses.put(busToAddTo, busArgs[0] + " " + routeToAdd + " " + busArgs[2] + " " + busArgs[3]);
                        break;
                    }
                    break;

                // adding a route
                case "add_route":
                    if (args.length != 3)
                    {
                        Main.failCount++;
                        System.out.println("Improper args...returning");
                    }
                    else {
                        if (routes.get(args[1]) == null) {
                            Main.successCount++;
                            createMethods.createRoute(args[1], args[2], routes, routeIds);
                            break;
                        }
                    }
                    break;

                // adding a stop to a route
                case "add_stop_to_route":
                    if (args.length != 3)
                    {
                        Main.failCount++;
                        System.out.println("Improper args...returning");
                        break;
                    }
                    else {
                        Main.successCount++;
                        createMethods.addStopToRoute(args[1], args[2], routes);
                        break;
                    }

                    // uploading commands
                case "upload_commands":
                    if (args.length != 2)
                    {
                        Main.failCount++;
                        System.out.println("Improper args...returning");
                        break;
                    }
                    else {
                        Main.successCount++;
                        uploadCommandsClass.uploadCommands(args[1]);
                        break;
                    }

                    // starting the simulation
                case "start_sim":
                    if (args.length != 2)
                    {
                        failCount++;
                        System.out.println("Improper args...returning");
                        break;
                    }
                    else {
                        successCount++;
                        simulations.startSim(args[1]);
                        break;
                    }

                    // running a system report
                case "sys_report":
                    if (args.length != 2)
                    {
                        failCount++;
                        System.out.println("Improper args...returning");
                        break;
                    }
                    else {
                        successCount++;
                        systemReport.systemReport(args[1]);
                        break;
                    }

                case "help":
                    Usage.usage(args);
                    break;
                case "-h":
                    Usage.usage(args);
                    break;
                case "--help":
                    Usage.usage(args);
                    break;
            }
        }

        // if the command if a batch file then circumvent the manual input
        if (continueOnBatch)
        {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to issue another command (y) or quit (n)?");
        String input = scanner.nextLine();
        if (input.equals("y"))
        {
            takeCommandOrNot = true;
        }
        else if (input.equals("n"))
        {
            gui.makeTheGui(buses, routes, stops, successCount, failCount, busesCurrent);
            takeCommandOrNot = false;
        }
        else
        {
            failCount++;
            System.out.println("That was an invalid response: sending to help ->");
            String[] blankArg = new String[]{"help"};
            readingArgs(blankArg);
        }
    }
}
