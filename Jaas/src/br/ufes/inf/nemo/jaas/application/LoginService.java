package br.ufes.inf.nemo.jaas.application;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.jaas.domain.User_;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;



public class LoginService  implements LoginModule {

	
	private static final Logger logger = Logger.getLogger(LoginService.class.getCanonicalName());
	
	private User user;
	
	
	private Subject subject;
	private CallbackHandler callbackHandler; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map sharedState; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
	private boolean loginOk;
	private Principal identity;
	private char[] credential;
	
	
	   
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
	    this.callbackHandler = callbackHandler;
	    this.sharedState = sharedState;
	    this.options = options;	 
	}

	@Override
	public boolean login() throws LoginException {
		loginOk = false;
	    getUsernameAndPassword(); 
	    getUser1();
	    validateUser(); 
	    loginOk = true;
	    return true;
	}

	@Override
	public boolean commit() throws LoginException {
		if( loginOk == false )return false;
		Group callerGroup ;
	    callerGroup = new SimpleGroup("CallerPrincipal");
	    callerGroup.addMember(identity);
		Set<Principal> principals = subject.getPrincipals();
	    principals.add(identity);  
	    principals.add(getRoleSets());
	    principals.add(callerGroup); 
	    logger.log(Level.INFO, "LOGIN  COMMIT - OK");
	    return true;
	}
	
	
	

	@Override
	public boolean abort() throws LoginException {
		logger.log(Level.INFO, "LOGIN  ABORT - OK");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		Set<Principal> principals = subject.getPrincipals();
	    principals.remove(identity);
	    principals.clear();
	    if(principals.isEmpty())
	    	logger.log(Level.INFO, "MYLOGIN  LOGOUT OK");
	    return true;
	}
	
	
	
	protected void getUsernameAndPassword() throws LoginException{
	    if( callbackHandler == null ){ throw new LoginException();  } 
	    NameCallback nc = new NameCallback("username");
	    PasswordCallback pc = new PasswordCallback("password: ", false);
	    Callback[] callbacks = {nc, pc};
        try {callbackHandler.handle(callbacks);}
        catch (Exception e) {throw new LoginException();} 
	    identity = new SimplePrincipal(nc.getName());
	    logger.log(Level.INFO, "LOGIN  IDENTY = {0}",nc.getName());
	    char[] tmpPassword = pc.getPassword();
	    if( tmpPassword != null ){
	    	credential = new char[tmpPassword.length];
	    	System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
	        pc.clearPassword();
	    }
	}

	
	
	
	
	protected void getUser1() throws LoginException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jaasLogin");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(  cb.equal(root.get(User_.username), identity.getName()));
		user = em.createQuery(cq).getSingleResult();
		tx.commit();
		em.close();
		emf.close();
		
	}
	
	

	protected Group getRoleSets() throws LoginException {
		SimpleGroup group = new SimpleGroup("Roles");
		group.addMember(new SimplePrincipal(user.getRole().getLabel()));
        return group ;
	}
	
	
	protected void validateUser( ) throws LoginException {
		String password = new String(credential);	
		if(user!=null){
			if( password == null || !password.equals(user.getPassword()) ){
		           throw new LoginException(); 
			}
			else{
				logger.log(Level.INFO, "ADMIN VALIDADO");
				return;
			}
		}		     
    }
	
	
}
