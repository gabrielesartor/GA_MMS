/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

//To execute
//java testValues data_file_name
//
//Example
// java testValues u_c_hihi_512_16.txt

import java.io.*;

public class testValues
{
  public static void main(String args[]) throws Exception
  {

  //Changing probability of crossover
  double j = 0.7;
  while( j < 0.95)
  {
    String file_name = "test/"+args[0]+"_"+512+"_"+j+"_"+(1/512)+".csv";
    PrintWriter out = new PrintWriter(new FileWriter(file_name));
    out.print("Iteration, Minimum MakeSpan\n");

    for (int iteration=0; iteration<50; iteration++) {



      double soluce = testSample(512,j,(1/512));
      //PRINT SOLUTION
      out.print(iteration+","+soluce+"\n");


      System.out.println(iteration+","+soluce);
    }
    out.flush();
    out.close();

    j = j + 0.05;
  }


/*
    //Changing probability of mutation
    testSample(512,0.7,0,args[0]);
    testSample(512,0.7,0.01,args[0]);
    testSample(512,0.7,0.02,args[0]);
    testSample(512,0.7,0.03,args[0]);
    testSample(512,0.7,0.04,args[0]);
    testSample(512,0.7,0.05,args[0]);
    testSample(512,0.7,0.06,args[0]);
    testSample(512,0.7,0.07,args[0]);
    testSample(512,0.7,0.08,args[0]);
    testSample(512,0.7,0.09,args[0]);
    testSample(512,0.7,0.1,args[0]);

    //Changing population
    testSample(200,0.7,(1/512),args[0]);
    testSample(300,0.7,(1/512),args[0]);
    testSample(500,0.7,(1/512),args[0]);
    testSample(600,0.7,(1/512),args[0]);
    testSample(800,0.7,(1/512),args[0]);
    testSample(1000,0.7,(1/512),args[0]);
*/

  }


  public static double testSample(int popsize1, double pc1, double pm1) throws Exception {


    // PARAMETERS MINMAKESPAN
    int numberOfMachines = 16;                        // Number of machines
    int    gn         = 512;                          // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = popsize1;                          // Population size
    double pc         = pc1;                          // Crossover probability
    double pm         = pm1; // Mutation probability
    double tf         = 0.0 ;               // Target fitness being sought
    long   MAX_ISTEPS = 50000;

    Problem   problem;                             // The problem being solved

    problem = new ProblemMinMakeSpan();

    problem.set_geneN(gn);
    problem.set_geneL(gl);
    problem.set_target_fitness(tf);



    Algorithm ga;          // The ssGA being used
    ga = new Algorithm(problem, popsize, gn, gl, pc, pm, numberOfMachines);


      for (int step=0; step<MAX_ISTEPS; step++)
      {
        ga.go_one_step();
        //System.out.print(step); System.out.print("  ");
        //System.out.println(ga.get_bestf());

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
