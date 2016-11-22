# Agro Market Place

Agro Marketplace is a platform that connects farmers and buyers of agricultural produts.
It is still under development.

# Setup Development Environment

## Prequisists

* MySQL 
* JAVA 8
* Maven

## Procedure

Fork the repo to your own profile

* clone

` git clone https://githlab.com/{your user name}/agro-market-place.git`

* add a remote repo to

` git remote add upstream https://githlab.com/labase/agro-market-place.git`

* enter development branch
` git checkout develop`

* fetch and pull latest changes

`git fetch --all`
`git pull --rebase upstream develop`

 * make changes and submit pull request to the develop branch. The master branch will
 only hold stable released versions
 
 ## Build and execute
 
` mvn package && java -jar target/agro-market-place-0.1.0-SNAPSHOT.jar`
 

 