#Minimum Makespan Problem solver

Implemented by 
  Gabriele Sartor and Morgan Gautherot 

This program aims to solve the Minimum Makespan problem.
In order to do this you have to run the file Exe.java giving as input the name of the file containing data about the time of execution of each job for each machine.

-----------Structure of the repository----------

1. doc : contain the report related to the project

2. src : folder containing java files and results of our test

---------------EXECUTION---------------

To execute the code, open the terminal in GA_MMS/src/ and run the command:
  java Exe data_file_name
  
Example of execution required by the project:

java Exe u_s_hihi_512_16.txt

java Exe u_s_lohi_512_16.txt

At the end of the execution it will display the values of the chromosome with the best fitness and its fitness.

---------------TEST RESULTS--------------

To look at the results of the algorithm with different parameters, you can use the class testValues.java.
This class is implemented to run 50 iteration of the algorithm for each combination of parameter, writing on a csv file the best result of each iteration.
(It takes a lot of time...)

To execute it, open the terminal in GA_MMS/src/ and run the command:
java testValues data_file_name

Example of execution

java testValues u_s_hihi_512_16.txt

java testValues u_s_lohi_512_16.txt

-----------------------------------------
