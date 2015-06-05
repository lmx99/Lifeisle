package com.lifeisle.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author Jekton Luo
 * @version 0.01 6/4/2015.
 */
public class PrintRunnable implements Runnable {

    private String pathToBePrinted;

    public PrintRunnable(String pathToBePrinted) {
        this.pathToBePrinted = pathToBePrinted;
    }

    @Override
    public void run() {
        File path = new File(pathToBePrinted);
        try {
            while (true) {
                File[] files = path.listFiles();
                System.out.println(files.length + " files");
                if (files.length != 0) {
                    for (File file : files) {
                        // TODO print and post information the file
                        System.out.println(file.getName());
                        while (!file.delete()) {
                            // TODO notify the user that the deleting is failed
                            System.out.println("Fail to delete the file " + file.getName());
                            // Wait for 500 milliseconds and try again
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                    }
                } else {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(5);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
