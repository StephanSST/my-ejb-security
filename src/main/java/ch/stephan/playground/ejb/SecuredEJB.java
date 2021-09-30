package ch.stephan.playground.ejb;

import java.security.Principal;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "AuthenticatedRole" })
@SecurityDomain("baloise-domain")
public class SecuredEJB implements SecuredEJBRemote {

    @Resource
    private SessionContext ctx;

    public String getSecurityInfo() {
        Principal principal = ctx.getCallerPrincipal();
        return principal.getName();
    }

    @RolesAllowed("admin")
    public boolean administrativeMethod() {
        return true;
    }
}
