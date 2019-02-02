package com.mobiquityinc.packer;

import java.util.List;

public class Packages {

    private static String byCommma = ",";
    private double acceptedPackageWeight;
    private List<PackageDetail> packageDetails;

    public Packages(double acceptedPackageWeight,List<PackageDetail> packageDetails){
        this.acceptedPackageWeight = acceptedPackageWeight;
        this.packageDetails = packageDetails;
    }

    public double getAcceptedPackageWeight() {
        return acceptedPackageWeight;
    }

    public void setAcceptedPackageWeight(double acceptedPackageWeight) {
        this.acceptedPackageWeight = acceptedPackageWeight;
    }

    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }

    public String pickBestPackage() {
        StringBuffer result = new StringBuffer();
        packageDetails.stream().forEach(packageDetail -> {if (packageDetail.getWeight() < acceptedPackageWeight) {
            acceptedPackageWeight = acceptedPackageWeight - packageDetail.getWeight();
            result.append(packageDetail.getPackageSequence() + byCommma);
        }});

        formatTheResult(result);
        return result.toString();
    }

    /* Method to remove additional comma at the end and to append - in case if no package could be shipped among the list of items(as per given problem)**/
    private void formatTheResult(StringBuffer result) {
        if (result.length() >= 1) {
            result.deleteCharAt(result.length() - 1);
        } else {
            result.append('-');
        }
    }
}

