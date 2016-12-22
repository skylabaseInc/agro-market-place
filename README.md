# Agro Market Place

Agro Marketplace is a platform that connects farmers and buyers of agricultural produts.
It is still under development.

# Development Environment Setup

## Prequisists

* MongoDB
* JAVA 8
* Maven

## Procedure

Fork the repo to your own profile

* clone

` git clone https://github.com/{your user name}/agro-market-place.git`

* add a remote repo to

` git remote add upstream https://github.com/ivange94/agro-market-place.git`

* fetch all branches

`git fetch --all`

To fetch a particular branch do `git fetch branch_name`

* enter development branch

` git checkout develop`

* pull latest changes

`git pull --rebase upstream develop`

 * make changes and submit pull request to the develop branch. The master branch will
 only hold stable released versions
 
 ## Database setup
 
 * Login to mysql and create 'marketplace' database
 * open application.properties and update mysql root password. Or create another user with all necessary privileges and
 update the username and password in the application.properties file
 
 ## Build and execute
 
` mvn package && java -jar target/agro-market-place-0.1.0-SNAPSHOT.jar`

## Run as a spring-boot application
Instead of packaging and running the jar, You could just
run as a spring boot application. To do that, from within the root directory run 
`mvn spring-boot:run`

This will startup an embedded tomocat server. Access the swagger docs here https://localhost:8080/swagger-ui.html
 

 
