EU Travel Registration System
Project Type: Client-Server Application
Language: Java
IDE: Apache NetBeans
Project Description
The system ensures data persistence, proper validation of personal and travel information, and error handling for network communication.

Features
Core Functionality (65%)
Users can apply for travel to EU countries, providing the following information:
First and last name
JMBG (Unique Master Citizen Number)
Passport number
Destination country/countries
Entry and exit dates
Mode of transportation (car, motorcycle, bus, air travel)
After a successful application, the system generates a text file containing all travel details:
Name, JMBG, passport number, destination countries, application date, entry date, expected stay duration
Information whether the user must pay for the application (for users aged 18–70)
Multiple clients can connect to the server simultaneously.
Validations:
Entry date cannot be before the current date; exit date cannot be before the entry date.
Maximum travel duration is 90 days.
Passport number must match the JMBG.
Users cannot apply for overlapping trips to the same countries.
Valid JMBG and passport number are checked against a predefined population database.
Additional Feature 1 (10%)
Registered users can view all their previous trips based on JMBG and passport number.
Trip status is shown as:
Completed
In progress
Locked (less than 48 hours until departure)
Additional Feature 2 (15%)
Users can register and log in:
Registration requires: username, password, first name, last name, email, JMBG, passport number
Login via username and password
Registered users can apply for trips by entering only the date and destination countries

Restrictions:
Unique username, JMBG, and passport number for each user
Persistent data storage after server shutdown
Additional Feature 3 (10%)
Registered users can edit their current travel applications up to 48 hours before the entry date.
Data persistence is maintained after server shutdown.

Technologies Used
Java SE – core programming
Apache NetBeans IDE – development environment
Client-Server Communication – TCP/IP sockets
File Handling – text files for travel records
Data Validation – JMBG, passport number, travel dates
Multi-threading – server supports multiple concurrent clients

How to Run
Open the project in Apache NetBeans.
Start the Server application first.
Run the Client application and connect to the server.
Use the menu options to register, log in, apply for travel, and view previous trips.

Future Improvements
Implement a GUI for better user experience.
Store user and travel data in a database (e.g., MySQL) instead of text files.
Add email notifications for trip confirmation.
