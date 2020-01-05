package com.company;

class Usage
{
    static void usage(String[] help_args)
    {
        if (help_args.length == 1)
        {
            System.out.println("RideShare Routes information for usage.");
            System.out.println("You can enter *help/-h/--help* followed by any of the below commands"
            + " to get the help for just that specific command.");
            System.out.println("Valid commands: ");

            // add bus
            System.out.println("add_bus [bus_id] [bus_name] [capacity] [speed]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - bus_id -> any number or unique identifier in context of the scale of use");
            System.out.println("\t - bus_name -> any name for the bus for user reference and familiarity");
            System.out.println("\t - capacity -> number representing the number of passengers possible in the vehicle");
            System.out.println("\t - speed -> number representing the speed of the vehicle on average for traveling routes");

            // add stop
            System.out.println("add_stop [stop_id] [stop_name] [x] [y]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - stop_id -> any number or unique identifier in context of the scale of use");
            System.out.println("\t - stop_name -> any name for the stop for user reference and familiarity");
            System.out.println("\t - x -> x location for geographical mapping");
            System.out.println("\t - y -> y location for geographical mapping");

            // add route
            System.out.println("add_route [route_id] [route_name]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - route_id -> any number or unique identifier in context of the scale of use");
            System.out.println("\t - route_name -> any name for the route for user reference and familiarity");

            // add route for bus
            System.out.println("add_route_for_bus [route_id] [bus_id]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - route_id -> id of route to add");
            System.out.println("\t - bus_id -> id of bus to add to");

            // add_stop_to_route
            System.out.println("add_stop_to_route [stop_id] [route_id]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - stop_id -> id of stop to add");
            System.out.println("\t - route_id -> id of route to add to");

            // upload commands
            System.out.println("upload_commands [file_path]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - file_path -> location of file containing the commands to run");

            // start simulation
            System.out.println("start_sim [turns]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - turns -> number of turns to run. This represents movement of the rides along their stops." +
                    "stops loop back to first stop on being done with last stop in sequence of stops on the route");

            // system report
            System.out.println("sys_report [report_type]");
            System.out.println("\t - Order of args matters and all must be provided.");
            System.out.println("\t - report_type -> type of report to run" +
                    "Options: system, bus, routes, stops, commands.");
        }
        else if (help_args.length == 2)
        {
            if (help_args[1].equals("add_bus"))
            {
                System.out.println("add_bus [bus_id] [bus_name] [capacity] [speed]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - bus_id -> any number or unique identifier in context of the scale of use");
                System.out.println("\t - bus_name -> any name for the bus for user reference and familiarity");
                System.out.println("\t - capacity -> number representing the number of passengers possible in the vehicle");
                System.out.println("\t - speed -> number representing the speed of the vehicle on average for traveling routes");
            }

            if (help_args[1].equals("add_stop"))
            {
                System.out.println("add_stop [stop_id] [stop_name] [x] [y]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - stop_id -> any number or unique identifier in context of the scale of use");
                System.out.println("\t - stop_name -> any name for the stop for user reference and familiarity");
                System.out.println("\t - x -> x location for geographical mapping");
                System.out.println("\t - y -> y location for geographical mapping");
            }

            if (help_args[1].equals("add_route"))
            {
                System.out.println("add_route [route_id] [route_name]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - route_id -> any number or unique identifier in context of the scale of use");
                System.out.println("\t - route_name -> any name for the route for user reference and familiarity");
            }

            if (help_args[1].equals("add_route_for_bus"))
            {
                System.out.println("add_route_for_bus [route_id] [bus_id]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - route_id -> id of route to add");
                System.out.println("\t - bus_id -> id of bus to add to");
            }

            if (help_args[1].equals("add_stop_to_route"))
            {
                System.out.println("add_stop_to_route [stop_id] [route_id]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - stop_id -> id of stop to add");
                System.out.println("\t - route_id -> id of route to add to");
            }

            if (help_args[1].equals("upload_commands"))
            {
                System.out.println("upload_commands [file_path]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - file_path -> location of file containing the commands to run");
            }

            if (help_args[1].equals("start_sim"))
            {
                System.out.println("start_sim [turns]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - turns -> number of turns to run. This represents movement of the rides along their stops." +
                        "stops loop back to first stop on being done with last stop in sequence of stops on the route");
            }

            if (help_args[1].equals("sys_report"))
            {
                System.out.println("sys_report [report_type]");
                System.out.println("\t - Order of args matters and all must be provided.");
                System.out.println("\t - report_type -> type of report to run" +
                        "Options: system, bus, routes, stops, commands.");
            }
        }
    }
}
