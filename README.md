Necessary Software
In order to run the first iteration (prototype) of the under graduate coordinator information system, the user first has several software packages they need to install. These packages are:
Eclipse: for importing and operating the system,
XAMPP:  for running apache and MySQL services needed for operating the system and its data,
Tomcat7: for running the web service from eclipse
Java 7 minimum SDK and JRE.
In order to run the system, the user firstly has to open eclipse. Once open, navigate to File > Import  and then click on General > Add existing project into workspace. From there you should be able to select the file infs3605 that you grabbed from our bit bucket. 
Next, it is time to setup the XAMPP environment. Firstly, select the MySQL tab in the XAMPP control panel and click on admin. This will open up a window called “phpMyAdmin” and will allow you to import out database. Our sql file is locate in the infs3605 folder by navigating INFS3605 > DB > flt.sql then in phpMyAdmin go to Import > ‘select the file’ > GO.

Lastly, in select the main project file in the project view and go to Java Build Path > Configure Build Path > Libraries if there are errors, select them all and remove, then head to Add External Jars > INFS3605 > WebContent > WEB-INF > lib and select all the file for import, then head over to the order and export tab and select them all and click apply. 

Accessing the application
In order to access the system, the user needs to run it as a server, select the Tomcat7 server they created for it and and click finish. Then they will either be automatically taken to the main page or need to access it from http://localhost:8080/FourLeavesTech/ noting that the 8080 number is specific to what you defined in the XAMPP control panel.
If you have not made an account, click on Register, and fill out the appropriate details (Noting that the system will send emails to the email you provide. Then click login, if you fail the three login attempts then an email will be sent to your email address requiring you to validate you account. 

Navigating the system
From the navigation bar, you will be presented with a few options:
UGCIS: this directs you to the main page
LOGOUT: this ends your session with the system and returns you to the login page
CALENDAR: this directs you to the calendar page
MENU
ADD STUDENT: this allows you to create an individual student and store its details in the system
ADD BOOKING: this allows you to create a booking via form
STUDENTS: lists all students in the system
COURSES: lists all the courses and allows you to CRUD them	
PROGRAMS: allows you to view all courses and CRUD them
CO-OP: if you are logged in as the Co op coordinator, you are given the the option to:
COURSES: view the test results of each student based on their courses
SPONSORS: Manage CRUD sponsors for the co op program
EMAIL PREFERENCES: Email each student the form for them to enter their preferences for the IP level selected
PROFILE: allows you to update the details in your profile
UGCIS
This main page allows the coordinator to see all the bookings they have made. To view the details of each booking, click on the details tab next to each booking. This will open the details page. From here, you can do a few things:
Update the booking by entering in the data into the form, and selecting the update button.
Send notes to each student: clicking the send notes button will send the student an email containing all the notes the coordinator has made,
Add notes, clicking the + button in the notes section will allow you to fill out some information based on the booking. This list is endless and will be displayed in the bookings page once you insert it. You can also delete this note by clicking delete, update by clicking update. 
Delete the booking by clicking delete on the home page (clicking UGCIS)
Add and update / delete additional attendees to the appointment
LOGOUT
This button will end the users session and return them to the login page

CALENDAR
This page gives the user a visual element to interact with their specific bookings. By default, you are given a week view of your calendar centering in on the current day. You can change this to either month or day view by selecting those options in the top section. 
Adding a booking is as simple as dragging out an area on any view, then entering in the students zID into the text area and then clicking the tick button. From here you can return to the main page (UGCIS) and update the booking by clicking on details.
Updating a booking can be done by selecting a booking and moving it around. The new date and time will be reflected in the database.
Deleting the calendar is as simple as selecting the booking, and pressing the garbage can icon.
Any additions or changes to the bookings are emailed to the student
ADD STUDENT
To add a student, click on the add student option in the menu. From here you are given a form, fill it out with the necessary details and click submit. Then you will be presented with the students page.

ADD BOOKING
This is a manual form where you can manually create both a student and a booking. Enter all fields as necessary and click submit.

STUDENTS
this is a list of all the students in the program. You can update an individual student by clicking update, and delete by clicking delete.

COURSES
This is a list view page that allows you to see a list of all the courses that the system has stored.

PROGRAMS
This is a list of all the programs in the system.

PROFILE
This is a form that allows you to update the details you registered with. By clicking on the element, and altering it and then clicking the submit button, you can change your details in the system.

**Creating Co-op Students
**1) Click on [Menu] from the header
2) [Add Student]
3) Make Student Type = [Co-op]
4) Fill in the necessary Fields
5) Press [Add]

**Managing Co-op Student’s Grades 
**1) Insert the Courses & its respective Grades under Student_Details
2) Click on [CO-OP] from the header menu > [Test Results]
3) Specify the Year and the Semester
4) A Collective view of the zID, rankings, average WAM, and grades are shown
5) Students who fail to meet the criteria is highlighted [RED] on grades
6) Clicking on the zIDs of the students will direct you to [Student’s Detail] page

**Managing Industry Placements 
**1) [CO-OP] header menu > [Add Sponsor]

2) When all the available sponsors are added, go to [Send Preference Form]
3) Type in the IP level with some information to the students
4) Click [ADD] to send an online form to students 
5) Students won’t have to login to the system to fill in their preferences
6) They would be given a link, on their email
7) When they submit their choices, it will be added to the database
8) Using this information, the co-op coordinator could update the industry   
   placement by [MENU] > [STUDENTS LIST] > [IP1~3] > [UPDATE].

* Repo owner or admin
* Other community or team contact