package ch.stephan.playground.ejb;

import java.util.Properties;
import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;

public class RemoteClient {

  private static final String URL = "http-remoting://localhost:8080";
  private static final String USER_ID_AND_PASSWORD = "admin";
//  private static final String USER_ID_AND_PASSWORD = "misterXY";

  public static void main(String[] args) throws Exception {
    System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");

    Properties props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
    props.put(Context.PROVIDER_URL, URL);
    props.put(Context.SECURITY_PRINCIPAL, USER_ID_AND_PASSWORD);
    props.put(Context.SECURITY_CREDENTIALS, USER_ID_AND_PASSWORD);
    Context context = new InitialContext(props);
    System.out.println("Successfully created context " + context);

    SecuredEJBRemote reference = (SecuredEJBRemote) context.lookup("ejb:/my-ejb-security/SecuredEJB!" + SecuredEJBRemote.class.getName());
    System.out.println("Successfully called secured bean, caller principal " + reference.getSecurityInfo());

    try {
      boolean hasAdminPermission = reference.administrativeMethod();
      System.out.println("\nPrincipal has admin permission: " + hasAdminPermission);
    } catch (EJBAccessException ex) {
      System.out.println("\nPrincipal has admin permission: false");
    }
    
    System.out.println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");
  }

}
