import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Project 0 banking app\n"
					  + "*********************       \n"
					  + "Select an option:           \n"
					  + "Exit..................... 0 \n"
					  + "Register................. 1 \n"
					  + "Log in................... 2 \n");
			input = scan.nextInt();
			scan.nextLine();		
				switch(input) {
			case 0:
				//EXIT
				System.out.println("Exiting application...");
				return;
			case 1:
				//REGISTER
				System.out.println("To create an account, start by entering your first and last name: \n");
				String newAccountName = scan.nextLine();
				System.out.println("Please enter a password for your new account: ");
				String newAccountPassword = scan.nextLine();
				Actor customer = new Actor(newAccountName, newAccountPassword);
				System.out.println("Thank you for creating your new account, " + newAccountName + ".\n"
								 + " You can now log in at the main menu to apply for a bank account!");
				break;
			case 2:
				//LOG IN
				System.out.println("Enter the name associated with your account: \n");
				String nameCredentials = scan.nextLine();
				System.out.println("Please enter the password associated with you account: \n");
				String passwordCredentials = scan.nextLine();
				//TODO call a log-in process
				//TODO handle account not found exception
				//TODO load account info from database
				System.out.println("(debug)\n"
						+ "0... Log out \n"
						+ "1... Customer\n"
						+ "2... Employee\n"
						+ "3... Admin   \n");
				int accountFound = scan.nextInt(); //TODO remove
				switch(accountFound) {
				case 0:
					System.out.println("Your account could not be found :(\n");
					break;
				case 1: //CUSTOMER ACCOUNT
					int accountOption;
					do {
						//Once the account is open, customers should be able to withdraw, deposit, and transfer funds between accounts
						System.out.println("Log in successful. Welcome back, (account holder first name)!\n");
						System.out.println("Select an option:    \n"
								  + "Log out.................. 0 \n"
								  + "Withdraw from account.... 1 \n"
								  + "Deposit to account....... 2 \n"
								  + "Transfer funds........... 3 \n"
								  + "Apply for an account .... 4");
						accountOption = scan.nextInt();
						scan.nextLine();
						switch(accountOption) {
						case 0:
							break;
						case 1:
							//WITHDRAWING
							System.out.println("Choose the account you'd like to withdraw from: ");
							//TODO show their accounts
							System.out.println("Test account 1 (show account balance)........... 1\n"
											 + "Test account 2 (show account balance)........... 2\n");
							int withdrawFrom = scan.nextInt();
							scan.nextLine();
							
							System.out.println("How much would you like to withdraw?\n ");
							float withdrawAmount = scan.nextFloat();
							scan.nextLine();
							
							System.out.println("Withdraw of  " + withdrawAmount + " complete. New account balance: " + "(updated account balance)");
							//TODO withdraw withdrawAmount from withdrawFrom	
							break;
						case 2:
							//DEPOSITING
							System.out.println("Choose the account you'd like to deposit to: ");
							//TODO show their accounts
							System.out.println("Test account 1 (show account balance)........... 1\n"
											 + "Test account 2 (show account balance)........... 2\n");
							int depositFrom = scan.nextInt();
							scan.nextLine();
							
							System.out.println("How much would you like to deposit to this account? \n ");
							float depositAmount = scan.nextFloat();
							scan.nextLine();
							System.out.println(depositAmount + " was deposited into your account. Your new balance is (show account balance).");
							break;
						case 3:
							//TRANSFERRING
							System.out.println("Choose the account you'd like to transfer from: ");
							//TODO show their accounts
							System.out.println("Test account 1 (show account balance)........... 1\n"
											 + "Test account 2 (show account balance)........... 2\n");
							int transferFrom = scan.nextInt();
							scan.nextLine();
							System.out.println("Choose the account you'd like to transfer to: ");
							//TODO show their accounts minus account that is being transfered from
							System.out.println("Test account 1 (show account balance)........... 1\n"
											 + "Test account 2 (show account balance)........... 2\n"
											 + "Only show account not being transfered from..... :)\n");
							int transferTo = scan.nextInt();
							scan.nextLine();
							System.out.println("How much would you like to transfer?\n ");
							float transferAmount = scan.nextFloat();
							scan.nextLine();
							System.out.println("Transfer complete. Your new account balances are: \n"
											 + "(account 1 balance)"
											 + "(account 2 balance)");
							break;
						case 4:
							//APPLYING
							System.out.println("What kind of account would you like to apply to?\n\n"
									+ "Individual Account...... 1\n"
									+ "Joint Account........... 2\n");
							int accountApply = scan.nextInt();
							scan.nextLine();
							switch(accountApply) {
							case 1:
								//CREATING INDIVIDUAL ACCOUNT
								System.out.println("Please enter the first and last name for the account: \n");
								String newAccountApplicationName = scan.nextLine();
								System.out.println("Please enter a password for your account:\n");
								String newAccountApplicationPassword = scan.nextLine();
								
								//TODO create account process
								System.out.println("Thank you for applying! We will review your application for an individual account shortly.");
								break;
								
							case 2:
								//CREATING JOINT ACCOUNT
								System.out.println("Please enter your first and last name for the account: \n");
								String newJointAccountName1 = scan.nextLine();
								System.out.println("Please enter your joint account holder's first and last name: \n");
								String newJointAccountName2 = scan.nextLine();
								System.out.println("Please enter a password for your account:\n");
								String newJointAccountPassword = scan.nextLine();
								
								//TODO use a create-new-account process
								//TODO check for account not found exception
								System.out.println("Thank you for applying! We will review your application for a join account shortly");
								break;
							}
						}
					}while(accountOption != 0);

				case 2:
					//EMPLOYEE ACCOUNT
					/*
					 * Employees of the bank should be able to view all of their customers information. This includes:
					 * Account information
					 * Account balances
					 * Personal information
					 * Employees should be able to approve/deny open applications for accounts
					 */
					
					System.out.println("Log in successful. Welcome back, (employee first name)!\n");
					int employeeAccountOption;
					do {
						System.out.println("Select an option:    \n"
								  + "Log out.................. 0 \n"
								  + "Lookup customer.......... 1 \n"
								  + "Review applications...... 2 \n");
						employeeAccountOption = scan.nextInt();
						scan.nextLine();
						switch(employeeAccountOption) {
						case 0:
							break;
						case 1:
							System.out.println("Enter the customer name you want to look up: \n");
							String employeeLookup = scan.nextLine();
							//TODO look up customer account

							boolean customerAccountFound = false;
							if (customerAccountFound) {
								System.out.println("Customer account found for " + employeeLookup);
								int employeeCustomerChoice;
								do {
									System.out.println("Select an option to view:    \n"
											  + "Exit..................... 0 \n"
											  + "Account information...... 1 \n"
											  + "Account balances......... 2 \n"
											  + "Personal Information..... 3 \n");
									
									employeeCustomerChoice = scan.nextInt();
									scan.nextLine();
									
									switch(employeeCustomerChoice) {
									case 0:
										break;
									case 1:
										//TODO lookup account info
										System.out.println("No account information found");
									case 2:
										System.out.println("No account balances found");
									case 3:
										System.out.println("No custome information found");
									}

									
								} while(employeeCustomerChoice != 0);
							}
							System.out.println("Customer account not found");								
						case 2:
							//TODO load pending applications
							//try-catch block...
							System.out.println("No applications found");
						}	
					} while(employeeAccountOption != 0);
					break;
				case 3:
					//ADMIN ACCOUNT
					/*
					 * Bank admins should be able to view and edit all accounts
					 * This includes:
					 * Approving/denying accounts
				     * withdrawing, depositing, transferring from all accounts
				     * canceling accounts
					 */
					System.out.println("Log in successful. Welcome back, (employee first name)!\n");

					int adminChoice;
					do {
						System.out.println("Select an option to view: \n"
								       + "Exit..................... 0 \n"
								       + "Review Applications...... 1 \n"
								       + "Lookup Account........... 2"
								       + "Create New Transaction... 3 \n"
								       + "Cancel Accounts.......... 4 \n");
						adminChoice = scan.nextInt();
						scan.nextLine();
						
						switch(adminChoice) {
						case 0:
							break;
						case 1: 
							System.out.println("Here are all the open applications for review: ");
							break;
						case 2:
							System.out.println("Enter the name of the employee or customer you'd like to look up: \n");
							break;
						case 3: 
							System.out.println("Please enter the name of the account that you'd like to create a transaction for: ");
							break;
						case 4: 
							System.out.println("Enter the name associated with the account that you're looking to cancel: ");
							String cancelAccountString = scan.nextLine();
							System.out.println("Account is calcelled");
						}
						
						
					} while(adminChoice != 0);
					break;
				}
				break;
			}
		} while (input != 0);
		scan.close();
	}

}