/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

/*
This class aims to solve the Minimum Makespan problem, given an input file in which we have the execution times of the each job for each machine.

Example of execution:
java Exe u_s_hihi_512_16.txt

*/

public class Exe
{
  public static void main(String args[]) throws Exception
  {
  
    if(args.length!=1) {
      System.out.println("Please enter the name of the input_file.");
      return;
    }


    // PARAMETERS MINMAKESPAN
    String input_file = args[0];                           //name of the file containing data of the problem
    int numberOfMachines = 16;                        //Number of machines
    int    gn         = 512;                          // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = 512;                          // Population size
    double pc         = 0.8;                          // Crossover probability
    double pm  = 1.0/(double)((double)gn*(double)gl); // Mutation probability
    double tf         = 0.0 ;                         // Target fitness being sought
    long   MAX_ISTEPS = 50000;
    
    
   /*     
    if(args!=null && args[0]!=null)
      input_file = args[0];  
    else {
      System.out.println("Please enter java Exe input_file [popsize pc pm]");
      return;
    }
    */
    

    Problem   problem;                             // The problem being solved

    problem = new ProblemMinMakeSpan(input_file);

    problem.set_geneN(gn);
    problem.set_geneL(gl);
    problem.set_target_fitness(tf);



    Algorithm ga;          // The ssGA being used
    ga = new Algorithm(problem, popsize, gn, gl, pc, pm, numberOfMachines);


    for (int step=0; step<MAX_ISTEPS; step++)
    {
      ga.go_one_step();
      System.out.print(step); System.out.print("  ");
      System.out.println(Math.abs(ga.get_bestf()));

      if(     (problem.tf_known())                    &&
      (ga.get_solution()).get_fitness()>=problem.get_target_fitness()
      )
      { System.out.print("Solution Found! After ");
        System.out.print(problem.get_fitness_counter());
        System.out.println(" evaluations");
        break;
      }

    }

    // Print the solution
    for(int i=0;i<gn*gl;i++)
      System.out.print((ga.get_solution()).get_allele(i));
    System.out.println("\n"+Math.abs((ga.get_solution()).get_fitness()));
  }

}
// END OF CLASS: Exe
