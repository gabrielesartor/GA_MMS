/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

/*
Intelligent Systems - Problem Solving project
Authors:  Gabriele Sartor
          Morgan Gautherot 
*/

/*
This class create files csv containing results useful for probabilistic analysis.
This class is implemented to run 50 iteration of the algorithm for each combination of parameter, writing on a csv file the best result of each iteration.
(It takes a lot of time...)

Example of execution
java testValues u_c_hihi_512_16.txt
*/


import java.io.*;

public class testValues
{
  public static void main(String args[]) throws Exception
  {
  
    if(args.length!=1) {
      System.out.println("Please enter the name of the input_file.");
      return;
    }


    //Execute test changing probability of crossover, mutation and population size
    double j = 1;

    for(double pc=0.70; pc<0.905 ; pc += 0.1 )
    {
      for(int pm=0; pm<11 ; pm += 2)
      {
        for(int popsize=256; popsize<2049 ; popsize *= 2)
        {
          String file_name = "pmTest_"+popsize+"_"+pc+"_"+pm+".csv";
          PrintWriter out = new PrintWriter(new FileWriter(file_name));
          out.print("Iteration, Minimum MakeSpan\n");

          for (int iteration=0; iteration<50; iteration++) {



            double soluce = testSample(popsize,pc,(pm/512), args[0]);

            out.print(iteration+","+soluce+"\n");


            System.out.println(iteration+","+soluce);
          }
          out.flush();
          out.close();
        }
      }
    }


  }


  public static double testSample(int popsize1, double pc1, double pm1, String input_file) throws Exception {


    // PARAMETERS MINMAKESPAN
    int numberOfMachines = 16;                        // Number of machines
    int    gn         = 512;                          // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = popsize1;                     // Population size
    double pc         = pc1;                          // Crossover probability
    double pm         = pm1;                          // Mutation probability
    double tf         = 0.0 ;                         // Target fitness being sought
    long   MAX_ISTEPS = 50000;

    Problem   problem;                                // The problem being solved

    problem = new ProblemMinMakeSpan(input_file);

    problem.set_geneN(gn);
    problem.set_geneL(gl);
    problem.set_target_fitness(tf);



    Algorithm ga;          
    ga = new Algorithm(problem, popsize, gn, gl, pc, pm, numberOfMachines);


      for (int step=0; step<MAX_ISTEPS; step++)
      {
        ga.go_one_step();


        if(     (problem.tf_known())                    &&
        (ga.get_solution()).get_fitness()>=problem.get_target_fitness()
        )
        { System.out.print("Solution Found! After ");
          System.out.print(problem.get_fitness_counter());
          System.out.println(" evaluations");
          break;
        }

    }
    return Math.abs((ga.get_solution()).get_fitness());

  }

}
// END OF CLASS: Exe
