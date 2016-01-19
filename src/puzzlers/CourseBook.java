package puzzlers;

public class CourseBook extends Book
{
	//static method cannot hide the instance method from Book
	// if Book contains static void print then, Course Book cannot override static method - basic principal 
	/*static void print()
	{
		System.out.println("From Course Book");
	}*/
	
	

}
