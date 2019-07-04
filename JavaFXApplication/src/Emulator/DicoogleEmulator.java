/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Andreia Patrocínio
 */
public class DicoogleEmulator {
    
    private static WebDriver driver;
    private static  Robot robot;
    
    public static void main(String[] args) throws Exception {
        robot = new Robot();
        openChrome();
        //preciso do sleep para a pagina carregar
        Thread.sleep(10000);
        dicoogleImageAdjustments();
        Thread.sleep(1000);
        dicoogleIABrightnessUp();
    }
    
    public DicoogleEmulator(){
        try{
            robot = new Robot();
        }catch(AWTException e){
            System.out.println("Erro");
        }
        
    }
    
    public static void openChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andreia Patrocínio\\Documents\\PROJETO CC\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Andreia Patrocínio\\AppData\\Roaming\\Google\\Chrome\\User Data");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://demo.dicoogle.com/tmg/dwsp/?seriesuid=2.25.40455800108579103101520589961762595191&studyuid=2.25.190719096823403736819300823791323488560&fbclid=IwAR2PG03uEUiyGPI0tS75dk981_3euFlMx4OuK-WrroEetKX4gMCNTEehW-A"); 
    }
    
    public static void dicoogleMisc(){
        WebElement misc = driver.findElement(By.id("btn-Miscellaneous"));
        misc.click();
    }
    
    public static void dicoogleMiscToogleFullscreen(){
        WebElement misc = driver.findElement(By.id("btn-FullscreenTool"));
        misc.click();
    }
    
    public static void dicoogleReset(){
        WebElement reset = driver.findElement(By.cssSelector("i[class='fa fa-power-off']"));
        reset.click();
    }
    
    public static void dicoogleAnnotate(){
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-annotate']"));
        annotate.click();
    }
    
    public static void dicoogleAnnotateRuler(){
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-line']"));
        annotate.click();
    }
    
    public static void dicoogleAnnotateRectangular(){
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-square']"));
        annotate.click();
    }
    
    public static void dicoogleImageInfo(){
        WebElement imageInfo = driver.findElement(By.cssSelector("i[class='fa fa-info-circle']"));
        imageInfo.click();
        
    }
    
    public static void dicoogleImageAdjustments(){
        WebElement imageadjust = driver.findElement(By.cssSelector("i[class='fa fa-adjust']"));
        imageadjust.click();
    }
    
    public static  void dicoogleIABrightnessUp(){
        Actions action = new Actions(driver);
       
        WebElement brightness = driver.findElement(By.cssSelector("input[name='brightControl-value']"));
        WebElement green = driver.findElement(By.cssSelector("i[class='btn btn-default btn-xs']"));
        green.click();
        //brightness.click();
        

        //WebElement brightness = driver.findElement(By.cssSelector("input[class='ng-valid ng-dirty ng-touched']"));
//        driver.findElement(By.name("brightControl-value")).clear();
//       
//        driver.findElement(By.name("brightControl-value")).sendKeys("40");
//        driver.findElement(By.name("brightControl-value")).click();
        
        
        //este funciona
//        WebElement imageInfo = driver.findElement(By.cssSelector("i[class='fa fa-info-circle']"));
//        imageInfo.click();
        
//        robot.keyPress(KeyEvent.VK_KP_UP);
//        robot.delay(1000);
//        robot.keyRelease(KeyEvent.VK_KP_UP);    
    }
    
    public static void dicoogleFit(){
        WebElement fit = driver.findElement(By.cssSelector("i[class='fa fa-arrows']"));
        fit.click();
    }
    
     public static void dicoogleFlip(){
        WebElement flip = driver.findElement(By.cssSelector("i[class='icon-rotate']"));
        flip.click();
    }
     
    public static void dicoogleFlipRight(){
        WebElement flip = driver.findElement(By.cssSelector("i[class='fa fa-rotate-right']"));
        flip.click();
    }
     
    public static void dicoogleFlipLeft(){
        WebElement flip = driver.findElement(By.cssSelector("i[class='fa fa-rotate-left']"));
        flip.click();
    }
    
    public static void dicoogleHideShow(){
        WebElement hide = driver.findElement(By.cssSelector("i[class='fa fa-undo']"));
        hide.click();   
        
    }
    
}
