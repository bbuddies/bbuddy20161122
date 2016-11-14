[![Build Status](https://travis-ci.org/bbuddies/bbuddy20161122.svg?branch=bbuddy20161122)](https://travis-ci.org/bbuddies/bbuddy20161122) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=bbuddies:bbuddy20161122)](https://sonarqube.com/dashboard?id=bbuddies%3Abbuddy20161122) [![Issues](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/violations.svg)](https://sonarqube.com/component_issues/index?id=bbuddies%3Abbuddy20161122#resolved=false)

[![Overall Coverage](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/overall_coverage.svg)](https://sonarqube.com/component_measures/domain/Coverage?id=bbuddies%3Abbuddy20161122) [![UT Coverage](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/coverage.svg)](https://sonarqube.com/component_measures/domain/Coverage?id=bbuddies%3Abbuddy20161122) [![Function Complexity](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/function_complexity.svg)](https://sonarqube.com/component_measures/domain/Complexity?id=bbuddies%3Abbuddy20161122) [![Class Complexity](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/class_complexity.svg)](https://sonarqube.com/component_measures/domain/Complexity?id=bbuddies%3Abbuddy20161122) [![Duplications](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/duplicated_blocks.svg)](https://sonarqube.com/component_measures/domain/Duplications?id=bbuddies%3Abbuddy20161122) [![Code Smells](https://img.shields.io/sonar/http/sonarqube.com/bbuddies:bbuddy20161122/code_smells.svg)](https://sonarqube.com/component_measures/domain/Maintainability?id=bbuddies%3Abbuddy20161122)

This is the repo for the exercise project to be developed in Shanghai team's Modern Web Development course

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.

* git
* jdk 1.8
* gradle 2.13 (don't use gradle 2.14, 2.14.1 or 3.X)
* mysql
* intellij idea community edition with the following plug-in installed
    * lombok plug-in
* Firefox 46.0 (don't use higher version)

# Setup Command Line Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.

* Create a default mysql dba user `mysql -u root -p < src/main/sql/create_default_dba.sql`
* Start Web Application `gradle run` (on port 8090)
* Run All Tests `gradle ci` (on port 8080)
* if you want to start it on a port rather than 8090 (e.g. 8070), please do `gradle run -Pport=8070`

# Setup Intellij Development Environment

* Start Web Application. Run com.odde.bbuddy.Application as a Spring Boot application by using "dev" as the active profile
* Run Unit Tests. Run those unit tests as normal. The only limitation is that you can't run those "Nested" tests together with other non "Nested" tests
* Run Acceptance Tests (cucumber). In feature file, you can select the feature or one scenario and then run it. In the configuration, you need to set the active profile as "test" by adding `SPRING_PROFILES_ACTIVE=test` to the environment variables.
* [Spring Boot Developer Tools](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html) is used so that you can hot load any modified code, template file and resource file without restart the application. Please follow the steps below to enable this hot load feature.
    * Start the application in Intellij as described in the first item. Don't start it with gradle in command line.
    * Edit any code or file, and make the project. Then, the change will be reloaded automatically.
    * You can install a Chrome extension called [Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei?hl=en) so that the tab (in which this application is opened) will be refreshed automatically.

# Setup Development Environment using VM
Install the following tools first.

* [VirtualBox](https://www.virtualbox.org/)
* [Vagrant](https://www.vagrantup.com)
* [Ansible](https://www.ansible.com/)

Then run the command: `vagrant up`

# If you are using MacOS, and have [Homebrew](http://brew.sh/) installed.
Run the below commands:

    brew cask install virtualbox vagrant
    brew install ansible
    vagrant up
