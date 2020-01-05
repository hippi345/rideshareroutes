package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class uploadCommandsClass
{
    // upload commands via a source text file and issue the commands in a batch
    static void uploadCommands(String fileLocation)
    {
        Main.continueOnBatch = true;
        System.out.println("Uploading file at location: " + fileLocation);
        File commandFile = new File(fileLocation);
        try (BufferedReader br = new BufferedReader(new FileReader(commandFile)))
        {
            Main.successCount++;
            String line;
            while ((line = br.readLine()) != null)
            {
                readArgs.readingArgs(line.split(" "));
                Thread.sleep(100);
            }
        }
        catch (IOException e)
        {
            Main.failCount++;
            System.out.println("File note found/valid. Path may not be valid.");
        }
        catch (InterruptedException e)
        {
            System.out.println("Thread done.");
        }
        Main.continueOnBatch = false;
    }
}
