[![Build Status](https://github.com/CycloneDX/sbom-commons/workflows/Maven%20CI/badge.svg)](https://github.com/CycloneDX/sbom-commons/actions?workflow=Maven+CI)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.cyclonedx.contrib.com.lmco.efoss.sbom/sbom-commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.cyclonedx.contrib.com.lmco.efoss.sbom/sbom-commons)
[![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)][License]
[![Website](https://img.shields.io/badge/https://-cyclonedx.org-blue.svg)](https://cyclonedx.org/)
[![Slack Invite](https://img.shields.io/badge/Slack-Join-blue?logo=slack&labelColor=393939)](https://cyclonedx.org/slack/invite)
[![Group Discussion](https://img.shields.io/badge/discussion-groups.io-blue.svg)](https://groups.io/g/CycloneDX)
[![Twitter](https://img.shields.io/twitter/url/http/shields.io.svg?style=social&label=Follow)](https://twitter.com/CycloneDX_Spec)

# sbom-commons
Lockheed Martin developed common SBOM library

## Overview/Description of Project
This project contains common classes used by the various components of the software bill of materials ([SBOM](https://gitlab.us.lmco.com/software-factory/sbom/documentation/-/blob/master/overview.md)) services and applications. The SbomCommons project is not intended as a standalone application, this project was written with test driven development to validate the project's methods and classes.  Additions should have paired JUnit tests.  

## Prerequisites
- Open JDK11
- Apache Maven 3.6.3 or greater installed 
- (Recommended) java IDE Eclipse with Subclipse 4.3.0 plug-in

## Docs
### Usage
#### How to Install/Setup Project
##### Local Install
- Clone this git repository 
- Compile first time with the Maven Command `mvn clean install`. The project is not intended as a standalone application, but instead to hold commonalities between the SBOM projects, such as the Date utility. The project was written as a test driven development to validate the methods and classes in this project and additions should have paired JUnit tests. Tests can be verified through the Maven Command `mvn clean test`.

##### Add to your existing Maven Project
With maven configured to connect to [nexus.us.lmco.com](https://nexus.us.lmco.com/) you can add following dependency to your pom.xml (note you will have to edit `TAG-Version`)
```
		<dependency>
			<groupId>org.cyclonedx.contrib.com.lmco.efoss.sbom</groupId>
			<artifactId>sbom-commons</artifactId>
			<version>TAG-Version</version>
		</dependency>
```
After the dependency is added to your pom you can reference the classes found in this project by adding the import, `import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.*`, to your java class.

#### How to Run/Use This Project
The project is not intended as a standalone application, the JUnit tests can be verified through the Maven Command `mvn clean test`.


Copyright & License
-------------------

CycloneDX SBOM Commons is Copyright (c) Lockheed Martin Corporation. All Rights Reserved.

Permission to modify and redistribute is granted under the terms of the Apache 2.0 license. See the [License] file for the full license.

[License]: https://github.com/CycloneDX/sbom-commons/blob/master/LICENSE
