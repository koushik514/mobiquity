package com.mobiquityinc.packer;

import org.junit.Test;
import com.mobiquityinc.exception.APIException;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertTrue;

public class PackerTest {
    final Packer packer = new Packer();

    // Test that checks if the output is as expected, as per the problemstatement
    @Test
    public void testPackage() throws APIException, FileNotFoundException {
        String filePath= "/home/sybase/linux_share/newTas/packageDetails.txt";
        String result =  packer.pack(filePath);
        assertTrue(result.contains("-"));
        assertTrue(result.contains("8,9"));
        assertTrue(result.contains("4")); //testing to send	a package which	weights	less in	case there is more than	one	package	with the same price
    }

    // Test that checks if the file is not found.
    @Test(expected = FileNotFoundException.class)
    public void testPackageWithFileNotFound_exceptionMessageTest() throws APIException, FileNotFoundException {
            String filePath = "/home/sybase/linux_share/newTas/package.txt";
            String result = packer.pack(filePath);

    }
    // Test that checks if the input format is correct.
    @Test(expected = APIException.class)
    public void testPackageWithFileNotFound_InvalidFormat() throws APIException, FileNotFoundException {
            String filePath = "/home/sybase/linux_share/newTas/packageDetails_InvalidFormat.txt";
            String result = packer.pack(filePath);
    }

    //Test that checks if the result has any indexes, when data having weight >100 is supplied
    @Test
    public void testPackage_ExceedingMaximumWeight() throws APIException, FileNotFoundException {
        String filePath = "/home/sybase/linux_share/newTas/packageDetails_ExceedingMaximumWeight.txt";
        String result = packer.pack(filePath);
        assertTrue(result.length() == 0);
    }

    //Test that checks if the result has any indexes, when data having weight >100 is supplied
    @Test
    public void testPackage_ExceedingMaximumCost() throws APIException, FileNotFoundException {
        String filePath = "/home/sybase/linux_share/newTas/packageDetails_ExceedingMaximumCost.txt";
        String result =  packer.pack(filePath);
        assertTrue(!result.contains("4"));
        assertTrue(result.contains("6,5"));
    }

    //testing to send a package which	weights	less in	case there is more than	one	package	with the same price
    @Test
    public void testPackage_withSameCost() throws APIException, FileNotFoundException {
        String filePath= "/home/sybase/linux_share/newTas/packageDetails_SameCost.txt";
        String result =  packer.pack(filePath);
        assertTrue(result.contains("8,9"));
        assertTrue(!result.contains("6,9")); //testing to send	a package which	weights	less in	case there is more than	one	package	with the same price
    }
}
