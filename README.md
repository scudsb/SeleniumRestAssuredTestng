https://github.com/RoboticAutomata/selenium-restassured-testing-example

API & UI Testing Example with Restassured, Selenium and TestNG
This repo demonstrates how to do Test Automation using Java, TestNg & Maven.

For the API Test Automation we use Restassured
For the UI Test Automation we use Selenium
Table of Contents

Application under Test
Example Scenario to Automate
Login Page
Add User Page
Contact List
Exactly what APIs are we testing?
Add User
Login User
Logout User
Delete User
Repository Structure
Run the tests
Run the API Tests
Run the UI Tests
Resources
API Tutorials
UI Tutorials
Application under Test
We will be testing the Thinking Tester Contact List App (CLA). The CLA enables a user to create an account to manage contacts.

The CLA API Documentation can be found here.

Example Scenario to Automate
Our test covers the specific workflow of opening the login page, creating a new user and logging out.

Login Page
Login Page

Add User Page
Add User Page

Contact List
Contact List Page

Exactly what APIs are we testing?
In case Thinking App goes down or the API is updated, here is the gist of the current APIs tested at the time of writing.

Add User
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users

Request Body ->
{
    "firstName": "Test",
    "lastName": "User",
    "email": "test@fake.com",
    "password": "myPassword"
}

Response Status ->
201
Login User
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users/login

Request Body ->
{
    "email": "test2@fake.com",
    "password": "myNewPassword"
}

Response Status ->
200

Response Body ->
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MDgyMWYzMDYyZmJiMjEzZTJhZDlhMjAiLCJpYXQiOjE2MTk3M
}
Logout User
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users/logout

Header ->
Authorization: Bearer $token

Response Status ->
200
Delete User
DELETE Request ->
https://thinking-tester-contact-list.herokuapp.com/users/me

Header ->
Authorization: Bearer $token

Response Status ->
200
Repository Structure
src/test/java/ThinkTester/ContactListApp
├── Apis
│   └── UserApi.java #Contains the User API Endpoints & Request Specifications
├── Pages #Page objects for the UI
│   ├── AddUserPage.java
│   ├── BasePage.java
│   ├── ContactListPage.java
│   └── LoginPage.java
├── Tests
│   ├── ApiTests #Tests each API
│   │   └── UserApiTest.java
│   └── UiTests #Test cases for the UI
│       └── SmokeUiTest.java
└── Utils
    ├── Environment.java #Specifies the Base URI
    └── UserFactory.java #Creates data for a Random User
Run the tests
Run the API Tests
mvn clean verify -Dtest=*ApiTest
Run the UI Tests
mvn clean verify -Dtest=*UiTest
Resources
API Tutorials
Youtube Video Demo API
Medium Blog Tutorial API
UI Tutorials
Youtube Video Demo UI
Medium Blog Tutorial UI
