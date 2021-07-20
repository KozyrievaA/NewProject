package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
   @Test
    public void verifyApplTest(){
        Assert.assertTrue(homePage.isCorrectPage());

    }
}
