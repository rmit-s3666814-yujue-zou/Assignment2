import java.util.ArrayList;

/**
 * This is a driver class named Driver. It also present the controller (business logic) layer of the application.
 *
 * @author Jyh-woei Yang
 * @version 21/05/2018
 */


 public class Driver 
 {
 	// instance variables - replace the example below with your own
	 private ArrayList<Person> personDriverList;
	 private ArrayList<Relationship> relationshipDriverList;
	 
 	/**
 	 * Constructor for objects of class Driver
 	 */
 	public Driver()
 	{
 		// initialise instance variables
 		personDriverList = new ArrayList<Person>();
 		relationshipDriverList = new ArrayList<Relationship>();
 	}
 	
    /**
     * Constructor for objects of class Driver
     * 
     * @param  personList an ArrayList of person
     * @param  relationshipList an ArrayList of relationship
     * @return 
     */
 	public Driver(ArrayList<Person> personList,ArrayList<Relationship> relationshipList)
 	{
 		// initialise instance variables
 		personDriverList = new ArrayList<Person>();
 		this.personDriverList.clear();
 		for (int i = 0; i < personList.size(); i++)
 			this.personDriverList.add(personList.get(i));
 		relationshipDriverList = new ArrayList<Relationship>();
 		relationshipDriverList.clear();
 		for (int i = 0; i < relationshipList.size(); i++)
 			this.relationshipDriverList.add(relationshipList.get(i));
 	}

 	//data getter
    /**
     * A method to let DB source create a result list and get ready to pass it onto view
     * 
     * @param  
     * @return a list of person
     */
 	public ArrayList<Person> getPersonDriverList()
 	{
 		return personDriverList;
 	}
 	
    /**
     * A method to let DB source create a result list and get ready to pass it onto view
     * 
     * @param  
     * @return a list of person
     */
 	public ArrayList<Relationship> getRelationshipDriverList()
 	{
 		return relationshipDriverList;
 	}
 	
 	//data setter
    /**
     * A method to set personList for driver use
     * 
     * @param  personList an ArrayList of person
     * @return 
     */
 	public void setPersonDriverList(ArrayList<Person> personList)
 	{
 		personDriverList.clear();
 		for (int i = 0; i < personList.size(); i++)
 			personDriverList.add(personList.get(i));
 	}
 	
    /**
     * A method to set personList for driver use
     * 
     * @param  relationshipList an ArrayList of relationship
     * @return 
     */
 	public void setRelationshipDriverList(ArrayList<Relationship> relationshipList)
 	{
 		relationshipDriverList.clear();
 		for (int i = 0; i < relationshipList.size(); i++)
 			relationshipDriverList.add(relationshipList.get(i));
 	}
 	
 	 /**
     * A method to search Relationship
     * 
     * @param  person1Name a String of person1's name
     * @param  person2Name a String of person2's name
     * @return String a relationship between person1 and person2 
     */
 	public String searchRelationship(String person1Name,String person2Name)
 	{
 		int count = 0;
 		for(int i = 0; i < relationshipDriverList.size(); i++)	
        {
 			count++;
 			//print out count for debugging
 			//System.out.println(count);
 			if (relationshipDriverList.get(i).getPerson2().getName().equals(person2Name))
 			{
	        	if (relationshipDriverList.get(i).getPerson1().getName().equals(person1Name))
	        	{
	        		System.out.println("(1)");
	            	System.out.println("Name:" + relationshipDriverList.get(i).getPerson1().getName());
	            	System.out.println(relationshipDriverList.get(i).getPerson2().getName());
	            	System.out.println(relationshipDriverList.get(i).getRelationship());
	            	return relationshipDriverList.get(i).getRelationship();
	        	}
 			}
        }
 		
 		for(int i = 0; i < relationshipDriverList.size(); i++)
 		{
 			if (relationshipDriverList.get(i).getPerson2().getName().equals(person1Name))
 			{
	        	if (relationshipDriverList.get(i).getPerson1().getName().equals(person2Name))
	        	{
	        		System.out.println("(2)");
	            	System.out.println("Name:" + relationshipDriverList.get(i).getPerson1().getName());
	            	System.out.println(relationshipDriverList.get(i).getPerson2().getName());
	            	System.out.println(relationshipDriverList.get(i).getRelationship());
	            	return relationshipDriverList.get(i).getRelationship();
	        	}
 			}
 		}
 		
 		return "";
 	}
 	
	 /**
     * A method to add Relationship
     * 
     * @param  person1Name a String of person1's name
     * @param  person2Name a String of person2's name
     * @param  relationship a String of relationship
     * @return An Array List of updated relationships 
     */
 	public ArrayList<Relationship> addRelationship(String Person1, String Person2, String relationship)
 	{
 		Person person1 = new Person();
 		person1.setName(Person1);
 		Person person2 = new Person();
 		person2.setName(Person2);
 		//default relationship = "friends";
 		Relationship insertRelationship = new Relationship(person1, person2, relationship);
 		relationshipDriverList.add(insertRelationship);
 		return getRelationshipDriverList();
 	}
 	
	 /**
     * A method to get Father's name
     * 
     * @param  searchPerson a person object
     * @return String this person's father name
     */
 	public String getFatherName(Person searchPerson)
 	{
 		String fatherName = "";
		for(int i = 0; i < relationshipDriverList.size(); i++)
		{	
			if(relationshipDriverList.get(i).getPerson1().getName().equals(searchPerson.getName()))
 			{
 				if(relationshipDriverList.get(i).getRelationship().trim().equals("father"))
 				{
 					//Generate myChildString, if father
 					System.out.println("= My Father: "+relationshipDriverList.get(i).getPerson2().getName());
	 				if (fatherName.equals(""))
	 				{
	 					fatherName = relationshipDriverList.get(i).getPerson2().getName();
	 				}
 				}
 			}
		}	
		return fatherName;
	}
 	
	 /**
     * A method to get Mother's name
     * 
     * @param  searchPerson a person object
     * @return String this person's mother name
     */
 	public String getMotherName(Person searchPerson)
 	{
 		String motherName = "";
		for(int i = 0; i < relationshipDriverList.size(); i++)
		{	
			if(relationshipDriverList.get(i).getPerson1().getName().equals(searchPerson.getName()))
 			{
 				if(relationshipDriverList.get(i).getRelationship().trim().equals("mother"))
 				{
 					//Generate myChildString, if father
 					System.out.println("= My Mother: "+relationshipDriverList.get(i).getPerson2().getName());
	 				if (motherName.equals(""))
	 				{
	 					motherName = relationshipDriverList.get(i).getPerson2().getName();
	 				}
 				}
 			}
		}	
		return motherName;
	}
 	
	 /**
     * A method to get Children's names
     * 
     * @param  searchPerson a person object
     * @return String this person's children names
     */
 	public String getChildrenList(Person searchPerson)
 	{
 		String myChildrenString = "";
 		//print out to know the performance of generate myChildrenString
 		System.out.println("== Print out to know the performance of generate myChildrenString");
 		for(int i = 0; i < relationshipDriverList.size(); i++)
 		{
 			//print out to know the performance of generate myChildrenString
 			System.out.println("==");
 			System.out.println("relationship Object("+ i +")");
 			System.out.println(relationshipDriverList.get(i).getPerson1().getName());
 			System.out.println(relationshipDriverList.get(i).getPerson2().getName());
 			System.out.println(relationshipDriverList.get(i).getRelationship());
 			if(relationshipDriverList.get(i).getPerson2().getName().equals(searchPerson.getName()))
 			{
 				if(relationshipDriverList.get(i).getRelationship().trim().equals("father"))
 				{
 					//Generate myChildString, if father
 					System.out.println("= My Children: "+relationshipDriverList.get(i).getPerson1().getName());
	 				if (myChildrenString.equals(""))
	 				{
	 					myChildrenString = relationshipDriverList.get(i).getPerson1().getName();
	 				}
	 				else if (!myChildrenString.equals(""))
	 				{
	 					myChildrenString = myChildrenString + "," + relationshipDriverList.get(i).getPerson1().getName();
	 				}
	 			}
	 			else if(relationshipDriverList.get(i).getRelationship().trim().equals("mother"))
	 			{
	 				//Generate myChildString, if mother
	 				System.out.println("= My Children: "+relationshipDriverList.get(i).getPerson1().getName());
	 				if (myChildrenString.equals(""))
	 				{
	 					myChildrenString = relationshipDriverList.get(i).getPerson1().getName();
	 				}
	 				else if (!myChildrenString.equals(""))
	 				{
	 					myChildrenString = myChildrenString + "," + relationshipDriverList.get(i).getPerson1().getName();
	 				}
	 			}
 				
 			}
 		}
 		return myChildrenString;
 	}
 	
 } 