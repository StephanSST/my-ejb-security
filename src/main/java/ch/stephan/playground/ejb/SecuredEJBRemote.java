package ch.stephan.playground.ejb;

public interface SecuredEJBRemote {

    String getSecurityInfo();

    boolean administrativeMethod();

}
