package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class gui
{
    public static void makeTheGui(HashMap buses, HashMap routes, HashMap stops, int successCount, int failCount, ArrayList busesCurrent)
    {
        String heading = "RIDESHARE ROUTES REPORT \n\n";
        String title = "End Summary \n";
        String busCount = "Rideshare Count: " + buses.size() + "\n";
        String routeCount = "Route Count: " + routes.size() + "\n";
        String stopsCount = "Stops Count: " + stops.size() + "\n";
        String busReportHeader = "Report type: Rideshare report \n";
        String busReport = "Current shares being simulated: \n";
        String goodCommands = "Success commands run count: " + successCount + "\n";
        String badCommands = "Failed commands when ran count: " + failCount + "\n";
        StringBuilder busesOnline = new StringBuilder();
        for (Object bus : busesCurrent) {
            busesOnline.append("UBER: ").append(bus.toString().split(" ")[0]).append(" \n");
        }
        String wtf = "";
        final ImageIcon icon;
        long howDidWeDo;
        if (failCount > 0 && successCount > 0) {
            howDidWeDo = (long) failCount / (failCount + successCount);
        }
        else
        {
            howDidWeDo = 0;
        }
        if(howDidWeDo < 0.10)
        {
            icon = new ImageIcon("src/com/company/assets/bananaman.jpg");
            wtf = "Mr. Bananarang Man says rideshare routes are fun for the whole family and how can"
                    + " you not trust that face? \n\n" + "Banana Man also says this code is...ok.\n\n";
        }
        else
        {
            icon = new ImageIcon("src/com/company/assets/sad_banana_man.jpg");
            wtf = "Mr. Bananarang Man says rideshare routes are fun for the whole family and how can"
                    + " you not trust that face? \n\n" + "Sad Banana Man also says this code is...pretty terrible.\n\n";
        }
        // final pop up summarizing things in a GUI
        String message1 = heading + wtf + title + busCount + routeCount + stopsCount
                + busReportHeader + busReport + busesOnline + goodCommands + badCommands;

        JOptionPane.showMessageDialog(null, message1, "End Report", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
