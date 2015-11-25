

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	/*File folder = new File("/home/yumecorp/ThirdParty_Jar");
    	File[] listOfFiles = folder.listFiles();
    	
    	    for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile()) {
    	    	  System.out.println("<PostResources className=\"org.apache.catalina.webresources.JarResourceSet\" base=\"/home/yumecorp/ThirdParty_Jar/"+listOfFiles[i].getName()+"\" internalPath=\"/\" webAppMount=\"/WEB-INF/classes\" />");
    	        //System.out.println("File " + listOfFiles[i].getName());
    	      } else if (listOfFiles[i].isDirectory()) {
    	        System.out.println("Directory " + listOfFiles[i].getName());
    	      }
    	    }*/
    	
    	File folder = new File("/home/yumecorp/ThirdParty_Jar");
    	File[] listOfFiles = folder.listFiles();
    	
    	    for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile()) {
    	    	  FileInputStream fis = new FileInputStream(listOfFiles[i]);
    	      	String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
    	      	System.out.println("tomcatyfp:"+listOfFiles[i].getName()+":"+md5+":/home/yumecorp/third_party_lib/yfp/");
    	      	fis.close();
    	      	
    	        //System.out.println("File " + listOfFiles[i].getName());
    	      } else if (listOfFiles[i].isDirectory()) {
    	        System.out.println("Directory " + listOfFiles[i].getName());
    	      }
    	    }
    	
        
    }
    
}
