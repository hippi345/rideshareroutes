package com.company;

import java.util.ArrayList;
import java.util.HashMap;

class createMethods
{
    static void addStop(String id, String name, String x, String y, HashMap stops)
    {
        stops.put(id, name + " " + x + " " + y);
    }

    static void addStopToRoute(String stop, String route, HashMap routes)
    {
        if (routes.get(route).toString().split(" ")[1].equals("blank_route"))
        {
            String[] routeArgs = routes.get(route).toString().split(" ");
            routes.put(route, routeArgs[0] + " " + stop);
        }
        else {
            String routeArgs = routes.get(route) + (" " + stop);
            routes.put(route, routeArgs);
        }
    }

    static void createRoute(String routeId, String routeName, HashMap routes, ArrayList routeIds)
    {
        if (routes.get(routeId) == null) {
            routes.put(routeId, routeName + " " + "blank_route");
            routeIds.add(routeId);
        }
    }
}
