# #Chicken

#### *Batch:* 1705May15Java
#### *Developers:* Cody Boucher, Theresa Dinh, Richard Marquez, Darrin McIntyre

### [User Stories](https://docs.google.com/document/d/1QqFmw7Qmrgp3Yy9gCnBTg-uOUbG4eKleCxOa1cK7P94/edit?usp=sharing)

## Pitch
\#Chicken is an online community space where users compete for time on a stage. 

The stage holds a 1-on-1 conversation in front of an audience that may chat among themselves. After a set period of time the audience votes on who they think should remain on the stage. One user gets voted off the stage and another is pulled at random from the audience.

## Git Workflow
[Video tutorial](https://www.youtube.com/watch?v=oFYyTZwMyAg&spfreload=10)

1. `git pull`: Get most recent version of `master`
1. `git branch newFeature`: Create a branch for your new functionality 
1. `git checkout newFeature`: Switch to newly created branch
1. Implement new feature
1. `git add .`
1. `git commit -am "descriptive commit msg"`: Add and commit all changes
1. `git checkout master`
1. `git pull`: Grab any changes to `master` since your modifications
1. `git checkout newFeature`
1. `git merge master`: Take all the changes from master and try to merge them back into `newFeature`
1. Resolve any conflicts, then:
   1. `git add .`
   1. `git commit -am "descriptive commit msg"`
1. `git push`: Send branch to GitHub
1. Create pull request on GitHub
1. Return to Step#1
