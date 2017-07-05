# #Chicken

#### *Batch:* 1705May15Java
#### *Developers:* Cody Boucher, Theresa Dinh, Richard Marquez, Darrin McIntyre

---

- [Scrum board](https://trello.com/b/A7eJqRgx/chickentalkchat)
- [Burndown chart](https://app.screenful.me/dashboard/22931/chickentalk-chat)
- [Slack](https://1705may15java.slack.com/messages/C5UEUHBU2/)
- [Travis CI](https://travis-ci.org/richard92m/Chicken/branches)
- [SonarQube](https://sonarcloud.io/dashboard?id=chat.chickentalk%3AChicken)
- [EC2 (chickentalk.chat)](https://chickentalk.chat:8443)

---

## A social experiment...
\#Chicken is an online community space where users compete for time on a stage. 

The stage holds a 1-on-1 conversation in front of an audience that may chat among themselves. After a set period of time the audience votes on who they think should remain on the stage. One user gets voted off the stage and another is pulled at random from the audience.

---

## Multi-tiered web application leveraging:
- HTML5, CSS3 w/ SASS
- JSPs, JavaScript, jQuery, AngularJS, AJAX
- WebRTC, Temasys Skylink, WebSockets
- Java 1.8, Spring (MVC, Security, SpEL), Hibernate, Jackson
- S3, OracleDB on RDS
- Tomcat on EC2

---

## Getting up and running
- Clone from GitHub
- Define environment variables
  - `CHICKEN_EMAIL_ADDRESS`
  - `CHICKEN_EMAIL_PASSWORD`
  - `CHICKEN_DB_URL`
  - `CHICKEN_DB_USERNAME`
  - `CHICKEN_DB_PASSWORD`
  - `CHICKEN_AWS_ACCESS_KEY_ID`
  - `CHICKEN_AWS_SECRET_ACCESS_KEY`
  - `CHICKEN_BUCKET_NAME`
- Configure Tomcat for SSL
  - `keytool -genkey -alias tomcat -keyalg RSA -keystore $CATALINA_HOME\conf\keystore`
  - Uncomment SSL configuration in `conf/server.xml`; point to `keystore`
- Add `src/main/webapp/WEB-INF` to build path
- Install `webRtcKeys.js` into `WEB-INF/static/js`
- Access locally at [https://localhost:8443](https://localhost:8443)

---

## Git workflow
[Video tutorial](https://www.youtube.com/watch?v=oFYyTZwMyAg&spfreload=10)

1. `git pull`: Get most recent version of `master`
1. `git branch newFeature`: Create a branch for your new functionality 
1. `git checkout newFeature`: Switch to newly created branch
1. Work on new feature
1. `git add .`
1. `git commit -am "descriptive commit msg"`: Add and commit all changes *often*
1. Return to Step#4 until feature completed
1. `git checkout master`
1. `git pull`: Grab any changes to `master` since your modifications
1. `git checkout newFeature`
1. `git merge master -m 'merged master into newFeature`: Take all the changes from master and try to merge them back into `newFeature`
1. Resolve any conflicts, then:
   1. `git add .`
   1. `git commit -am "descriptive commit msg"`
1. `git push`: Send branch to GitHub
1. Create pull request on GitHub
1. Return to Step#1

