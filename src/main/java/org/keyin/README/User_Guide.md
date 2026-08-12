## USER GUIDE

**System Overview**
The Gym App is a console-based system for managing gym users, memberships, workout classes, and merchandise. It uses menu choices to let people add information, view records, and manage gym services.

**User Roles**
The system has three roles with different permissions.

*Member* can:
- Purchase a membership
- View their membership
- Browse available workout classes
- View merchandise available for purchase

*Trainer* can:
- Create, update, and delete workout classes assigned to them
- Purchase a membership
- Browse available workout classes
- View merchandise available for purchase

*Admin* can:
- View all users and contact information
- Delete users
- View total membership revenue
- Add new merchandise
- View merchandise stock and total valuation
- Create, update, and delete workout classes

**Common Workflows**

*Create an account*
1. Start the app.
2. Choose the option to add a new user.
3. Enter a username, password, email, phone number, address, and role.
4. Submit the form to create the account.

*Log in*
1. Choose the login option from the main menu.
2. Enter your username.
3. Enter your password.
4. The system opens the menu for your role.

*Purchase a membership*
1. Log in as a Member or Trainer.
2. Choose the membership purchase option.
3. Enter the membership type, price, and purchase date.
4. The membership is saved to the system.

*Manage workout classes*
1. Log in as a Trainer or Admin.
2. Choose the class option you need, such as create, update, or delete.
3. Enter the requested class details.
4. The system saves the changes.

*View merchandise*
1. Log in as any role that has merchandise access.
2. Choose the view merchandise option.
3. The system displays the available items.

**System Limitations**
- The app uses a text-based console instead of a graphical interface.
- Payments are entered manually and are not processed online.
- Users must enter information carefully because there is limited input validation.
- The system depends on the PostgreSQL database being available.