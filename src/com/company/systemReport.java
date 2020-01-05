package com.company;

public class systemReport
{
    // system report
    static void systemReport(String type)
    {
        // system report
        if (type.equals("system")) {
            System.out.println("System report: ");
            System.out.println("Bus Count: " + Main.buses.size());
            System.out.println("Route Count: " + Main.routes.size());
            System.out.println("Stops Count: " + Main.stops.size());
            System.out.println("Current buses being simulated: ");
            for (String bus : Main.busesCurrent) {
                System.out.println(bus.split(" ")[0]);
            }
            System.out.println("End of report...\n");
            Main.successCount++;
        }
        else if (type.equals("bus"))
        {
            System.out.println("Bus report: ");
            for (String bus : Main.busesCurrent)
            {
                System.out.println(bus);
                System.out.println("Properties: " + Main.buses.get(bus));
            }
            System.out.println("End of bus report \n");
            Main.successCount++;
        }
        else if (type.equals("stops"))
        {
            System.out.println("Stops report: ");
            for (String bus : Main.busesCurrent)
            {
                System.out.println("Bus: " + bus);
                System.out.println("Stops: " + Main.busStops.get(bus));
            }
            System.out.println("End of bus stops report \n");
            Main.successCount++;
        }
        else if (type.equals("routes"))
        {
            System.out.println("Route report: ");
            for (String route : Main.routeIds)
            {
                System.out.println("Route: " + route);
                System.out.println("Name and stop ids: " + Main.routes.get(route));
            }
            System.out.println("End of routes report \n");
            Main.successCount++;
        }
        else if (type.equals("commands"))
        {
            Main.successCount++;
            System.out.println("Successful commands ran: " + Main.successCount);
            System.out.println("Failed commands count: " + Main.failCount);
        }
        else
        {
            System.out.println("Report type: " + type + " -> is invalid.");
            Main.failCount++;
        }
    }
}
