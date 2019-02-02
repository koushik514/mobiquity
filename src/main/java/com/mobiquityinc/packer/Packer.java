package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Packer {
    private static String byColon = ":";
    private static String byCommma = ",";
    private static String byOpeningBrace = "(";
    private static String byClosingBrace = ")";
    private static double totalWeightConstraint = 100;
    private static int totalPriceConstraint = 100;

    public static String pack(String filePath) throws APIException, FileNotFoundException {
        BufferedReader br = null;
        String line = "";
        StringBuffer listOfPackages = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("utf-8")));

            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(byColon);

                if (lineValues != null) {
                    double acceptedPackageWeight = Double.parseDouble(lineValues[0].trim()); // 1st value before split
                    if (acceptedPackageWeight <= totalWeightConstraint) { //constraint to skip if weight is more than 100
                        String[] stringItems = lineValues[1].trim().split(" ");
                        List<PackageDetail> packagesToBeShipped = getPackageDetails(stringItems);

                        Collections.sort(packagesToBeShipped,new PackageCostComparator());
                        Packages packages = new Packages(acceptedPackageWeight, packagesToBeShipped); // insert the acceptable weight and the sorted list in this class.
                        listOfPackages.append(packages.pickBestPackage()+"\r\n");

                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new APIException(e.getMessage());
        }catch (FileNotFoundException e) {
           throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listOfPackages.toString();
    }

    /* Method that reads each line in the file into PackageDetails class **/
    private static List<PackageDetail> getPackageDetails(String[] stringItems) {
        List<PackageDetail> packagesToBeShipped = new ArrayList<PackageDetail>();
        Arrays.stream(stringItems).forEach(stringItem -> {
            String[] packageDetails = stringItem.split(byCommma);
            int id = Integer.parseInt(packageDetails[0].substring(1));
            double weight = Double.parseDouble(packageDetails[1]);
            int price = Integer.parseInt(packageDetails[2].substring(1, packageDetails[2].length() - 1));// to filter currency value £ or $ etc
            if (price <= totalPriceConstraint) { // constraint to skip if price is more than 100
                PackageDetail packageDetail = new PackageDetail(id, weight, price);
                packagesToBeShipped.add(packageDetail);
            }
        });
        return packagesToBeShipped;
    }
}

