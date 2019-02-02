package com.mobiquityinc.packer;

import java.util.Comparator;

//To sort contents of list based on the cost of the item
public class PackageCostComparator implements Comparator<PackageDetail> {

    @Override
    public int compare(PackageDetail o1, PackageDetail o2) {

        int result;

        if (o2.getCost() < o1.getCost()) {
            result = -1;
        } else if (o2.getCost() > o1.getCost()) {
            result = 1;
        } else {
            result = 0;
        }
        if (result == 0) // 	to send	a package which	weights	less in	case there is more than	one	package	with the same price
        {
            if (o1.getWeight() < o2.getWeight()) {
                result = -1;
            }
        }

        return result;

    }

}
