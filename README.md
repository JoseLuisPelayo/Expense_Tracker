# Xpense Tracker
Xpense tracker is a command-line application for tacking and managing expenses.  
It provides a straitforward way to interact with a expenses database using a simple cli interface.
  
### It allow users to
> - Add new expenses with date, description and amount.  
> - Update existing expenses.
> - Delete expenses.  
> - View all expenses.
> - View a summary of all expenses.
> - View a summary of expenses for a specific month.
> - Save and load expenses from a file.

## Installation
1. Prerequisites  
Ensure you have the following installed on your system:  
    Java Development Kit (JDK)  
    Apache Maven  

2. Navigate to the directory where you want to install the application and clone or download this repository:
     `git clone https://github.com/JoseLuisPelayo/Expense_Tracke`
   
3. Navigate to the project  
     `cd Expense_Tracker`
   
4. Compile the project
    There are two ways to compile the project
    - In windows its possible execute the setup.bat file.  
        This script compile the project and add the /target folder to your system's PATH environment variable, allowing you to use the app globally.

    - Compile manually the project  
      `mvn clean install`  
       Add the folder .../target to the path enviroment variable.  
             [How to add a variable to the path enviroment variable in Windows](https://learn.microsoft.com/en-us/previous-versions/office/developer/sharepoint-2010/ee537574(v=office.14))  
             [How to add a variable to the path enviroment variable in Mac](https://medium.com/@B-Treftz/macos-adding-a-directory-to-your-path-fe7f19edd2f7)  
             [How to add a variable to the path enviroment variable in Linux](https://www.redswitches.com/blog/path-variable-in-linux/#:~:text=variable%20in%20Ubuntu%3F-,To%20permanently%20add%20a%20directory%20to%20the%20PATH%20variable%20in,Save%20the%20file%20and%20exit.)

5. Reload Your Terminal
     Once the PATH is updated, for the changes to take effect.
      
## Usage
- Commands are:  
    update                   -> Update expense  
    add                      -> Add a expense  
    remove, rm, delete, del  -> Remove a expense  
    find, f                  -> Find expense by ID  
    list, ls                 -> List expenses  
    summary, sum             -> Summary expenses  

- Add a expense
  - Usage:  
   This command add a expense to our history  
    `xpenseTracker add [-hV] [-d=<expense date>] <expense description><expense amount>`  
  - Options are:  
    -d, --date=<expense date>  
       -h, --help         Show this help message and exit.  
    -V, --version      Print version information and exit.  

- Update expense  
  - Usage:  
    This command update a expense  
      `xpenseTracker update [-hV] [-a=<expense amount>] [-d=<expense description>] <expense ID>`  
  - Options are:  
  -a, --amount=<expense amount>  
  -d, --description=<expense description>  
  -h, --help         Show this help message and exit.  
  -V, --version      Print version information and exit.  

- Remove a expense  
  - Usage:  
  This command delete a expense of our history    
   `xpenseTracker remove[-hV] <expense ID>`    
  - Options are:  
  -h, --help         Show this help message and exit.    
  -V, --version      Print version information and exit.

- Find expense by ID  
  - Usage:  
This command find a expense by ID  
`xpenseTracker find [-hV] <expense ID>`  
  - Options are:  
  -h, --help         Show this help message and exit.  
  -V, --version      Print version information and exit.  

- List expenses  
  - Usage:  
This command list all expenses  
`xpenseTracker list [-hV]`  
  - Options are:  
  -h, --help      Show this help message and exit.  
  -V, --version   Print version information and exit.  

- Summary expenses  
  - Usage:  
This command provide the summary of  expenses    
 `xpenseTracker summary [-hV] [-m=<month number 1 to 12>]`  
  - Options are:  
  -h, --help      Show this help message and exit.  
  -m, --month=<month number 1 to 12>  List the summary of the month expenses in the current year  
  -V, --version   Print version information and exit.  

