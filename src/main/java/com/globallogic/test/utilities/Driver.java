package com.globallogic.test.utilities;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.globallogic.test.config.ConfigurationDetails;
import com.globallogic.test.config.configuration;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver Instance;

    public static void Initialize() {
        configuration.getInstance();
        configuration.getXMLFile();
        List<ConfigurationDetails> configurationDeatilsList1 = configuration.getConfigurationDetailsList();
        if (Instance == null) {
            if (configurationDeatilsList1.get(0).getBrowser().equalsIgnoreCase("ff")) {
                System.out.println("Initializing ff browser");
                System.setProperty("webdriver.gecko.driver", "C:\\Solaris Automation\\firefox prerequisites\\geckodriver.exe");
                Instance = new FirefoxDriver();
            } else if (configurationDeatilsList1.get(0).getBrowser().equalsIgnoreCase("ie")) {
                System.out.println("Initializing ie browser");
                System.setProperty("webdriver.ie.driver", "C:\\CProjectlib\\IEDriverServer.exe");
                Instance = new InternetExplorerDriver();
            } else if (configurationDeatilsList1.get(0).getBrowser().equalsIgnoreCase("chrome")) {
                System.out.println("Initializing chrome browser");
                System.setProperty("webdriver.chrome.driver", "C:\\Solaris Automation\\chrome prerequisites\\chromedriver.exe");
                Instance = new ChromeDriver();
            }
        }
        Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Instance.manage().window().maximize();

    }

    public static void close() {
        System.out.println("Closing browser");
        Instance.close();
        Instance = null;
    }

    public void quit() {
        System.out.println("Quiting browser");
        Instance.quit();
        Instance = null;
    }

    public static void addLogToFile() {
        Logger logger = Logger.getLogger(Driver.class);
        PropertyConfigurator.configure("src\\main\\java\\logProperties\\log4j.properties");
        logger.debug("logs started");

    }

    public static void sendMail(File Report) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(PropertiesUtils.getProperty("sender.username"), PropertiesUtils.getProperty("sender.password"));
                    }
                });
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(PropertiesUtils.getProperty("sender.username")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(PropertiesUtils.getProperty("receiver.username")));
            message.setSubject(PropertiesUtils.getProperty("mail.subject"));
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            StringWriter writer = new StringWriter();
            configuration.getInstance();
            configuration.getXMLFile();
            List<ConfigurationDetails> configurationDeatilsList1 = configuration.getConfigurationDetailsList();

            float passPercentage = ((float) ReportGenerator.passCounter / (ReportGenerator.passCounter + ReportGenerator.failCounter)) * 100;
            String s = "<table border='1' style='text-align: center'><tr bgcolor='#DCDCDC'><th>Devices</th><th>TOTAL # of\n" +
                    "Test Cases</th><th>PASS</th><th>FAIL</th><th>PASS %</th></tr><th>" + configurationDeatilsList1.get(0).getBrowser() + "</th><th>" + (ReportGenerator.passCounter + ReportGenerator.failCounter) + "</th><th>" + ReportGenerator.passCounter + "</th><th>" + ReportGenerator.failCounter + "</th><th>" + passPercentage + "</th></tr>";
            writer.write(s);
            messageBodyPart.setContent(writer.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            addAttachment(multipart, Report);
            message.setContent(multipart);
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAttachment(Multipart multipart, File file) throws MessagingException {
        DataSource source = new FileDataSource(file);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file.getName());
        multipart.addBodyPart(messageBodyPart);
    }
}









