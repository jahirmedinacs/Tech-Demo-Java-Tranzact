package common;

// io.qameta.allure.Allure and io.qameta.allure.Description are used for Allure reporting.
import io.qameta.allure.Allure;
import io.qameta.allure.Description;

// org.apache.commons.io.FileUtils is used for operations with files, such as copying files.
import org.apache.commons.io.FileUtils;

// org.apache.commons.lang3.RandomStringUtils is used for generating random alphanumeric strings.
import org.apache.commons.lang3.RandomStringUtils;

// org.openqa.selenium.* classes are used for Selenium WebDriver operations.
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// org.testng.Assert is used for assertions in TestNG tests.
import org.testng.Assert;

// java.io.* classes are used for IO operations.
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

// java.text.* classes are used for parsing and formatting dates.
import java.text.ParseException;
import java.text.SimpleDateFormat;

// java.time.* classes are used for date and time operations.
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

// java.util.* classes are used for various utility operations.
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

// This class is a utility class that provides various helper methods.
public class Utilities {

    // Regex pattern for alphanumeric characters.
    public static final String REGEX = "[^a-zA-Z0-9]+";

    // This method formats a string with the given arguments.
    public String format(String str, Object... args) {
        return String.format(str, args);
    }

    // This method generates a random alphanumeric string of the given length.
    public String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    // This method generates a random integer between the given min and max values.
    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // This method generates a random index for a list of the given size.
    public int randomIndex(int size) {
        return ThreadLocalRandom.current().nextInt(0, size - 1);
    }

    // This method captures a screenshot of the current page in the given WebDriver and saves it to the screenshots folder.
    // The screenshot is annotated with the @Description annotation for Allure reports.
    @Description("Screenshot")
    public void captureScreenshot(WebDriver driver) {
        try {
            // Format the current date and time.
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
            String date = formatter.format(currentDate.getTime()).replace("/", "_");
            date = date.replaceAll(":", "-");

            // Get the title of the current page.
            String title = driver.getTitle();
            title = title.replace("|", "-");

            // Create the filename for the screenshot.
            String nameFile = format("Screenshot_%s_%s", title, date);

            // Capture the screenshot and save it to the screenshots folder.
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./screenshots/" + nameFile + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    // This method captures a screenshot of the current page in the given WebDriver and attaches it to the Allure report.
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

    // This method parses and formats a date string with year into a LocalDateTime object.
    public LocalDateTime getParsedAndFormattedDateWithYear(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM yyyy").parse(date).toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // This method parses and formats a date string with a comma and year into a LocalDateTime object.
    public LocalDateTime getParsedAndFormattedDateWithCommaAndYear(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM, yyyy").parse(date).toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // This method parses and formats a date string into a LocalDate object.
    public LocalDate getParsedAndFormattedDate(final String date) {
        try {
            return LocalDateTime.ofInstant(new SimpleDateFormat("dd MMM").parse(date).toInstant(), ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // This method checks if a list of strings is in alphabetical order.
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

    // This method returns the Y location of a WebElement. Useful for scrolling to elements.
    public int getElementLocation(WebElement element) {
        Rectangle elementRect = element.getRect();
        return elementRect.getY();
    }
}

