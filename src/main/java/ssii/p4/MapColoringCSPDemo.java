package ssii.p4;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.MinConflictsStrategy;
import aima.core.search.csp.SolutionStrategy;

/**
 * Demonstrates the performance of different constraint solving strategies. The map coloring problem
 * from the textbook is used as CSP.
 *
 * @author Ruediger Lunde
 */

public class MapColoringCSPDemo {
  /**
   * Entrada para ejecutar las pruebas
   *
   * @param args parámetros opcionales de entrada al ser invocado éste método
   */
  @SuppressWarnings("nls")
  public static void main(final String[] args) {
    final CSP csp = new MapCSP();
    final StepCounter stepCounter = new StepCounter();
    SolutionStrategy solver;

    solver = new MinConflictsStrategy(1000);
    solver.addCSPStateListener(stepCounter);
    stepCounter.reset();
    System.out.println("Map Coloring (Minimum Conflicts)");
    System.out.println(solver.solve(csp.copyDomains()));
    System.out.println(stepCounter.getResults() + "\n");

    solver = new ImprovedBacktrackingStrategy(true, true, true, true);
    solver.addCSPStateListener(stepCounter);
    stepCounter.reset();
    System.out.println("Map Coloring (Backtracking + MRV + DEG + AC3 + LCV)");
    System.out.println(solver.solve(csp.copyDomains()));
    System.out.println(stepCounter.getResults() + "\n");

    solver = new BacktrackingStrategy();
    solver.addCSPStateListener(stepCounter);
    stepCounter.reset();
    System.out.println("Map Coloring (Backtracking)");
    System.out.println(solver.solve(csp.copyDomains()));
    System.out.println(stepCounter.getResults() + "\n");
  }

  /** Counts assignment and domain changes during CSP solving. */
  protected static class StepCounter implements CSPStateListener {
    private int assignmentCount = 0;
    private int domainCount = 0;

    @Override
    public void stateChanged(final Assignment assignment, final CSP csp) {
      ++assignmentCount;
    }

    @Override
    public void stateChanged(final CSP csp) {
      ++domainCount;
    }

    /**
     * Resetea las asignaciones y el dominio.
     */
    public void reset() {
      assignmentCount = 0;
      domainCount = 0;
    }

    /**
     * Obtiene los cambios realizados en el dominio y en las asignaciones
     *
     * @return una cadena de texto que informa de los cambios realizados en las asignaciones y el
     *         dominio.
     */
    @SuppressWarnings("nls")
    public String getResults() {
      final StringBuffer result = new StringBuffer();
      result.append("assignment changes: " + assignmentCount);
      if (domainCount != 0) {
        result.append(", domain changes: " + domainCount);
      }
      return result.toString();
    }
  }
}
