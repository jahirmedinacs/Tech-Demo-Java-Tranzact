package common;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Utilities {

    public static final String REGEX = "[^a-zA-Z0-9]+";

    public String format(String str, Object... args) {
        return String.format(str, args);
    }

    public String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int randomIndex(int size) {
        return ThreadLocalRandom.current().nextInt(0, size - 1);
    }

    @Description("Screenshot")
    public void captureScreenshot(WebDriver driver) {
        try {
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
            String date = formatter.format(currentDate.getTime()).replace("/", "_");
            date = date.replaceAll(":", "-");
            String title = driver.getTitle();
            title = title.replace("|", "-");
            String nameFile = format("Screenshot_%s_%s", title, date);
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./screenshots/" + nameFile + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public void allureCaptureScreenshotRe(WebDriver driver) {
        try {
            Allure.addAttachment(
                    UUID.randomUUID().toString(),
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime getParsedAndFormattedDateWithYear(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM yyyy").parse(date).toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDateTime getParsedAndFormattedDateWithCommaAndYear(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM, yyyy").parse(date).toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getParsedAndFormattedDate(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM").parse(date).toInstant(), ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isInAlphabeticalOrder(final List<String> list) {
        String previous = "";
        List<String> tmpList = list.stream().map(e -> e.trim().substring(0, 1).toLowerCase()).collect(Collectors.toList());
        for (final String current : tmpList) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }
    
    public int getElementLocation(WebElement element) {
        Rectangle elementRect = element.getRect();
        return elementRect.getY();
    }
}
