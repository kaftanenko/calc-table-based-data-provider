
# Calc Table (based) Data Provider

**Content**

* [Introduction](#introduction)
* [Examples](#examples)
    * [Sample Data](#sample-data)
    * [Portrait format representation](#portrait-format-representation)
    * [Landscape format representation](#landscape-format-representation)
* [Application fields](#application-fields)
    * [Dictionaries](#dictionaries)
    * [Test data sets](#test-data-sets)
    * [Test case scenarios](#test-case-scenarios)
* [Vision and conclusion](#vision-and-conclusion)

## Introduction

The Calc Table (based) Data Provider is an open source framework for developing of parsers for reading complex data sets from calc table documents and provide it for further use within a concrete applications.

Actually supported is the only Java language, but support for further languages is possible and can be implemented on demand. At least support for the Python language is in plan.

One of the ground framework features is its high customization possibility. Such customization comprise actually:
* integration of the target project specific:
    * value types
    * value notation formats (planned)
* translation rules for attribute description titles into the target attribute names
* customizable data areas mark rules, such as structure description and content area

... and so on.

The Framework supports two base data representation formats within the calc tables: 
* landscape
* portrait

The ground framework features can be best demonstrated based on the Examples sub-project.

## Examples

Note: here will be given only an overview of the Examples sub-project, for further details please look [here](./java/examples/).

### Sample Data

Further examples demonstrate source calc table data represantation of personal data consisting of two data records and will be read through the framework as the following data sets (here representing in the JSON-Notation): 

```json
[
  {
    "personalData": {
      "sSN": "006-32-9914",
      "firstName": "David R.",
      "lastName": "Brown",
      "birthDate": "1971-01-13",
      "gender": "M"
    },
    "contactData": {
      "postalAddresses": [
        {
          "kind": "HOME",
          "street2": null,
          "street1": "4972 Main Street",
          "city": "Seattle",
          "state": "Washington (WA)",
          "zip": 98109,
          "country": "US"
        },
        {
          "kind": "WORK",
          "street2": "Sun Shine Co.",
          "street1": "222 5th Ave N",
          "city": "Seattle",
          "state": "Washington (WA)",
          "zip": 98109,
          "country": "US"
        }
      ],
      "phones": [
        {
          "kind": "HOME",
          "number": "+1 425-654-5440"
        },
        {
          "kind": "MOBILE",
          "number": "+1 206-356-6647"
        },
        {
          "kind": "WORK",
          "number": "+1 206-443-9275"
        }
      ],
      "emailAddresses": [
        {
          "kind": "PRIVATE",
          "address": "david.brown@aol.com"
        },
        {
          "kind": "WORK",
          "address": "david.brown@sun-shine.com"
        }
      ]
    }
  },
  {
    "personalData": {
      "sSN": "610-16-5934",
      "firstName": "Jennifer J.",
      "lastName": "Taveras",
      "birthDate": "1974-02-09",
      "gender": "F"
    },
    "contactData": {
      "postalAddresses": [
        {
          "kind": "HOME",
          "street2": null,
          "street1": "6199 S Virginia St",
          "city": "Reno",
          "state": "Nevada (NV)",
          "zip": 89502,
          "country": "US"
        },
        {
          "kind": "WORK",
          "street2": "Thunder Inc.",
          "street1": "2001 E. Plumb Lane",
          "city": "Reno",
          "state": "Nevada (NV)",
          "zip": 89510,
          "country": "US"
        }
      ],
      "phones": [
        {
          "kind": "HOME",
          "number": "+1 530-986-8096"
        },
        {
          "kind": "MOBILE",
          "number": "+1 510-552-0541"
        },
        {
          "kind": "WORK",
          "number": "+1 520-365-7412"
        }
      ],
      "emailAddresses": [
        {
          "kind": "PRIVATE",
          "address": "taveras1974@gmail.com"
        },
        {
          "kind": "WORK",
          "address": "j.taveras@thunder.com"
        }
      ]
    }
  }
]
```

### Landscape format representation

The calc table leaf in the landscape format consist of the two following areas:
* header area describing data sets structure
* content area with data sets values

The header area mark sign is (in this case) its consistent not-transparent and none-white background:

![Example: person data in landscape format](./doc/assets/images/example__person-data_in_landscape-format.png)

The original Excel file could be found under: [java/examples/test-data/src/test/resources/test-data-sources/Example_TestDataSets_PersonData_in_LandscapeFormat.xlsx](./java/examples/test-data/src/test/resources/test-data-sources/)

### Portrait format representation

The calc table leaf in the portrait format consist of the three following areas:
* header area with the free choosable cell textes
* data set structure description area, bounded through the columns span of the first header cell
* data content area bounded through the rest header cells (from the second upto the last one)

The header area mark sign is (in this case) also its consistent not-transparent and none-white background:

![Example: person data in portrait format](./doc/assets/images/example__person-data_in_portrait-format.png)

The original Excel file could be found under: [java/examples/test-data/src/test/resources/test-data-sources/Example_TestDataSets_PersonData_in_PortraitFormat.xlsx](./java/examples/test-data/src/test/resources/test-data-sources/)

## Application fields

The calc tables are still a favorite tool for very many technicians just like for not so-technical stamped professionals. It is a very appropriate form for data representation of any kind. 

In many cases it could be very benefiting to enabling automatically consuming of the calc table data avoiding its manually translating into the other programmmatically processable forms, such as data base records, SQL scripts, source code and so on.

The most benefits of such one approach are:
* the responsibility for the data content specification and (!) realization shifts to the domain area professionals
* error correction during the development time simplifies up to:
    * domain specialist corrects specification documents
    * developer generates corrected artefacts corresponding 1:1 to the domain specialist expectation
* minimization of the error risk by translating of the data specification into its technical representation form

Especially unbeatable is the approach regarding project consistency if the same information peaces from the specification documents results in many different artefacts.

### Dictionaries

One of the application fields for the framework could be consuming of the in the calc table form defined dictionary data.

The read data could be further used for updating of the corresponding data bases either directly or for generating update-scripts for the later applying at deployment time.

An representative application example can be found under [java/examples/dict](./java/examples/dict/).

### Test data sets

It is always benefitable to let the domain area specialist to specify synthetic test data stands with pre-defined domain area specific features and dependencies. Such data sets could be applied either by manual or automated tests.

The framework allows the domain area specialists to specify the test data using comfortably one of theirs favorite tool and allows the developers othersides to integrate the test data 1:1 into the further test routines, for example:
* to mock some componenents
* to import the data into the test data base
* to reset the system under test in one of a pre-defined states

The most benefit of such one approach insists in the repeatably applicability of the test data sets within the development process and in existens of stable consistent test data description documents that can be additively improved and adjust on the further project changes.

An representative application example can be found under [java/examples/test-data](./java/examples/test-data/).

### Test case scenarios

The next level of improvent relatively to the test data sets could be description of complete test use cases within the calc tables consisting of the following three data peaces:
* initial data stand and/or (service under test) input parameters
* expected result data stand and/or output parameters
* expected error and/or warning messages

It is thinkable to design project specific test case description documents and using them within the automated tests.

An representative application example can be found under [java/examples/test-scenarios](./java/examples/test-scenarios/).

# Vision and conclusion

The next planned development steps embrace:

* introduction of object references within the calc tables
* improvement of customization features, such as:
    * customization of the notation form for collection typed nodes
    * configuration files for non-programmatical customization of some basic features
    * customization of the project-wide consistent regional parameters (date/
    number formats etc.)
* support for the Map data types
* port to other programming languages, at least for the Python

It's also thinkable to develop specialized calc table based data providers for:

- use in Gherkin-notation based frameworks ([Cucumber](https://cucumber.io), [Robot](https://robotframework.org/) etc.)

That's all folks. I would very appriciated for the further ideas about possible framework improvements and its new application fields.

Thanks a lot in advance!
