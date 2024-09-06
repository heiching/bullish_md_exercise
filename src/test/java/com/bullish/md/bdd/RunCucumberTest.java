package com.bullish.md.bdd;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.bullish.md")
//@SelectClasspathResource("com.bullish.md.bdd")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.bullish.md.bdd")
//@ConfigurationParameter(key = FEATURES_PROPERTY_NAME,value = "src/test/resources/com/bullish/md/bdd")

public class RunCucumberTest {

}
