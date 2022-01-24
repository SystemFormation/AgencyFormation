

# Agency Formation
Agency Formation provides human resources with all the tools to hire a candidate, to provide training material to an employee and the appropriate tools to view the teams that are working on projects. For team managers, the system offers tools to create teams with relative specifics of the necessary skills, manage the teams created and finally has the possibility to dissolve it at any time the TM deems it necessary. For employees, Agency Formation allows you to view training material, view your profile, with the ability to add and view your skills.

## Authors
* **Armando Conte** - *Project Manager* - [Armando Conte](https://github.com/armandoC27)
* **Vincenzo Pagliaro** - *Project Manager* - [Vincenzo Pagliaro](https://github.com/chuck2098)
* **Gennaro Cecco** - *Developer* - [Gennaro Gecco](https://github.com/GennaroCecco)
* **Gianpaolo Laurenzano** - *Developer* - [Gianpaolo Laurenzano](https://github.com/PauLaure)
* **Manuel Nocerino** - *Developer* - [Manuel Nocerino](https://github.com/Megalilde)
* **Domenico Pagliuca** - *Developer* - [Domenico Pagliuca](https://github.com/DomenicoPagliuca)
* **Luigi Giacchetti** - *Developer* - [Luigi Giacchetti](https://github.com/Rankoll)
* **Emanuele Scarpa** - *Developer* - [Emanuele Scarpa](https://github.com/ManuScarpa)
* **Pasquale Severino** - *Developer* - [Pasquale Severino](https://github.com/PiEsse11)

## Documentation
* Project java doc can be found in *doc* directory.
* Jacoco test report can be found in *doc* directory.
* All other documents can be found in *docProject* directory.

## Installation
All installation steps are written in AF_MDI_v1.1.docx

## Clone and run the project in localhost
You can follow these steps or installation manual.
1. Clone this repo: `git clone https://github.com/SystemFormation/AgencyFormation.git`
2. Run `gradlew war` and wait for grandle build.
3. Inside directory build/libs you can find .war file.
4. Deploy .war file on your java server(ex. Tomcat).
5. Import database and populate it with the script that you can find in **folder AF_DB**.
6. Open your browser on `localhost:8080\AgencyFormation`
7. Login with ["d.pagliuca@studenti.unisa.it"](mailto:d.pagliuca@studenti.unisa.it) password: **"lol"**  for Human Resource or with ["m.nocerino@studenti.unisa.it"](mailto:m.nocerino5@studenti.unisa.it) password: **"lol"** for Team Manager.

## Working with the project
* Master branch contains release-ready only features.
* Develop branch contains multiple stable features.


### Get new data from my branch
If you and another *developer* are working on the same feature branch but you want to get the changes he pushed:

1. Make sure you are in YOUR (already existing) feature branch. If not: `git checkout development`
2. `git pull`
3. Resolve possible conflicts. Ask if you are in trouble. A common solution is:
* `git stash` to save your local changes in a local secure stack
* `git pull`
* `git stash pop` to reapply your local changes again

### How to write a good commit message
Follow this: `https://chris.beams.io/posts/git-commit/` , especially points 2, 3, 4, 5.


