

public class Player implements Comparable<Player>{
	
	private String firstName, lastName, fullName, email;
	private int age, score, number, attempts, hit, miss;
	int shotHit;
	
	public Player(String firstName, String lastName, String email, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.fullName = firstName + " " + lastName;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validateAge(Player player) {
		/*the variable age gets the value of the age of the player
		 * through the getAge() method of the class Player.
		 * if the age is greater than 12 and less than 100, it returns true,
		 * that means it is a valid age. otherwise returns false.*/
		int age = player.getAge();
		if(age > 12 && age < 100) {
			return true;
		}
		return false;
	}
	/***
	 * @author Marta
	 * @param player: a {@link Player} object
	 * @return true if it is a valid name
	 */
	public static boolean validateName(Player player) {
		/*the variable name gets the full name of the player
		 * through the methods getFirstName() and getLastName() of the class Player.
		 * the full name will be the concatenation of the firstName and the lastName.
		 * */
		String name = player.getFirstName() + player.getLastName();

		/*this loop checks if the name given contains only valid caracteres.
		 * if in any position of the array name contains number, or special caracteres,
		 * such as @, %, etc. it will return false, that means it is not a valid name.*/
		for (int i = 0; i < name.length(); i++) {
	          if (!Character.isAlphabetic((name.charAt(i)))) {
	        	  System.out.println("number in the name!..");
	               return false;
	          }
	          /*this loop checks weather the first name or the last name given is empty.
	           * if so, it will return false, that means it is not a valid input.*/
	          if(player.getFirstName().isEmpty() || player.getLastName().isEmpty()) {
	        	  System.out.println("empty name");
	        	  return false;
	          }
	   
	     }
		/*that validation makes sure that the very first position of the string name 
		 * is a valid caracter and the full name is only made of alphabetic caracteres only.
		 * as java consider zero and not one as the first position, we have to consider it, adding a minus one
		 * at the end of the conditional instruction: name.length() - 1. 
		 * if we do not do like that, we are gonna get an exception during the game.*/
		if (Character.isAlphabetic((name.charAt(0))) && Character.isAlphabetic((name.charAt(name.length() - 1)))) {
			//System.out.println("name OK");
		     return true;
		}
		return false;
	}
	/***
	 * @author Marta
	 * @param player: a {@link Player} object
	 * @return true if the email given is value according to the game specification
	 */
	/*
	 * this email validation accepts only emails given in a predefinited pattern.
	 * the pattern is: anyName@anyWhere.ie . in this case, has to be .ie.
	 * */
	public static boolean validateEmail(Player player) {
	    //the email pattern
	    boolean email = player.getEmail().matches("\\w+@\\w+\\.ie");
	    // \w -> A word character: range from a to z, A to Z and from 0 to 9 .[a-zA-Z_0-9]
	    // \. -> a dot
	    if (email){
	    	//System.out.println("email OK");
	      return true;
	    }
	    else{
	    	System.out.println("email invalid!");
	      return false;
	    }  
	 }
	
	@Override
	public String toString() {
		return "\nName: " + this.fullName + "\n" + "e-mail: " + this.email + "\n" + "Age: " + this.age;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore() {
		this.score = hit-(miss*2);
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit += hit;
	}
	
	public int getMiss() {
		return miss;
	}
	
	public void setMiss(int miss) {
		this.miss += miss;
	}
	
	public int getAttempts() {
		return attempts;
	}
	
	public void setAttempts() {
		this.attempts++;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	/*as the class Player implements the interface comparable, we have to override the compareTo method.
	 * if the score of this player is greater than the score of the other player, returns -1, that means
	 * this player has a greater score, otherwise returns 1 or 0: 1 means that the score of the other player
	 * compare to this player is greater, and zero means that the scores compared are equals.*/
	@Override
	public int compareTo(Player other) {
		if(this.score > other.getScore()) {
			return -1;
		}
		if(this.score < other.getScore()) {
			return 1;
		}
		return 0;
	}

}
