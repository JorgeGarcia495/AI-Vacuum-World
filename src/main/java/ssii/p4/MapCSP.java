package ssii.p4;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.NotEqualConstraint;
import aima.core.search.csp.Variable;


/**
 * Artificial Intelligence A Modern Approach (3rd Ed.): Figure 6.1, Page 204.<br>
 * <br>
 * The principal states and territories of Australia. Coloring this map can be viewed as a
 * constraint satisfaction problem (CSP). The goal is to assign colors to each region so that no
 * neighboring regions have the same color.
 *
 * @author Ruediger Lunde
 * @author Mike Stampone
 */
@SuppressWarnings("javadoc")
public class MapCSP extends CSP {
  public static final Variable NSW = new Variable("NSW"); //$NON-NLS-1$
  public static final Variable NT = new Variable("NT"); //$NON-NLS-1$
  public static final Variable Q = new Variable("Q"); //$NON-NLS-1$
  public static final Variable SA = new Variable("SA"); //$NON-NLS-1$
  public static final Variable T = new Variable("T"); //$NON-NLS-1$
  public static final Variable V = new Variable("V"); //$NON-NLS-1$
  public static final Variable WV = new Variable("WV"); //$NON-NLS-1$
  public static final Variable WA = new Variable("WA"); //$NON-NLS-1$
  /** Nuevo color naranjito añadido */
  public static final String NARANJITO = "ORANGE"; //$NON-NLS-1$
  public static final String RED = "RED"; //$NON-NLS-1$
  public static final String GREEN = "GREEN"; //$NON-NLS-1$
  public static final String BLUE = "BLUE"; //$NON-NLS-1$

  /**
   * Returns the principle states and territories of Australia as a list of variables.
   *
   * @return the principle states and territories of Australia as a list of variables.
   */
  private void collectVariables() {
    addVariable(NSW);
    addVariable(WA);
    addVariable(NT);
    addVariable(Q);
    addVariable(SA);
    addVariable(V);
    addVariable(T);
    // Añadida nueva región
    addVariable(WV);
  }

  /**
   * Constructs a map CSP for the principal states and territories of Australia, with the colors
   * Red, Green, and Blue.
   */
  public MapCSP() {
    collectVariables();

    // Añadido nuevo color al dominio.
    final Domain colors = new Domain(new Object[] {RED, GREEN, BLUE, NARANJITO});

    for (final Variable var : getVariables()) {
      setDomain(var, colors);
    }

    addConstraint(new NotEqualConstraint(WA, NT));
    addConstraint(new NotEqualConstraint(WA, SA));
    addConstraint(new NotEqualConstraint(NT, SA));
    addConstraint(new NotEqualConstraint(NT, Q));
    addConstraint(new NotEqualConstraint(SA, Q));
    addConstraint(new NotEqualConstraint(SA, NSW));
    addConstraint(new NotEqualConstraint(SA, V));
    addConstraint(new NotEqualConstraint(Q, NSW));
    addConstraint(new NotEqualConstraint(NSW, V));
    // Añadida restricción
    addConstraint(new NotEqualConstraint(WA, V));
  }
}
