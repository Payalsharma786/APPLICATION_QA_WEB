package com.globallogic.test.config;

import com.globallogic.test.utilities.Driver;
import org.apache.log4j.Logger;

public class ConfigurationDetails {
    private String browser;
    Logger logger = Logger.getLogger(ConfigurationDetails.class);

    public String getBrowser() {
        logger.debug("Browser from getBrowser()::"+browser );
        return browser;
    }

    public void setBrowser(String browser) {

        this.browser = browser;
        logger.debug("Browser from setBrowser()::"+this.browser );
    }

    @Override
    public String toString() {
        logger.debug("Browser::"+this.browser );
        return " browser=" + this.browser;
    }

}
