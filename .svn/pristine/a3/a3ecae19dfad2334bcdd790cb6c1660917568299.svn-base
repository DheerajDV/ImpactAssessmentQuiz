package com.iocl.ImpactAssessmentQuiz.login;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ADAuthentication {

	public boolean Authenticate(String user, String pass) throws Exception {
		Hashtable<String, String> env = null;
		DirContext ctx = null;
		try {
			env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "LDAP://dcmkho03:389 LDAP://dcmkho1:389 LDAP://dcmkho2:389 ");
			// env.put(Context.PROVIDER_URL, "LDAP://dcmkho1:389");
			// env.put(Context.PROVIDER_URL, "LDAP://ds.indianoil.in:389");
			env.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");
			env.put(Context.SECURITY_PRINCIPAL, user);
			env.put(Context.SECURITY_CREDENTIALS, pass);
			ctx = new InitialDirContext(env);
			ctx.close();
		} catch (NamingException ne) {
			System.out.println(ne);
			return false;
		} finally {
			env = null;
			ctx = null;
		}
		return true;

	}

}
