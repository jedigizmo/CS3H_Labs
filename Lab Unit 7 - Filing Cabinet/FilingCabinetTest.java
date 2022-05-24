/** 
 *  FilingCabinetTest tests the FilingCabinet class, adding students to
 *  different drawers and multiple students to the same drawer. Tests
 *  the contains and removes methods, also.
 *	 @author Tracy Ishman
 *  Date:   2016-02-01
 *	 Period: HCS3, all periods
 */

public class FilingCabinetTest 
{
	public static void main(String[] args) 
	{
		FilingCabinet hcs3 = new FilingCabinet();
		System.out.println(hcs3);
		System.out.println();
		
		Student apple = new Student("Apple", "Braeburn", 199110, 3.891);
		Student oscar = new Student("Green", "Oscar", 135, 3.75);
		Student ernie = new Student("Red", "Ernie", 123, 2.5);
		Student elmo = new Student("Redandblue", "Elmo", 246, 2.8);
		Student dog = new Student("Yappy", "Dog", 531, 2.75);
		Student cat = new Student("Yeck", "Cat", 333, 1.75);
		Student bert = new Student("Yellow", "Bert", 456, 4.0);
		Student bird = new Student("Yellow", "Big Bird", 654, 3.25);
		Student duck = new Student("Yellow", "Duck", 29384, 3.95);
		Student mac = new Student("Yokel", "Mac", 315386, 2.98);
		Student cupcake = new Student("Yummy", "Cupcake", 315386, 2.98);
		Student zebra = new Student("Zebra", "Stripey", 121212, 3.333);

		hcs3.add(ernie);    // R - add to empty
		hcs3.add(bert);     // Y - add to empty
		hcs3.add(oscar);    // G - add to empty
		hcs3.add(apple);    // A - add to empty
		hcs3.add(zebra);    // Z - add to empty
		hcs3.add(cat);      // add in front of only
		hcs3.add(duck);     // add to end
		hcs3.add(bird);     // add in middle
		hcs3.add(mac);      // add to end
		hcs3.add(dog);      // add in front of several
		hcs3.add(cupcake);  // add to end of several
		System.out.println(hcs3);
		System.out.println();
		
		// Show in reverse to verify previous links, too
		System.out.println(hcs3.reverseToString());
		System.out.println();
		
		Student searchErnie = new Student("Red", "Ernie", 123, 2.5);
		if (hcs3.contains(searchErnie))
			System.out.println(searchErnie.getName() + " is in the filing cabinet.");
		else
			System.out.println(searchErnie.getName() + " is NOT in the filing cabinet.");
		
		if (hcs3.contains(elmo))
			System.out.println(elmo.getName() + " is in the filing cabinet.");
		else
			System.out.println(elmo.getName() + " is NOT in the filing cabinet.");

		if (hcs3.contains(mac))
			System.out.println(mac.getName() + " is in the filing cabinet.");
		else
			System.out.println(mac.getName() + " is NOT in the filing cabinet.");
		System.out.println();
		
		// Removing non-existent node
		System.out.println("Removing " + elmo);
		hcs3.remove(elmo);
		System.out.println(hcs3);
		System.out.println();
		
		// Removing only node
		System.out.println("Removing " + searchErnie);
		hcs3.remove(searchErnie);
		System.out.println(hcs3);
		System.out.println();

		// Removing middle node
		System.out.println("Removing " + bird);
		hcs3.remove(bird);
		System.out.println(hcs3);
		System.out.println();

		// Removing last node
		System.out.println("Removing " + cupcake);
		hcs3.remove(cupcake);
		System.out.println(hcs3);
		System.out.println();

		// Removing first node
		System.out.println("Removing " + dog);
		hcs3.remove(dog);
		System.out.println(hcs3);
		System.out.println();

		// Show in reverse to verify previous links, too
		System.out.println(hcs3.reverseToString());
		System.out.println();
	}
}

/* Expected output:

Filing Cabinet:
**end**

Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer R:  Red, Ernie 123 2.5
Drawer Y:  Yappy, Dog 531 2.75
           Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Big Bird 654 3.25
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
           Yummy, Cupcake 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Reverse Filing Cabinet:
Drawer Z:  Zebra, Stripey 121212 3.333
Drawer Y:  Yummy, Cupcake 315386 2.98
           Yokel, Mac 315386 2.98
           Yellow, Duck 29384 3.95
           Yellow, Big Bird 654 3.25
           Yellow, Bert 456 4.0
           Yeck, Cat 333 1.75
           Yappy, Dog 531 2.75
Drawer R:  Red, Ernie 123 2.5
Drawer G:  Green, Oscar 135 3.75
Drawer A:  Apple, Braeburn 199110 3.891
**end**

Red, Ernie is in the filing cabinet.
Redandblue, Elmo is NOT in the filing cabinet.
Yokel, Mac is in the filing cabinet.

Removing Redandblue, Elmo 246 2.8
Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer R:  Red, Ernie 123 2.5
Drawer Y:  Yappy, Dog 531 2.75
           Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Big Bird 654 3.25
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
           Yummy, Cupcake 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Removing Red, Ernie 123 2.5
Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer Y:  Yappy, Dog 531 2.75
           Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Big Bird 654 3.25
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
           Yummy, Cupcake 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Removing Yellow, Big Bird 654 3.25
Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer Y:  Yappy, Dog 531 2.75
           Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
           Yummy, Cupcake 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Removing Yummy, Cupcake 315386 2.98
Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer Y:  Yappy, Dog 531 2.75
           Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Removing Yappy, Dog 531 2.75
Filing Cabinet:
Drawer A:  Apple, Braeburn 199110 3.891
Drawer G:  Green, Oscar 135 3.75
Drawer Y:  Yeck, Cat 333 1.75
           Yellow, Bert 456 4.0
           Yellow, Duck 29384 3.95
           Yokel, Mac 315386 2.98
Drawer Z:  Zebra, Stripey 121212 3.333
**end**

Reverse Filing Cabinet:
Drawer Z:  Zebra, Stripey 121212 3.333
Drawer Y:  Yokel, Mac 315386 2.98
           Yellow, Duck 29384 3.95
           Yellow, Bert 456 4.0
           Yeck, Cat 333 1.75
Drawer G:  Green, Oscar 135 3.75
Drawer A:  Apple, Braeburn 199110 3.891
**end**

Press any key to continue...
*/