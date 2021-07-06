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
			<groupId>com.lmco.efoss.sbom</groupId>
			<artifactId>sbom-commons</artifactId>
			<version>TAG-Version</version>
		</dependency>
```
After the dependency is added to your pom you can reference the classes found in this project by adding the import, `import com.lmco.efoss.sbom.commons.*`, to your java class.

#### How to Run/Use This Project
The project is not intended as a standalone application, the JUnit tests can be verified through the Maven Command `mvn clean test`.


## License
[Licenses](./LICENSE) for this project.
