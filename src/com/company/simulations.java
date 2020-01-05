package com.company;

import java.util.Arrays;

public class simulations
{
    // start simulation
    static void startSim(String turns)
    {
        try {
            for (int i = 0; i < Integer.parseInt(turns); i++)
            {
                moveTheBuses();
            }
            Main.successCount++;
        }
        catch (Exception e)
        {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private static void moveTheBuses()
    {
        int timeElapsed = 0;
        for (String bus: Main.busesCurrent)
        {
            System.out.println("Bus is: " + Main.buses.get(bus.split(" ")[0]).split(" ")[1]);
            // buses.put(busId, busName + " " + blankRoute + " " + capacity + " " + speed)
            String busName = Main.buses.get(bus.split(" ")[0]).split(" ")[0];
            String busRoute = Main.buses.get(bus.split(" ")[0]).split(" ")[1];
            String busCap = Main.buses.get(bus.split(" ")[0]).split(" ")[2];
            String busSpeed = Main.buses.get(bus.split(" ")[0]).split(" ")[3];

            String[] routeStops = Main.routes.get(busRoute).split(" ");
            // name stop1 stop2 ... stopn
            if (Main.busStops.get(bus) == null)
            {
                // first stop
                Main.busStops.put(bus.split(" ")[0], routeStops[1]);
                String stop = Main.stops.get(routeStops[1]);
                // addStop(String id, String name, String x, String y)
                int x = Integer.parseInt(stop.split(" ")[1]);
                int y = Integer.parseInt(stop.split(" ")[2]);
                int distance = (int) Math.sqrt((x*x) + (y*y));
                timeElapsed = timeElapsed + (distance / Integer.parseInt(busSpeed));
                System.out.println("Total time elapsed since bus: " + busName + " -> " + timeElapsed);
                int passengersFirstStop = (int) (Math.round(Math.random()*Integer.parseInt(busCap)));
                System.out.println("Passengers boarded: " + passengersFirstStop);
                Main.busPassengers.put(bus, String.valueOf(passengersFirstStop));
                Main.successCount++;
            }
            else
            {
                // not the first stop
                // getting the current stop
                String stop = Main.busStops.get(bus);
                // setting the variable to store the next stop
                String nextStop = "";
                // go to the next stop
                String busRouteCurrent = Main.routes.get(bus);
                // route name ... stops
                String[] routeArgs = busRouteCurrent.split(" ");
                // size is name plus number of stops
                int stopSize = routeArgs.length;

                // comparing stops
                for (int i = 1; i < stopSize; i++)
                {
                    if (routeArgs[i].equals(stop));
                    {
                        if (i == stopSize - 1)
                        {
                            nextStop = routeArgs[1];
                        }
                        else
                        {
                            nextStop = routeArgs[i+1];
                        }
                    }
                }

                // addStop(String id, String name, String x, String y)
                int x = Integer.parseInt(Main.stops.get(nextStop).split(" ")[1]);
                int y = Integer.parseInt(Main.stops.get(nextStop).split(" ")[2]);
                int oldX = Integer.parseInt(Main.stops.get(stop).split(" ")[1]);
                int oldY = Integer.parseInt(Main.stops.get(stop).split(" ")[2]);
                int distance = (int) Math.sqrt((x-oldX)*(x-oldX) + (y-oldY)*(y-oldY));
                System.out.println("Time spent on this trip: " + (distance / Integer.parseInt(busSpeed)));
                timeElapsed = timeElapsed + (distance / Integer.parseInt(busSpeed));
                System.out.println("Total time elapsed since bus: " + busName + " -> " + timeElapsed);
                // passengers to leave
                int passengersLeave = (int) Math.round(Math.random() * Integer.parseInt(Main.busPassengers.get(bus)));
                // passengers to board
                int passengersToBoard = (int) Math.round(Math.random() * Integer.parseInt(busCap));
                // make sure we don't overpack
                if (passengersToBoard >= Integer.parseInt(busCap) - passengersLeave)
                {
                    Main.busPassengers.put(bus,busCap);
                }
                // passengers boarded is cap minus the ones that left
                System.out.println("Passengers boarded: " + (Integer.parseInt(busCap) - passengersLeave));
                Main.busPassengers.put(bus, String.valueOf(busCap));
                Main.successCount++;
            }
        }
    }
}
