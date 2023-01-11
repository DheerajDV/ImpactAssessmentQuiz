package com.iocl.ImpactAssessmentQuiz.configuration;

//import java.util.Arrays;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Value("${ad.domain}")
//	private String AD_DOMAIN;
//
//	@Value("${ad.url}")
//	private String AD_URL;

//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
//	}

//	@Bean
//	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN,
//				AD_URL);
//		provider.setConvertSubErrorCodesToExceptions(true);
//		provider.setUseAuthenticationRequestCredentials(true);
//
//		return provider;
//	}

//	@Bean(BeanIds.AUTHENTICATION_MANAGER)
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//
//		authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider())
//				.userDetailsService(userDetailsService());
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/");
		http.sessionManagement().invalidSessionUrl("/");
		http.sessionManagement().sessionFixation().migrateSession();
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");

//		http.headers().frameOptions().sameOrigin();

	}

	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
}