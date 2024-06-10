# Data_Structures
 Hashing
 # CSC2001F Assignment A6 - Hashing

## Overview
This assignment focuses on the implementation and evaluation of linear probing, quadratic probing, and optimizing a hash function for a specific dataset. It involves using various hash table classes to store simple String data items and evaluating their performance.

## Framework
You'll work with several hash table classes provided:
- `HashTable`: Incomplete implementation of a hash table.
- `QPHashTable`: Incomplete implementation of a Quadratic Probing (QP) hash table.
- `LPHashTable`: Incomplete implementation of a Linear Probing (LP) hash table.

Additional classes include `AutoTest`, `LPAutoTest`, `QPAutoTest`, and `DataMaker`. `AutoTest`, `LPAutoTest`, and `QPAutoTest` help test your work, while `DataMaker` generates personalized test data.

## Design Principles
The `HashTable` class has methods for storing data items, inserting strings, and checking if a string exists. It relies on a general-purpose `findIndex()` function for these operations, which is implemented differently by subclasses. Performance evaluation involves counting probes during insertions and searches, achieved through the `Monitorable` superclass.

## Task One 
Complete the implementations of `LPHashTable` and `QPHashTable` classes:
- `LPHashTable`: Implement using linear probing technique.
- `QPHashTable`: Implement using quadratic probing technique.

Submission involves submitting completed classes to the automatic marker.

## Task Two 
Optimize the hash function for inserting a specific list of names in an `LPHashTable`. For every possible combination of weights, evaluate the number of probes required for insertion. Output the least number of probes required and the number of weight combinations achieving this.

### Submission
Submit to the automatic marker:
1. Your data file `mydata.txt`.
2. Your `Optimize.java` code.
3. A text file called `results.txt` with the least number of probes required to insert the contents of `mydata.txt` and the number of weight combinations.

## Instructions for DataMaker
1. Run `DataMaker.java` with your username and the number of usernames required.
   ```plaintext
   java DataMaker <username> <list length>

## Writer
Sakhile Eddie Mjiyakho (MJYSAK001)   


