# Simple API made with Spring boot#

##An API made using Spring Boot using##
- H2 database
- JWT Authorization

###after running the application all urls can be accessed by adding 
"http://localhost:<port number mentioned in src/main/resources/application.properties>/"
before every endpoint mentioned below"

##JWT Authorization is required for all endpoints##
User must generate JWT token and attach it in the "Authorization" header,
and must have value set to "Bearer <your JWT token>".

to generate JWT token send POST request to api/authenticate endpoint.
with username and password attached in the JSON body.

example:
{
	"username":"user",
	"password":"password"
}



##The API has two primary endpoints to perform CRUD operations##
- api/department
- api/employee

##Available operations on api/department##
###1. GET api/department###
Gets all the records from the database.

###2. GET api/department/<Integer Id>###
Gets specific record from the database having Id mentioned in the url.

###3. POST api/department###
Allows us to add a new deparment record.
Accepts JSON body.

example: 
{
	"departmentName":"Accounts"
}

###4. PUT api/department/<Integer Id>####
Allows us to edit a pre existing department record having Id mentioned in the url.
Accepts JSON body, all fields are optional.

example: 
{
	"departmentName":"Quality Assurance"
}

###5. DELETE api/department/<Integer Id>###
Allows us to delete a pre existing department record having Id mentioned in the url.

##Available operations on api/employee##
###1.GET api/employee###
Gets all the records from the database.

###2.GET api/employee/<Integer Id>###
Gets specific record from the database having Id mentioned in the url.

###3. POST api/department###
Allows us to add a new employee record.
Accepts JSON body.

example: 
{
	"firstName":"Erin",
	"lastName":"Hannon",
	"title":"Receptionist",
	"departmentId":4,
	"email":"erin.hannon@gmail.com",
	"contact":"+1-123-456-7890"
}

###4. PUT api/department/<Integer Id>###
Allows us to edit a pre existing department record having Id mentioned in the url.
Accepts JSON body, all fields are optional.

example: 
{
	"firstName":"Michael",
	"lastName":"Klump",
	"title":"Receptionist",
	"departmentId":4,
	"email":"michael.klump@gmail.com",
	"contact":"+1-098-765-4321"
}

###5. DELETE api/department/<Integer Id>
Allows us to delete a pre existing department record having Id mentioned in the url.

##Security##
Every endpoint is secured using JWT authorization.
Generated JWT token contains information regarding the user that generates the said token.
Every user has roles associated with them the database contains information about two roles.
1. USER
2. ADMIN
more roles can be added, addition of roles is discussed later.

###USER role###
user role grants the ability to perform all the CRUD operations mentioned previously.

###ADMIN role###
admin role grants all the powers of USER role as well as the ability to manipulate user and security information in the database.

##Security operations##
All the operations mentioned below can be performed by user having and ADMIN role.
Available endpoints:
1. api/user - manipulaye user information in database
2. api/role - manipulate role information in database
3. api/role-data - manipulate user-role information in database

##Available operations on api/user##
###1. GET api/user###
Gets all the user records from the database.

###2. GET api/user/<Interge Id>###
Gets specific user record from database having Id mentioned in the url.

###3. POST api/user###
Allows us to add a new user record.
Accepts JSON body.

example:
{
        "username": "admin",
        "password": "password",
        "userNonExpired": true,
        "userNonLocked": true,
        "userCredentialsNonExpired": true,
        "userEnabled": true,
        "authorities":["role.USER", "role.ADMIN"]
}

###4. PUT api/user/<Integer Id>###
Allows us to edit a pre existing user record having Id mentioned in the url.
Accepts JSON body, all fields are optional.

example:
{
        "username": "user",
        "password": "password",
        "userNonExpired": true,
        "userNonLocked": true,
        "userCredentialsNonExpired": true,
        "userEnabled": true,
        "authorities":["role.USER"]
}

### DELETE api/user/<Interge Id>
Allows us to delete a pre existing user record having Id mentioned in the url.

##Available operations on api/role##
###1. GET api/role###
Gets all the role records from the database.

###2. GET api/role/<Interge Id>###
Gets specific role record from database having Id mentioned in the url.

###Note : Below operations modify the role information in the database.
After making changes using the operations shown below the api might not work as intended.
To adapt with these changes, the src/main/java/com/application/api/security/SecurtiyConfiguration.java file should be changed appropriately.

###3. POST api/role###
Allows us to add a new role record.
Accepts JSON body.

example:
{
        "role":"role.USER"
}

###4. PUT api/role/<Integer Id>###
Allows us to edit a pre existing role record having Id mentioned in the url.
Accepts JSON body, all fields are optional.

example:
{
        "role":"role.SOME_NEW_ROLE"
}

###5. DELETE api/role<Integer Id>###
Allows us to delete a pre existing role record having Id mentioned in the url.

##Available operations on api/role-data##
###1. GET api/role-data###
Gets all the role-data records from the database.

###2. GET api/role-data/<Interge Id>###
Gets specific role-data record from database having Id mentioned in the url.

###3. POST api/role-data###
Allows us to add a new role-data record.
Accepts JSON body.

example:
{
	"userId":2,
	"roleId":1
}

###4. PUT api/role-data/<Integer Id>###
Allows us to edit a pre existing role-data record having Id mentioned in the url.
Accepts JSON body, all fields are optional.

example:
{
	"userId":2,
	"roleId":2
}

###5. DELETE api/role-data<Integer Id>###
Allows us to delete a pre existing role-data record having Id mentioned in the url.

##H2-Database##
the database configuration is available in src/main/resources/application.properties file.
Database console is enabled in the said file and can be view in the browser by accessing the url "/h2-console".
The console is not secured, to secure the console endpoint 
the line .antMatchers("/h2-console/**).permitAll();
should be removed or changed to .antMatchers("/h2-console/**).authenticated();
these changes are to be made in src/main/java/com/application/api/security/SecurtiyConfiguration.java file.
