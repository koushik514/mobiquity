Steps followed

1. I have read the filecontents, with UTF 8 encoding inorder to parse currency symbols.
2. I assumed that each line in the file corresponds to a unique package. Based on this assumption, I split the contents of the file. The value before ':' is the max allowed weight.
3. While reading the contents, I created an object(PackageDetail) that has weight, index and cost of package. Applied given constraints
Max weight that a package can take is 100
Max weight and cost of an item is 100
4. Created a PackageCostComparator class(that implements Comparator)to sort the list of packageDetails as per cost(maximum cost being the top value in list)
Also added additional sort constraint to a package which weights less in	case there is more than	one	package	with the same price.
5. Created a class that takes input as maxAllowed weight and the list of Packagedetails to be choosen for shipping.
This class has a method which filters the best package/packages to be shipped.
6. The result is appended to a string buffer.

Additional details.
APIException is thrown, in case if file has invalid values.

Solution Details :
To achieve this solution, I implemented "Strategy" Design pattern through the use of 'Comparator'. Using Comparator, I was able to sort the data set using custom comparision operations. Using Comparator would result in better performance and reduction of boiler plate code since there would be no necessity of iterating over the data set for sorting.

Testing

Note: the files used for testing are added under src/test/resources folder. While testing the absolute path of these files is to be given in respective testcases.

Created following test cases.
1. Test that checks if the output is as expected, as per the given challenge.
2. Test that checks if the file is found, which throws fileNotFound exception.
3. Test that checks if the input format is correct, throws APIException if not correct.
4. Test that checks if the result has any indexes, when data having weight >100 is supplied.
5. Test that checks if the result has any indexes, when data having weight >100 is supplied.
6. Test that sends a package which	weighs	less in	case there is more than	one	package	with the same price

