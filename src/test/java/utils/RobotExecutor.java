package utils;

import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

@Log4j2
public class RobotExecutor {

    public static void downloadFile(String fileName) throws AWTException, InterruptedException {
        log.info("Robot starting...");
        Robot robot = new Robot();
        ClassLoader classLoader;
        File file;
        StringSelection stringSelection;

        classLoader = RobotExecutor.class.getClassLoader();

        file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        String absolutePath = file.getAbsolutePath();

        log.info("Absolute path to file is " + absolutePath);

        stringSelection = new StringSelection(absolutePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        log.info(Toolkit.getDefaultToolkit().getSystemClipboard().toString());

        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);

        log.info("Robot finished!!!");
    }
}

