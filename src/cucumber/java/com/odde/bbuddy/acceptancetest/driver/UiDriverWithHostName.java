package com.odde.bbuddy.acceptancetest.driver;

import com.odde.bbuddy.common.view.Params;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("cucumber-glue")
public class UiDriverWithHostName implements UiDriver {

    public static final String DELIMITER = ":";
    @Value("${server.port}")
    private String port = "8080";

    @Value("${selenium.browser:FIREFOX}")
    private String browser;
    private final String hostName = "http://localhost";
    private UiDriver originalDriver;

    @PostConstruct
    public void init() {
        originalDriver = new SeleniumWebDriver(Browser.valueOf(browser));
    }

    @Override
    public void close() {
        originalDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        originalDriver.navigateTo(urlWithHostAndPort(url));
    }

    public String urlWithHostAndPort(String url) {
        return hostName + DELIMITER + port + url;
    }

    @Override
    public UiElement findElementByName(String name) {
        return originalDriver.findElementByName(name);
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return originalDriver.findElementByTag(tag);
    }

    @Override
    public void navigateToWithParams(String url,
                                     Params params) {
        originalDriver.navigateToWithParams(urlWithHostAndPort(url), params);
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return originalDriver.findSelectByName(name);
    }

    @Override
    public UiElement findElementById(String id) {
        return originalDriver.findElementById(id);
    }

    @Override
    public void waitForTextPresent(String text) {
        originalDriver.waitForTextPresent(text);
    }
}
