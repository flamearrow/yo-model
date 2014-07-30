yo-model 
	defines the data types transferred between Android and server
	implements the serialization and deserialization logic between byte[] and data objects(under construction)

Checkout the avro schema at src/maim/avro/yo.avsc

to generate data objects from schema file:
	command line: mvn generate-sources
	eclipse:  run as-> maven Generate-sources

data objects will be generated at target/generated-sources/java/, in eclipse click `build path->Use as Source Folder` to add to build path 
