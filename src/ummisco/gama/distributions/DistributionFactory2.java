package ummisco.gama.distributions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.StringTokenizer;

import umontreal.ssj.probdist.Distribution;
import umontreal.ssj.probdist.DistributionFactory;

public class DistributionFactory2 {


	public static Distribution  getDistribution(String str){
		// Extracts the name of the distribution.
		// If there is an open parenthesis, the name contains all the 
		// non-space characters preceeding it.If not,the name is the full string.

		int i = 0;
		str = str.trim();

		int idx = str.indexOf ('(', i);
		String distName;
		if (idx == -1)
			distName = str.substring (i).trim();
		else
			distName = str.substring (i, idx).trim();

		// Try to find the class in probdist package.
		Class<?> distClass;
		try {
			distClass = Class.forName ("ummisco.gama.distributions." 
					+ distName);
		}
		catch (ClassNotFoundException e) {
			// Look for a fully qualified classname whose constructor
			//  matches this string.
			try {
				 distClass = Class.forName ("umontreal.ssj.probdist." 
                         + distName);
				// We must check if the class implements Distribution 
				if (Distribution.class.isAssignableFrom(distClass) == false)
					throw new IllegalArgumentException 
					("The given class is not a Probdist distribution class.");
			}
			catch (ClassNotFoundException ex) {
				throw new IllegalArgumentException ("Invalid distribution name: " 
						+ distName);
			}
		}

		String paramStr = "";
		if (idx != -1) {
			// Get the parameters from the string.
			int parFrom = idx;
			int parTo = str.lastIndexOf (')');
			// paramStr will contain the parameters without parentheses.
			paramStr = str.substring (parFrom + 1, parTo).trim();
			if (paramStr.indexOf ('(') != -1 || paramStr.indexOf (')') != -1)
				//All params are numerical,so parenthesis nesting is forbidden here
				throw new IllegalArgumentException ("Invalid parameter string: " 
						+ paramStr);
		}

		if (paramStr.equals ("")) {
			// No parameter is given to the constructor.
			try {
				return (Distribution) distClass.newInstance();
			}
			catch (IllegalAccessException e) {
				throw new IllegalArgumentException 
				("Default parameters not available");
			}
			catch (InstantiationException e) {
				throw new IllegalArgumentException 
				("Default parameters not available");
			}
		}

		// Find the number of parameters and try to find a matching constructor.
		// Within probdist, there are no constructors with the same
		// number of arguments but with different types.
		// This simplifies the constructor selection scheme.
		StringTokenizer paramTok = new StringTokenizer (paramStr, ",");
		int nparams = paramTok.countTokens();
		Constructor[] cons = distClass.getConstructors();
		Constructor distCons = null;
		Class[] paramTypes = null;
		// Find a public constructor with the correct number of parameters.
		for (i = 0; i < cons.length; i++) {
			if (Modifier.isPublic (cons[i].getModifiers()) &&
					((paramTypes = cons[i].getParameterTypes()).length == nparams)) {
				distCons = cons[i];
				break;
			}
		}
		if (distCons == null)
			throw new IllegalArgumentException ("Invalid parameter number");

		// Create the parameters for the selected constructor.
		Object[] instParams = new Object[nparams];
		for (i = 0; i < nparams; i++) {
			String par = paramTok.nextToken().trim();
			try {
				// We only need a limited set of parameter types here.
				if (paramTypes[i] == int.class)
					instParams[i] = new Integer (par);
				else if (paramTypes[i] == long.class)
					instParams[i] = new Long (par);
				else if (paramTypes[i] == float.class) {
					if (par.equalsIgnoreCase ("infinity") || par.equalsIgnoreCase 
							("+infinity"))
						instParams[i] = new Float (Float.POSITIVE_INFINITY);
					else if (par.equalsIgnoreCase ("-infinity"))
						instParams[i] = new Float (Float.NEGATIVE_INFINITY);
					else
						instParams[i] = new Float (par);
				}
				else if (paramTypes[i] == double.class) {
					if (par.equalsIgnoreCase ("infinity") || par.equalsIgnoreCase
							("+infinity"))
						instParams[i] = new Double (Double.POSITIVE_INFINITY);
					else if (par.equalsIgnoreCase ("-infinity"))
						instParams[i] = new Double (Double.NEGATIVE_INFINITY);
					else
						instParams[i] = new Double (par);
				}
				else
					throw new IllegalArgumentException
					("Parameter " + (i+1) + " type " + paramTypes[i].getName() +
							"not supported");
			}
			catch (NumberFormatException e) {
				throw new IllegalArgumentException
				("Parameter " + (i+1) + " of type " +
						paramTypes[i].getName()+" could not be converted from String");
			}
		}

		// Try to instantiate the distribution class.
		try {
			return (Distribution) distCons.newInstance (instParams);
		}
		catch (IllegalAccessException e) {
			return null;
		}
		catch (InstantiationException e) {
			return null;
		}
		catch (InvocationTargetException e) {
			return null;
		}
	}
}
