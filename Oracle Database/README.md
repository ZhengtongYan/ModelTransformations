# Model Transformations

This works only on [Oracle 19c Virtual Machine](https://www.oracle.com/downloads/community/vts-hands-on-labs-downloads.html) or in fully licensed Oracle database. Since graphs are not supported by Oracle Cloud Infrastucture, the code will not work properly if user tries to connect it to the cloud services. The data that these files support is [UniBench](https://github.com/HY-UDBMS/UniBench).

- OracleUnibenchLoader
    - This folder contains java program written with Eclipse. User can open it to Eclipse and run it. Before running the java code, you need to add jar-files from location **/u01/app/oracle/product/version/db_1/md/property_graph/lib** and jar-files from location **/u01/app/oracle/product/version/db_1/jdbc/lib** to the building path, for example, in Eclipse.
- SQLCreateUnibenchSchema
    - This folder contains a collection of simple text files that have SQL commands for creating the schema for Unibench data.
- SQLLoaderControlFiles
    - When, for example, raw csv formatted data is uploaded to Oracle database, this process is handled with loader control files. This folder contains those files for UniBench data.
