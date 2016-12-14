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
    testSample(512,0.7,(1/512),args[0]);
    testSample(512,0.8,(1/512),args[0]);
    testSample(512,0.9,(1/512),args[0]);
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
  
  
  public static void testSample(int popsize1, double pc1, double pm1, String name_file_test) throws Exception {
    String file_name = "test/values_test_"+popsize1+"_"+pc1+"_"+pm1+".csv";
    
    PrintWriter out = new PrintWriter(new FileWriter(file_name));
    out.print("Iteration, Minimum MakeSpan\n");
  
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

    for (int iteration=0; iteration<50; iteration++) {
      for (int step=0; step<MAX_ISTEPS; step++)
      {
        ga.go_one_step();
        System.out.print(step); System.out.print("  ");
        System.out.println(ga.get_bestf());

        if(     (problem.tf_known())                    &&
        (ga.get_solution()).get_fitness()>=problem.get_target_fitness()
        )
        { System.out.print("Solution Found! After ");
          System.out.print(problem.get_fitness_counter());
          System.out.println(" evaluations");
          break;
        }

      }

      //PRINT SOLUTION
      out.print(iteration+","+(ga.get_solution()).get_fitness()+"\n");
      

    }
    out.flush();
    out.close();
      
  }

}
// END OF CLASS: Exe