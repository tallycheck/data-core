package com.taoswork.tallycheck.servo.tallyadmin;

import com.taoswork.tallycheck.servo.DemoServo;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gaoyuan on 7/10/16.
 */
public class DemoDebugServoTest {

    @Test
    public void runAsMain() throws IOException {
        String configXml = "META-INF/spring/dubbo-tallyadmin-servo.xml";

        DemoServo.runServo(configXml);
    }
}
