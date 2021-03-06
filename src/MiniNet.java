/**
 * This is a startup class named MiniNet. It also present the view layer of the application.
 *
 * @author Jyh-woei Yang, Yujue Zou
 * @version 21/05/2018
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.hsqldb.HsqlException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MiniNet extends Application 
{
    PeopleDao peopleDao = new PeopleDao();
    private ArrayList<Person> personViewList;
    RelationshipDao relationshipDao = new RelationshipDao();
    private ArrayList<Relationship> relationshipViewList;
    private Driver driver;
    private int selectPersonIndex;
	
    public MiniNet()
    {
        //Optional constructor
    	peopleDao = new PeopleDao();
    	relationshipDao = new RelationshipDao();
    	selectPersonIndex = -1;
    }
    
    @Override
    public void init()
    {
        //By default this does nothing, but it
        //can carry out code to set up your app.
        //It runs once before the start method,
        //and after the constructor.

        //Loading data from text file
        peopleDao.loadPeopleFile();

        //Loading data from database
        //peopleDao.readFromDB();
    		
    	personViewList = peopleDao.getResultList();
    	relationshipDao.loadRelationshipFile();
    	relationshipViewList = relationshipDao.getResultList();
    	driver = new Driver(personViewList,relationshipViewList);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
    	// Creating the Java button
		final Button button = new Button();
        final Button button2 = new Button();
        final Button button3 = new Button();
        final Button button4 = new Button();
        final Button button5 = new Button();
        final Button button6 = new Button();
        final Button button7 = new Button();
        final Button button8 = new Button();
        final Label msgLabel = new Label();
        
        // Setting text to button
         button.setText("        Add a person          ");
        button2.setText("       Select a person        ");
        button3.setText("      Display the profile     ");
        button4.setText(" Delete selected person ");
        button5.setText("       Find connection        ");
        button6.setText("      Define relationship     ");
        button7.setText("       Find the families      ");
        button8.setText("             Exit             ");
        msgLabel.setText("");
        
        // Registering a handler for button
        button.setOnAction((ActionEvent event) -> {
            // Printing "1) Add a person into the network" to the console
            System.out.println("1) Add a person into the network");
            Stage secondaryStage1 = new Stage();
            start1(secondaryStage1);
            //Open a new window using GUI, with text boxes , input the attributes and button add => validation
            //add a person to this network
        });
        
        // Registering a handler for button
        button2.setOnAction((ActionEvent event) -> {
            // Printing "2) Select a person" to the console
            System.out.println("2) Select a person");
            Stage secondaryStage2 = new Stage();
            
            start2(secondaryStage2);
            //Open a new window using GUI, list out text field, labels and names
            //Clink on the submit button and shows the details
        });
        
        // Registering a handler for button
        button3.setOnAction((ActionEvent event) -> {
            // Printing "3) Display the profile") to the console
            System.out.println("3) Display the profile");
            //If the selectPerson = null,
            //	show error message "Person are not select", return to the menu
            //else show specific person's profile
            String input = "";
            
            if (selectPersonIndex == (-1))
        		msgLabel.setText("Msg: Please select a person to display.");
            else if (selectPersonIndex != (-1))
            {
            	input = personViewList.get(selectPersonIndex).getName();
            
	            String targetName = "";
	            String targetPhotopath = "";
	            String targetAge = "";
	            String targetGender = "";
	            String targetStatus = "";
	            String targetAusStates = "";
	            
	            for(int i = 0; i < personViewList.size(); i++)	
	            {
	            	if (personViewList.get(i).getName().equals(input))
	            	{
		            	System.out.println(personViewList.get(i).getName());
		            	targetName = personViewList.get(i).getName();
                        System.out.println(personViewList.get(i).getPhotoPath());
		            	targetPhotopath = personViewList.get(i).getPhotoPath();
		            	System.out.println(personViewList.get(i).getAge());
		            	targetAge = personViewList.get(i).getAge()+"";
		            	System.out.println(personViewList.get(i).getGender());
		            	targetGender = personViewList.get(i).getGender()+"";
		            	System.out.println(personViewList.get(i).getStatus());
		            	targetStatus = personViewList.get(i).getStatus();
		            	System.out.println(personViewList.get(i).getAusStates());
		            	targetAusStates = personViewList.get(i).getAusStates();
	            	}
	            }
	            
	            Stage secondaryStage3 = new Stage();
	            
	            start3(secondaryStage3,targetName,targetPhotopath,targetAge,targetGender,targetStatus,targetAusStates);
	            
	            if(selectPersonIndex > (-1))
	            {
		            //reset the selectPersonIndex back to -1, if we display the item successfully
	        		selectPersonIndex = -1;
	        		msgLabel.setText("");
		            //reset back to -1
	            }
	            
            }		
        });
        
        // Registering a handler for button
        button4.setOnAction((ActionEvent event) -> {
            // Printing "4) Delete the selected person" to the console
            System.out.println("4) Delete the selected person");
            if (selectPersonIndex == (-1))
            	msgLabel.setText("Msg: Please select a person to delete.");
            if (selectPersonIndex > (-1))
            	System.out.println(personViewList.get(selectPersonIndex).getName());
            if (selectPersonIndex > (-1))
            {	
            	personViewList.remove(selectPersonIndex);
            	//reset the selectPersonIndex back to -1, if we delete the item successfully
            	selectPersonIndex = -1;
            	msgLabel.setText("");
            }
            //Delete everything about this person from the network including profile, relationships
        });
        
        // Registering a handler for button
        button5.setOnAction((ActionEvent event) -> {
            // Printing "5) Find out whether two people are directly connect in some ways" to the console
            System.out.println("5) Find out whether two people are directly connect in some ways");
            //new windows
            //textbox for inputbox1 person1 name
            //textbox for inputbox2 person2 name
            //find out if there is a data have person1's name = inputbox1
            //								  person2's name = inputbox2
            //print out "Yes, they are " + "relationship."
            //or 	    "No. they do not know each other."
            System.out.println(relationshipViewList.get(1).getPerson1().getName());
			System.out.println(relationshipViewList.get(1).getPerson2().getName());
			System.out.println(relationshipViewList.get(1).getRelationship());
			
			System.out.println("== Only related to Ben Turner ==");
			for(int i = 0; i < relationshipViewList.size(); i++)	
            {
            	if (relationshipViewList.get(i).getPerson2().getName().equals("Ben Turner"))
            	{
            		System.out.println("= Relation =");
            		System.out.println(relationshipViewList.get(i).getPerson1().getName());
	            	System.out.println(relationshipViewList.get(i).getPerson2().getName());
	            	System.out.println(relationshipViewList.get(i).getRelationship());
            	}
            }
			//RelationshipDao relationshipDao;
			//driver.setRelationshipDriverList(relationshipViewList);
			
			Stage secondaryStage5 = new Stage();
            
            start5(secondaryStage5);
			
        });
        
        // Registering a handler for button
        button6.setOnAction((ActionEvent event) -> {
            // Printing "6) Define relation between two people" to the console
            System.out.println("6) Define relation between two people");
            //new windows
            // textbox1 person1's name
            // textbox2 person2's name
            // check their relationship has been defined or not?
            // if yes, they are ""
            // if no
            // add them into the data storage
            // add two people as a friends,parents,classmate,colleagues,couple
            // parents, person2 is person1's Mparents
            // parents, person2 is person1's Fparents
    		System.out.println(relationshipViewList.get(0).getPerson1().getName());
    		System.out.println(relationshipViewList.get(0).getPerson2().getName());
    		System.out.println(relationshipViewList.get(0).getRelationship());
    			
    		Stage secondaryStage6 = new Stage();
                
            start6(secondaryStage6);
        });
        
        // Registering a handler for button
        button7.setOnAction((ActionEvent event) -> {
            // Printing "7) Find out the name(s) of the children or parents to the console
            System.out.println("7) Find out the name(s) of the children or parents");
            //System.out.println(His/her Childrens' name are: )
            //System,out.println(His/her Parents' names are: +" MparentName "+ and + " +FparentName"")
            Stage secondaryStage7 = new Stage();
            String mparentName = "";
            String fparentName = "";
            
            if (selectPersonIndex == (-1))
        		msgLabel.setText("Msg: Please select a person to display.");
            else if (selectPersonIndex != (-1))
            {
            	String condition = "normal";	
            	//if has parents (must be a Children or Young child, age <= 16)
            	if (personViewList.get(selectPersonIndex).getAge() <= 16)
            	{
	            	condition = "hasParents";	
	            	
		            mparentName = driver.getFatherName(personViewList.get(selectPersonIndex)); 
		            fparentName = driver.getMotherName(personViewList.get(selectPersonIndex));
		            System.out.println(mparentName+fparentName);
            	}
	            
            	if (personViewList.get(selectPersonIndex).getAge() > 16)
            		condition = "hasChildren";
            	
                //if has child (must be a Children or Young child, age <= 16)
	            if (personViewList.get(selectPersonIndex).getAge() > 16 && condition.equals("hasChildren"))
	            {
		            //if has children, for example "Susan Turner" and "Mark Turner"
		            String children1 = "Susan Turner";
		            String children2 = "Mark Turner";
	            }
	            
	            start7(secondaryStage7, mparentName, fparentName);
	            		
	            if (condition.equals("hasChildren")) // for example, "Susan Turner" and "Mark Turner"
	            {
		            String myChildrenString = driver.getChildrenList(personViewList.get(selectPersonIndex));
		            start7(secondaryStage7, myChildrenString, "");
	            }
            }
            
            if(selectPersonIndex > (-1))
            {
	            //reset the selectPersonIndex back to -1, if we display the item successfully
        		selectPersonIndex = -1;
        		msgLabel.setText("");
	            //reset back to -1
            }
        });
        
        // Registering a handler for button
        button8.setOnAction((ActionEvent event) -> {
            // Printing "8) Exit" to the console
        		// close all the windows.
            System.out.println("8) Exit");
            stop();
        });
        
        // Initializing the StackPane class
        // final StackPane root = new StackPane();

        // Initializing the VBox class
        final VBox root = new VBox();
        // Adding all the nodes to the Vbox
        
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
          
        root.getChildren().addAll(button, button2, button3, button4, button5, button6, button7, button8, msgLabel);
        
        // Creating a scene object
        final Scene scene = new Scene(root, 640, 480);
		
        //Get Style from CSS
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
		
        // Adding the title to the window (primaryStage)
        primaryStage.setTitle("MiniNet");
        primaryStage.setScene(scene);
        // Show the window(primaryStage)
        primaryStage.show();

    }

	/**
     * A method to open a windows for function 1) Add a person into the network
     * 
     * @param secondaryStage1
     * @return 
     */
    public void start1(Stage secondaryStage1) 
    {   	
	    //Creating a GridPane container
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(5);
	    grid.setHgap(5);
	    	
	    //Defining the FirstName text field
	    final TextField name = new TextField();
	    name.setPromptText("Enter your first name.");
	    name.setPrefColumnCount(10);
	    name.getText();
	    GridPane.setConstraints(name, 0, 0);
	    grid.getChildren().add(name);
	    	
	    //Defining the Lastname text field
	    final TextField lastName = new TextField();
	    lastName.setPromptText("Enter your last name.");
	    GridPane.setConstraints(lastName, 0, 1);
	    grid.getChildren().add(lastName);
	    	
	    //Defining the Age text field
	    final TextField age = new TextField();
	    age.setPromptText("Enter your age.");
	    GridPane.setConstraints(age, 0, 2);
	    grid.getChildren().add(age);
	    
	    //Defining the Gender text field
	    final TextField gender = new TextField();
	    gender.setPromptText("Enter your gender (M or F).");
	    GridPane.setConstraints(gender, 0, 3);
	    grid.getChildren().add(gender);
	    	
	    //Defining the Status text field
	    final TextField status = new TextField();
	    status.setPromptText("Enter your status.");
	    GridPane.setConstraints(status, 0, 4);
	    grid.getChildren().add(status);
	    	
	    //Defining the Status text field
	    final TextField ausStates = new TextField();
	    ausStates.setPromptText("Enter your state.");
	    GridPane.setConstraints(ausStates, 0, 5);
	    grid.getChildren().add(ausStates);
	    	
	    //Defining the Submit button
	    Button submit = new Button("Submit");
	    GridPane.setConstraints(submit, 1, 0);
	    grid.getChildren().add(submit);
	    	
	    //Defining the Clear button
	    Button clear = new Button("Clear");
	    GridPane.setConstraints(clear, 1, 1);
	    grid.getChildren().add(clear);
	    	
    	//Adding a Label
    	final Label label = new Label();
    	GridPane.setConstraints(label, 0, 7);
    	GridPane.setColumnSpan(label, 2);
    	grid.getChildren().add(label);

    	//Setting an action for the Submit button
    	submit.setOnAction(new EventHandler<ActionEvent>() 
    	{
	    	@Override
	        public void handle(ActionEvent e) 
	    	{

				//test NoSuchAgeException
		    	try 
                {
		    		NoSuchAgeException testException = new NoSuchAgeException("",convertStringtoInt(age.getText()));
		    		testException.validNotSuchAge(convertStringtoInt(age.getText()));
		    		
		    		if ((name.getText() != null && !name.getText().isEmpty())) 
		   	        {
		
		   	            label.setText(name.getText() + " " + lastName.getText()
	    	                + " are now successfully added \ninto this Mini Network!");
		
	    	            Person newPerson = new Person();
	    	            newPerson.setName(name.getText() + " " + lastName.getText());
	    	            newPerson.setPhotoPath("N/A");
	    	        	newPerson.setAge(convertStringtoInt(age.getText()));
	    	            try 
                        {
	    	            	newPerson.setGender(gender.getText().charAt(0));
	    	            }
			    	    catch (Exception stringIndexException)
			    	    {
			        		newPerson.setGender('M'); // set default = male
			    	    }
			    	    newPerson.setStatus(status.getText());
			    	    newPerson.setAusStates(ausStates.getText());
			            personViewList.add(newPerson);
			    	            
			        } 
                    else 
                    {
			            label.setText("You have not insert a name.");
			        }
		    	}
		    	catch(NoSuchAgeException nsaException)
		    	{
		    		label.setText("Error message: No Such Age Exception,\n"
		    				+ "you have to enter a age of person between\n"
		    				+ "0 - 150.");
		    	}
	    	}
	    	     
    	});
    	 
	    //Setting an action for the Clear button
	    clear.setOnAction(new EventHandler<ActionEvent>() 
	    {
	    	@Override
	        public void handle(ActionEvent e) 
	    	{
	            name.clear();
	            lastName.clear();
	            age.clear();
	            gender.clear();
	            status.clear();
	            ausStates.clear();
	            label.setText(null);
	        }
	    });
    		
        //Adding all the nodes to the StackPane
        //grid.getChildren().addAll();
    	grid.setAlignment(Pos.CENTER);
        
        // Creating a scene object
        final Scene scene = new Scene(grid, 400, 300);
        // Adding the title to the window (primaryStage)
        secondaryStage1.setTitle("Add a Person to the network");
        secondaryStage1.setScene(scene);
		
        //Get Style from CSS
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        // Show the window(primaryStage)
        secondaryStage1.show();
    }

	/**
     * A method to open a windows for function 2) Select a person from network
     * 
     * @param secondaryStage2 
     * @return 
     */
    public void start2(Stage secondaryStage2) 
    {
    	//Creating a GridPane container
    	GridPane grid = new GridPane();
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.setVgap(5);
    	grid.setHgap(5);

    	//Adding a dataLabel    	
    	Label dataLabel = new Label();
	    GridPane.setConstraints(dataLabel, 0, 0);
	    GridPane.setColumnSpan(dataLabel, 1);
	    grid.getChildren().add(dataLabel);
    	
        String showntext = "";
        for(int i = 0; i < personViewList.size(); i++)
    	{
    		showntext = showntext + i + ") " + personViewList.get(i).getName() + "\n";
    	}
    	dataLabel.setText(showntext);
    	
    	//Defining the selectNo text field
    	final TextField selectNo = new TextField();
    	selectNo.setPromptText("Enter your select no.");
    	selectNo.setPrefColumnCount(10);
    	selectNo.getText();
    	GridPane.setConstraints(selectNo, 0, 1);
    	grid.getChildren().add(selectNo);
    	
    	//Defining the Submit button
    	Button submit = new Button("Submit");
    	GridPane.setConstraints(submit, 1, 1);
    	grid.getChildren().add(submit);
    	
    	//Defining the Clear button
    	Button clear = new Button("Clear");
    	GridPane.setConstraints(clear, 1, 2);
    	grid.getChildren().add(clear);
    	
    	//Adding a Label
    	final Label label = new Label();
    	GridPane.setConstraints(label, 0, 3);
    	GridPane.setColumnSpan(label, 2);
    	grid.getChildren().add(label);

    	//Setting an action for the Submit button
    	submit.setOnAction(new EventHandler<ActionEvent>() 
    	{

    		@Override
    	    public void handle(ActionEvent e) 
    		{
    	        if (!validationPersonListSize(convertStringtoInt(selectNo.getText())))
    	        {
    	        		label.setText("Your select number is out of bound, please select it again!");
    	        }
    	        else if ((selectNo.getText() != null && !selectNo.getText().isEmpty())) 
    	        {		
    	        		label.setText("Thank you for selecting Person no." + selectNo.getText() + ";\n"
    	        				+ " Name: " + personViewList.get(convertStringtoInt(selectNo.getText())).getName() + ";\n"
    	        				+ " Age: " + personViewList.get(convertStringtoInt(selectNo.getText())).getAge() + ";\n"
    	        				+ " Gender: "+ personViewList.get(convertStringtoInt(selectNo.getText())).getGender() + ";\n"
    	        				+ " Status: "+ personViewList.get(convertStringtoInt(selectNo.getText())).getStatus() + " ");
    	        		selectPersonIndex = convertStringtoInt(selectNo.getText());
    	        } 
    	        
    	        if (selectNo.getText().isEmpty())
    	        {
    	            label.setText("You have not select any items.");
    	        }
    	     }
    	});
    	 
    	//Setting an action for the Clear button
    	clear.setOnAction(new EventHandler<ActionEvent>() 
    	{
    		@Override
    		    public void handle(ActionEvent e) {
    				selectNo.clear();
    		        label.setText(null);
    		        //reset the selectPersonIndex while user press clear 
    		        selectPersonIndex = -1;
    		    }
    	});
    		
        //Adding all the nodes to the StackPane
        //grid.getChildren().addAll();
    	grid.setAlignment(Pos.CENTER);
        
        // Creating a scene object
        final Scene scene = new Scene(grid, 400, 300);
        // Adding the title to the window (primaryStage)
        secondaryStage2.setTitle("Select a person from network");
        secondaryStage2.setScene(scene);
		
        //Get Style from CSS
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        // Show the window(primaryStage)
        secondaryStage2.show();
    }

    /**
     * A method to open a windows for function 3) Display the profile
     * 
     * @param secondaryStage3
     * @param a sets of String to show in label 
     * @return 
     */

    public void start3(Stage secondaryStage3,String t1,String t2,String t3,String t4,String t5,String t6) 
    {    	 	
    	Label label1 = new Label("Name:");
    	TextField textField = new TextField ();
    	HBox hb = new HBox();
    	hb.getChildren().addAll(label1, textField);
    	hb.setSpacing(10);
	
    	//Creating a GridPane container
    	GridPane grid = new GridPane();
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.setVgap(5);
    	grid.setHgap(5);
    	
    	//Adding a Label name
    	final Label name = new Label();
    	name.setText("Name: "+t1);
    	GridPane.setConstraints(name, 0, 1);
    	GridPane.setColumnSpan(name, 2);
    	grid.getChildren().add(name);
	
    	//Adding a Label photoPath
    	final Label photoPath = new Label();

    	if(t2.trim().isEmpty())
    		photoPath.setText("[Photopath]");
    	else
    		photoPath.setText("[" + t2 + "]");
        
    	GridPane.setConstraints(photoPath, 20, 1);
    	GridPane.setColumnSpan(photoPath, 2);
    	grid.getChildren().add(photoPath);
    	
	   //Adding a Label age
    	final Label age = new Label();
    	age.setText("Age: "+t3);
    	GridPane.setConstraints(age, 0, 3);
    	GridPane.setColumnSpan(age, 2);
    	grid.getChildren().add(age);
    	
    	//Adding a Label gender
    	final Label gender = new Label();
    	gender.setText("Gender: "+t4);
    	GridPane.setConstraints(gender, 0, 4);
    	GridPane.setColumnSpan(gender, 2);
    	grid.getChildren().add(gender);
    	
    	//Adding a Label staus
    	final Label status = new Label();
    	status.setText("Status: "+t5);
    	GridPane.setConstraints(status, 0, 5);
    	GridPane.setColumnSpan(status, 2);
    	grid.getChildren().add(status);
    	
    	//Adding a Label ausStates
    	final Label ausStates = new Label();
    	ausStates.setText("State: "+t6);
    	GridPane.setConstraints(ausStates, 0, 6);
    	GridPane.setColumnSpan(ausStates, 2);
    	grid.getChildren().add(ausStates);

        // Adding all the nodes to the StackPane
        //grid.getChildren().addAll();
    	grid.setAlignment(Pos.CENTER);
    
        // Creating a scene object
        final Scene scene = new Scene(grid, 400, 300);
        // Adding the title to the window (primaryStage)
        secondaryStage3.setTitle("Display the profile");
        secondaryStage3.setScene(scene);
		
        //Get Style from CSS
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        // Show the window(primaryStage)
        secondaryStage3.show();
    }

    /**
     * A method to open a windows for function 5) Find out whether two people are directly connected in some ways.
     * 
     * @param secondaryStage5
     * @return 
     */
    public void start5(Stage secondaryStage5) 
    {
		//Create a driver class to for relationship CRUD operation
    	driver = new Driver(personViewList,relationshipViewList);
		
	    //Creating a GridPane container
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(5);
	    grid.setHgap(5);
	    	
	    //Defining the FirstName text field
	    final TextField name = new TextField();
	    name.setPromptText("Enter person1's name.");
	    name.setPrefColumnCount(10);
	    name.getText();
	    GridPane.setConstraints(name, 0, 0);
	    grid.getChildren().add(name);
	    	
	    //Defining the SecondName text field
	    final TextField secondName = new TextField();
	    secondName.setPromptText("Enter person2's name.");
	    GridPane.setConstraints(secondName, 0, 1);
	    grid.getChildren().add(secondName);
	    	
	    //Defining the Submit button
	    Button submit = new Button("Submit");
	    GridPane.setConstraints(submit, 1, 0);
	    grid.getChildren().add(submit);
	    	
	    //Defining the Clear button
	    Button clear = new Button("Clear");
	    GridPane.setConstraints(clear, 1, 1);
	    grid.getChildren().add(clear);
	    	
    	//Adding a Label
    	final Label label = new Label();
    	GridPane.setConstraints(label, 0, 7);
    	GridPane.setColumnSpan(label, 2);
    	grid.getChildren().add(label);

    	//Setting an action for the Submit button
    	submit.setOnAction(new EventHandler<ActionEvent>() 
    	{
	    	@Override
	        public void handle(ActionEvent e) 
	    	{
	            if ((name.getText() != null && !name.getText().isEmpty())) 
	            {
	                //make a String to show msg
	    	        String searchResult = driver.searchRelationship(name.getText(),secondName.getText());
	    	        if (!searchResult.equals(""))
	    	        	label.setText(secondName.getText() + " is " + name.getText() + "'s "
                            + searchResult + "!");
	    	        		
	    	        else
	    	        	label.setText(name.getText() + " and " + secondName.getText() + " have no connections!");
                        
                        
	    	    } 
                else 
                {
	    	        label.setText("You have not left a name.");
	    	    }
	    	}
    	});
    	 
	    //Setting an action for the Clear button
	    clear.setOnAction(new EventHandler<ActionEvent>() 
	    {
	    	@Override
	        public void handle(ActionEvent e) 
	    	{
	            name.clear();
	            secondName.clear();
	            label.setText(null);
	        }
	    });
    		
        //Adding all the nodes to the StackPane
        //grid.getChildren().addAll();
	    grid.setAlignment(Pos.CENTER);
        
        // Creating a scene object
        final Scene scene = new Scene(grid, 500, 300);
        // Adding the title to the window (primaryStage)
        secondaryStage5.setTitle("Find out whether two people are directly connect in some ways");
        secondaryStage5.setScene(scene);
            
        //Get Style from CSS
        scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
            
        // Show the window(primaryStage)
        secondaryStage5.show();
    }
    
    @Override
    public void stop()
    {
    	System.exit(0);
        //By default this does nothing
        //It runs if the user clicks the go-away button
        //closing the window or if Platorm.exit() is called.
        //Use Platorm.exit() instead of System.exit(0).
        //is called. This is where you should offer to 
        //save unsaved stuff the user has generated.
    }
    
    /**
     * A method to open a windows for function 6) Define relation between two people
     * 
     * @param secondaryStage6
     * @return 
     */
    public void start6(Stage secondaryStage6) 
    {
    	//Create a driver class to for relationship CRUD operation
		driver = new Driver(personViewList,relationshipViewList);
    	
    	//Creating a GridPane container
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(5);
	    grid.setHgap(5);
	    	
	    //Defining the FirstName text field
	    final TextField name = new TextField();
	    name.setPromptText("Enter person1's name.");
	    name.setPrefColumnCount(10);
	    name.getText();
	    GridPane.setConstraints(name, 0, 0);
	    grid.getChildren().add(name);
	    	
	    //Defining the SecondName text field
	    final TextField secondName = new TextField();
	    secondName.setPromptText("Enter person2's name.");
	    GridPane.setConstraints(secondName, 0, 1);
	    grid.getChildren().add(secondName);
	    	
	    //Defining the Comment text field
	    final TextField insertRelationship = new TextField();
	    insertRelationship.setPrefColumnCount(15);
	    insertRelationship.setPromptText("Enter the relationship they are.");
	    GridPane.setConstraints(insertRelationship, 0, 2);
	    grid.getChildren().add(insertRelationship);
	    	
	    //Defining the Submit button
	    Button submit = new Button("Submit");
	    GridPane.setConstraints(submit, 1, 0);
	    grid.getChildren().add(submit);
	    	
	    //Defining the Clear button
	    Button clear = new Button("Clear");
	    GridPane.setConstraints(clear, 1, 1);
	    grid.getChildren().add(clear);
	    	
		//Adding a Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, 7);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
	
		//Setting an action for the Submit button
		submit.setOnAction(new EventHandler<ActionEvent>() 
		{
	    	@Override
	    	public void handle(ActionEvent e) 
	    	{
	    		//Define Qualified relationship
	    		Boolean isQualifiedRelationship = false;
	    			
	    		//Define is one person taken
	    		Boolean isTaken = false; 
	    			
	    		//Define is two people able to be friends
	    		Boolean beFriends = false;
	    			
	    		//Define is two person able to be colleagues
	    		Boolean beColleagues = false;
	    			
	    		//Define is two person able to be classmates
	    		Boolean beClassmates = false;
	    			
	    		//Define if a person are not in this network
	    		Boolean isPersonExisted = false;
	    		for (int i = 0 ; i < personViewList.size(); i++ )
	    		{
	    			if(name.getText().trim().equals(personViewList.get(i).getName().trim()))
	    			{
			    		for (int j = 0 ; j < personViewList.size(); j++ )
			    		{
			    			
			    			if(secondName.getText().trim().equals(personViewList.get(j).getName().trim()))
			    			{
			    				isPersonExisted = true;
			    				isQualifiedRelationship = true;
			    			}
			    					
			    		}
	    			}
	    		}
	    			
	    		//get person1 and person2's age for validation
	    		if (isPersonExisted)
	    		{
	    			for (int i = 0 ; i < personViewList.size(); i++ )
	    			{
	    				if(name.getText().trim().equals(personViewList.get(i).getName().trim()))
		    			{
		    				Person person1 = new Person();
		    				person1.setAge(personViewList.get(i).getAge());
		    				System.out.println("Person 1's age is "+ person1.getAge());
		    					
				    		for (int j = 0 ; j < personViewList.size(); j++ )
				    		{	
				    			if(secondName.getText().trim().equals(personViewList.get(j).getName().trim()))
				    			{
				    				Person person2 = new Person();
				    				person2.setAge(personViewList.get(j).getAge());
				    				System.out.println("Person 2's age is "+ person2.getAge());
				    						
				    				if(person1.getAge() < 3 || person2.getAge() < 3)
									{
			    						try 
			    						{
			    							TooYoungException tyException1 = new TooYoungException("",person1.getAge());
			    							tyException1.validTooYoung(person1.getAge());				    								
			    							TooYoungException tyException2 = new TooYoungException("",person2.getAge());
				    						tyException2.validTooYoung(person2.getAge());
				    					}
				    					catch(TooYoungException tyException)
                                        {
				    						if (!insertRelationship.getText().isEmpty())
				    							label.setText("Too Young Exception: \n"
				    										+ "One of the people are too young \nto be "+ insertRelationship.getText() +".");
				    							isQualifiedRelationship = false;
				    					}						
				    				}  						
				    			}	
				    		}
		    			}
	    			}
	    				
	    				
	    			//pass person1's name and person2's name to method isPersonTaken check if they can be couple
	    			if (insertRelationship.getText().equals("couple"))
	    			{
		    			if(isPersonExisted)
		    			{		
		    				isTaken = isPersonTaken(name.getText(), secondName.getText());
		    					
		    				if (isTaken)
		    				{
		    					isPersonExisted = false;
		    					isTaken = true;
		    					isQualifiedRelationship = false;
		    					System.out.println("Person2, one of them are taken!");
		    				}
		    					
			    			if (!isTaken)
			    			{
			    				System.out.println("Person1 is not taken. Let test Person2.");
			    				isTaken = isPersonTaken(secondName.getText(),name.getText());
			    					
			    				if (isTaken)
			    				{
			    					isPersonExisted = false;
			    					isTaken = true;
			    					isQualifiedRelationship = false;
			    					System.out.println("Person1, one of them are taken!");
			    				}
			    			}
		    			}
		    					
		    				
		    			if(isPersonExisted && !isTaken)
		    			{
		    				Person person1 = new Person();
		    				Person person2 = new Person();
			    			for (int i = 0 ; i < relationshipViewList.size(); i++ )
			    	    	{
	
			    		    	for (int peopleIndex = 0 ; peopleIndex < personViewList.size(); peopleIndex++ )
			    		    	{
			    		    		if(name.getText().trim().equals(personViewList.get(peopleIndex).getName().trim()))
			    			    	{
			    			    					
			    			    		person1.setName(personViewList.get(peopleIndex).getName());
			    			    		person1.setAge(personViewList.get(peopleIndex).getAge());
			    			    		person1.setGender(personViewList.get(peopleIndex).getGender());
			    			
	//		    			    		System.out.println("Person 1's name is "+ person1.getName());
	//		    			    		System.out.println("Person 1's age is "+ person1.getAge());
	//		    			    		System.out.println(personViewList.get(peopleIndex).getGender()+"Person 1's gender is "+ person1.getGender());
	//		    			    					
			    					    for (int j = 0 ; j < personViewList.size(); j++ )
			    					    {	
			    					    	if(secondName.getText().trim().equals(personViewList.get(j).getName().trim()))
			    					    	{
			    					    		person2.setName(personViewList.get(j).getName());
			    					    		person2.setAge(personViewList.get(j).getAge());
			    					    		person2.setGender(personViewList.get(j).getGender());
			    					    					
	//		    					    		System.out.println("Person 2's name is "+ person2.getName());
	//		    					    		System.out.println("Person 2's age is "+ person2.getAge());
	//		    					    		System.out.println(personViewList.get(j).getGender()+"Person 2's gender is "+ person2.getGender());
			    					    	}
			    					    }
			    			    	}
			    		    	}
			    		    				
						    	if(insertRelationship.getText().trim().equals(relationshipViewList.get(i).getRelationship().trim()))
						    	{		
				    	    		//if person1 is a child
				    	    		if(person1.getAge() <= 16)
				    	    		{
				    	    			System.out.println(relationshipViewList.get(i).getPerson1().getAge()+"");
				    	    			label.setText("Msg: You cannot set these two people\n"
				    	    					+ "as couple, because person1 is a child!");
				    	    			isQualifiedRelationship = false;
				    	    										    	    						
				    		            try
				    		            {
				    		            	NotToBeCoupledException ntbException = new NotToBeCoupledException("", relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
				    		            	ntbException.validNotToBeCoupled(relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
				    		            }
				    		            catch(Exception ntbException1)
				    		            {
				    		            	System.out.println("Err msg: Cannot be coupled exception!");
				    		            }
				    	    		}

				    	    	    //if person2 is a child
				    	    	    if(person2.getAge() <= 16)
						    	    {
				    	    			System.out.println(relationshipViewList.get(i).getPerson2().getAge()+"");
				    	    			label.setText("Msg: You cannot set these two people\n"
				    	    					+ "as couple, because person2 is a child!");
				    	    			isQualifiedRelationship = false;
				    	    						
				    		            try
				    		            {
				    		            	NotToBeCoupledException ntbException = new NotToBeCoupledException("", relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
				    		            	ntbException.validNotToBeCoupled(relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
				    		            }
				    		            catch(Exception ntbException1)
				    		            {
				    		            	System.out.println("Err msg: Cannot be coupled exception!");
				    		            }
				    	    		}
				    	    					
				    	    		//if person1 and person2 are both male
				    	    		if(person1.getGender() == 'M')
				    	    		{
				    	    			if(person2.getGender() == 'M')
				    	    			{
					    	    			System.out.println(relationshipViewList.get(i).getPerson2().getAge()+"");
					    	    			label.setText("Msg: You cannot set these two people\n"
					    	    					+ "as couple, because person1 and person2 \n"
					    	    					+ "are both male!");
					    	    			isQualifiedRelationship = false;
					    	    		}
				    	    		}
				    	    					
				    	    		//if person1 and person2 are both female
				    	    		if (person1.getGender() == 'F')
				    	    		{
				    	    			if(person2.getGender() == 'F')
				    	    			{
					    	    			System.out.println(relationshipViewList.get(i).getPerson2().getAge()+"");
					    	    			label.setText("Msg: You cannot set these two people\n"
					    	    					+ " as couple, because person1 and person2 \n"
					    	    					+ "are both female!");
					    	    			isQualifiedRelationship = false;
					    	    		}	
				    	    		}
						    	}
			    	    	}
		    			}
	    			}
	    				
	    			//Be Friends
	    			if(isPersonExisted && insertRelationship.getText().equals("friends"))
	    			{
	    				beFriends = true;
	    				beFriends = beFriends(name.getText(),secondName.getText());
	    				if (!beFriends)
	    					isQualifiedRelationship = false;
	    			}
	    				
	    			//Be Colleagues
	    			if(isPersonExisted && insertRelationship.getText().equals("colleagues"))
	    			{
	    				beColleagues = true;
	    				beColleagues = beColleagues(name.getText(),secondName.getText());
	    				if (!beColleagues)
	    					isQualifiedRelationship = false;
	    			}
	    				
	    			//Be Classmates
	    			if(isPersonExisted && insertRelationship.getText().equals("classmates"))
	    			{
	    				beClassmates = true;
	    				beClassmates(name.getText(),secondName.getText());
	    				if (!beClassmates)
	    					isQualifiedRelationship = false;
	    			}
	    				
		    		if (insertRelationship.getText().equals("friends") || insertRelationship.getText().equals("colleagues") || insertRelationship.getText().equals("classmates") || insertRelationship.getText().equals("couple"))
		    		{
			    		if ((insertRelationship.getText() != null && !insertRelationship.getText().isEmpty())) 
			    	    {
			    	        //make a string to show msg
			    			if (isQualifiedRelationship)
			    			{
				                label.setText(name.getText() + " and " + secondName.getText()
					   	           + " now became " + insertRelationship.getText() + "!");
				                //renew relationshipViewList
				                relationshipViewList = driver.addRelationship(name.getText(),secondName.getText(),insertRelationship.getText());
				    			System.out.println("A Relationship has been added into this Network.");
			    			}
			    		} 
                        else 
                        {
			    	        label.setText("You have not left a relationship.");
			    	    }
		    		} 
                    else 
                    {
		    			label.setText("Msg: You can only define as 'friends',\n 'colleagues', 'classmates' or 'couple'.");
		    		}
	    			
	    		}
	    		
	    		if (!beFriends && insertRelationship.getText().equals("friends"))
	    			label.setText("Msg: These two people cannot be Friends.\n"
	    					+ "Please check your inserted names!");
	    		if (!beColleagues && insertRelationship.getText().equals("colleagues"))
	    			label.setText("Msg: These two people cannot be Colleagues.\n"
	    					+ "Please check your inserted names!");
	    		if (!beClassmates && insertRelationship.getText().equals("classmates"))
	    			label.setText("Msg: These two people cannot be Classmates.\n"
	    					+ "Please check your inserted names!");
	    		
	    		if(!isPersonExisted)
	    			label.setText("Msg: One of the people are not found in this \n"
	    					+ "Network, please check your inserted names!");
	    		
	    		if (isTaken)
	    			label.setText("Msg: One of the people are taken in this \n"
	    					+ "Network, please check your inserted names!");
	    		
	    		}
	    	});
		 
	    	//Setting an action for the Clear button
	    	clear.setOnAction(new EventHandler<ActionEvent>() 
	    	{
	    		@Override
	    	    public void handle(ActionEvent e) 
	    		{
	    	        name.clear();
	    	        secondName.clear();
	    	        insertRelationship.clear();
	    	        label.setText(null);
	    	    }
	    	});
			
	    //Adding all the nodes to the StackPane
	    //grid.getChildren().addAll();
		grid.setAlignment(Pos.CENTER);
	    
	    // Creating a scene object
	    final Scene scene = new Scene(grid, 500, 300);
	    // Adding the title to the window (primaryStage)
	    secondaryStage6.setTitle("Define relation between two people");
	    secondaryStage6.setScene(scene);
		
	    //Get Style from CSS
	    scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
	    
	    // Show the window(primaryStage)
	    secondaryStage6.show();

    }
    
    /**
     * A method to open a windows for function 7) Find out the name(s) of the children or parents
     * 
     * @param  
     * @return 
     */
    public void start7(Stage secondaryStage7, String t1, String t2) 
    {	
	    //Creating a GridPane container
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(5);
	    grid.setHgap(5);
	    	
	    //Adding a Label title1
	    final Label title1 = new Label();

	    if(!t1.trim().isEmpty())
	    	title1.setText("[ Father ]");

	    GridPane.setConstraints(title1, 0, 0);
    	GridPane.setColumnSpan(title1, 2);
    	grid.getChildren().add(title1);
	    	
    	//Adding a Label title2
    	final Label title2 = new Label();

    	if(!t2.trim().isEmpty())
    		title2.setText("[ Mother ]");

    	GridPane.setConstraints(title2, 20, 0);
    	GridPane.setColumnSpan(title2, 2);
    	grid.getChildren().add(title2);
		
	    //Adding a Label1
	    final Label person1 = new Label();

	    if(t1.trim().isEmpty())
	    	person1.setText("[ Father's name ]");
	    else
	    	person1.setText("[ " + t1 + " ]");

	    GridPane.setConstraints(person1, 0, 1);
    	GridPane.setColumnSpan(person1, 2);
    	grid.getChildren().add(person1);
		
	    //Adding a Label2
	    final Label person2 = new Label();

	    if(t2.trim().isEmpty())
	    	person2.setText("[ Mother's name ]");
	    else
	    	person2.setText("[ " + t2 + " ]");
	    	
	    if(!t1.trim().isEmpty() && t2.trim().isEmpty())
	    {
	    	person1.setText("[ " + t1 + " ]");
	    	person2.setText("");
	    	title1.setText("My children are");
	    	title2.setText("");
	    }

    	GridPane.setConstraints(person2, 20, 1);
    	GridPane.setColumnSpan(person2, 2);
    	grid.getChildren().add(person2);
		
    	//Adding all the nodes to the StackPane
    	//grid.getChildren().addAll();
		grid.setAlignment(Pos.CENTER);
	    
    	//Creating a scene object
    	final Scene scene = new Scene(grid, 400, 300);
    	// Adding the title to the window (primaryStage)
    	secondaryStage7.setTitle("Find out the name(s) of the children or parents");
    	secondaryStage7.setScene(scene);
		
    	//Get Style from CSS
    	scene.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
    	    
    	//Show the window(primaryStage)
    	secondaryStage7.show();
	    
    }
  
    /**
     * A method to show if the person is taken
     * 
 	 * @param  person1Name a String of person1's name
 	 * @param  person2Name a String of person2's name
 	 * @return Boolean is Person1 or person2 are taken
     */
 	
 	public Boolean isPersonTaken(String person1Name, String person2Name)
 	{
 		for(int i = 0; i < relationshipViewList.size(); i++)	
        {	
 			if (relationshipViewList.get(i).getPerson2().getName().trim().equals(person2Name.trim()))
 			{
 				
	        	System.out.println("(1)");
	            System.out.println("Name:" + relationshipViewList.get(i).getPerson1().getName());
	            System.out.println(relationshipViewList.get(i).getPerson2().getName());
	            System.out.println(relationshipViewList.get(i).getRelationship());
	            if(relationshipViewList.get(i).getRelationship().trim().equals("couple"))
	            {	
	            	System.out.println("Tester found in Person2 position");
	            	NotToBeCoupledException ntbException = new NotToBeCoupledException("", relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
	            	try
	            	{
	            		ntbException.validNotToBeCoupled(relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
	            	}
	            	catch(Exception ntbException1)
                    {
	            		System.out.println("Err msg: Cannot be coupled exception!");
	            	}
	            		return true;
	            }

 			}
        }
 		
 		for(int i = 0; i < relationshipViewList.size(); i++)
 		{	
 			
 			if (relationshipViewList.get(i).getPerson1().getName().trim().equals(person2Name.trim()))
 			{

	        	System.out.println("(2)");
	            System.out.println(relationshipViewList.get(i).getPerson1().getName());
	            System.out.println("Name:" +relationshipViewList.get(i).getPerson2().getName());
	            System.out.println(relationshipViewList.get(i).getRelationship().equals("couple"));
	            if(relationshipViewList.get(i).getRelationship().trim().equals("couple"))
	            {	
	            	System.out.println("=Tester found in Person1 position=");
	            	try
	            	{
	            		NotToBeCoupledException ntbException = new NotToBeCoupledException("", relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
	            		ntbException.validNotToBeCoupled(relationshipViewList.get(i).getPerson2().getAge(), relationshipViewList.get(i).getPerson2().getAge());
	            	}
	            	catch(Exception ntbException1)
	            	{
	            		System.out.println("Err msg: Cannot be coupled exception!");
	            	}
	            	return true;
	            }

 			}
 		}
 		
 		return false;
 	}

 	/**
     * A method to show if the person is able to be friends
     * 
 	 * @param  person1Name a String of person1's name
 	 * @param  person2Name a String of person2's name
 	 * @return Boolean is Person1 or person2 able to be friends
     */
 	
 	public Boolean beFriends(String person1Name, String person2Name)
 	{
 		int person1age = 0;
 		int person2age = 0;
 		for(int i = 0; i < personViewList.size(); i++)	
        {	
 			if (personViewList.get(i).getName().trim().equals(person1Name.trim()))
 			{
 					
	        	System.out.println("(1)");
	            System.out.println("Name:" + personViewList.get(i).getName());
	            System.out.println("Age:" + personViewList.get(i).getAge());
	            person1age = personViewList.get(i).getAge();
 			}
        }
 		
 		for(int j = 0; j < personViewList.size(); j++)	
        {	
 			if (personViewList.get(j).getName().trim().equals(person2Name.trim()))
 			{
 					
	        	System.out.println("(2)");
	            System.out.println("Name:" + personViewList.get(j).getName());
	            System.out.println("Age:" + personViewList.get(j).getAge());
	            person2age = personViewList.get(j).getAge();
 			}
        }
 		
		try 
		{
			NotToBeFriendsException ntbfException = new NotToBeFriendsException("",person1age,person2age);
			ntbfException.validNotToBeFriends(person1age, person2age);
		}
		catch(NotToBeFriendsException ntbfException1)
		{
			System.out.println("Err Msg: Cannot be friends Exception");
		}

 		
 		if (person1age > 16 && person2age > 16)
 			return true;
 		if (person1age > 2 || person2age > 2)
 			if((person1age - person2age) * (person1age - person2age) < 9)
 				return true;
 		
 		return false;
 	}
 	
 	/**
     * A method to show if the person is able to be Colleagues
     * 
 	 * @param  person1Name a String of person1's name
 	 * @param  person2Name a String of person2's name
 	 * @return Boolean is Person1 or person2 able to be Colleagues
     */
 	
 	public Boolean beColleagues(String person1Name, String person2Name)
 	{
 		int person1age = 0;
 		int person2age = 0;
 		for(int i = 0; i < personViewList.size(); i++)	
        {	
 			if (personViewList.get(i).getName().trim().equals(person1Name.trim()))
 			{
 					
	        	System.out.println("(1)");
	            System.out.println("Name:" + personViewList.get(i).getName());
	            System.out.println("Age:" + personViewList.get(i).getAge());
	            person1age = personViewList.get(i).getAge();
 			}
        }
 		
 		for(int j = 0; j < personViewList.size(); j++)	
        {	
 			if (personViewList.get(j).getName().trim().equals(person2Name.trim()))
 			{
 					
	        	System.out.println("(2)");
	            System.out.println("Name:" + personViewList.get(j).getName());
	            System.out.println("Age:" + personViewList.get(j).getAge());
	            person2age = personViewList.get(j).getAge();
 			}
        }
 		
		try 
		{
			NotToBeColleaguesException ntbcException = new NotToBeColleaguesException("",person1age,person2age);
			ntbcException.validNotToBeColleagues(person1age,person2age);
		}
		catch(NotToBeColleaguesException ntbcException1)
		{
			System.out.println("Err Msg: Cannot be colleagues Exception");
		}
 		
 		if (person1age > 16 && person2age > 16)
 			return true;
 		
 		return false;
 	}
 	
 	/**
     * A method to show if the person is able to be Classmates
     * 
 	 * @param  person1Name a String of person1's name
 	 * @param  person2Name a String of person2's name
 	 * @return Boolean is Person1 or person2 able to be Classmates
     */
 	
 	public Boolean beClassmates(String person1Name, String person2Name)
 	{
 		int person1age = 0;
 		int person2age = 0;
 		for(int i = 0; i < personViewList.size(); i++)	
        {	
 			if (personViewList.get(i).getName().trim().equals(person1Name.trim()))
 			{
 					
	        	System.out.println("(1)");
	            System.out.println("Name:" + personViewList.get(i).getName());
	            System.out.println("Age:" + personViewList.get(i).getAge());
	            person1age = personViewList.get(i).getAge();
 			}
        }
 		
 		for(int j = 0; j < personViewList.size(); j++)	
        {	
 			if (personViewList.get(j).getName().trim().equals(person2Name.trim()))
 			{
 					
	        	System.out.println("(2)");
	            System.out.println("Name:" + personViewList.get(j).getName());
	            System.out.println("Age:" + personViewList.get(j).getAge());
	            person2age = personViewList.get(j).getAge();
 			}
        }
 		
		try 
		{
			NotToBeClassmatesException ntbclException = new NotToBeClassmatesException("",person1age,person2age);
			ntbclException.validNotToBeClassmates(person1age,person2age);
		}
		catch(NotToBeClassmatesException ntbclException1)
		{
			System.out.println("Err Msg: Cannot be classmates Exception");
			return false;
		}
 		
 		if (person1age > 2 && person2age > 2)
 			return true;
 		
 		return false;
 	}
 	
    /**
     * Main function that opens the "MiniNet" window
     * 
     * @param args the command line arguments
     */
    public static void main(final String[] arguments) 
    {
        launch(arguments);
    }
    
    private int convertStringtoInt(String input) //method to convert String to Integer
    {
        //intialised variables
        String S = input;
        int i = -1;
        //try catch to handle NumberFormatException
        try
        {
            // the String to int conversion happens here
            i = Integer.parseInt(input.trim());

            // print out the value after the conversion
            // System.out.println("int i = " + i);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage() + ", please input an integer!");
        }
        return i; //if (i == -1) it is a NumberFormatException. 
    }
    
    /**
     * A validation function to valid inputsize is Out of Bound or not
     * 
     * @param inputSize a int of inputSize
     */
    public Boolean validationPersonListSize(int inputSize)
    {
    	if (0 <= inputSize && inputSize < personViewList.size())
    		return true;
    	if (inputSize > personViewList.size() || inputSize < 0)
    		return false;
		return false;
    }
    
}