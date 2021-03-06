package fi.ukkosnetti.symprap.auth;

import java.io.File;
import java.util.Arrays;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.model.UserRole;
import fi.ukkosnetti.symprap.service.UserService;

/**
 *	Configure this web application to use OAuth 2.0.
 *  
 */
@Configuration
public class OAuth2SecurityConfiguration {
	
	@Autowired
	private UserService userDetailsService;
	
	@Value("${adminPass:test}")
	private String adminPass;
	
	
	@SuppressWarnings("unchecked")
	@Bean
	public AuthenticationManager authenticationManager() {
		try {
			userDetailsService.createUser(new UserCreate("adm", adminPass, "Super", "User", null, null, Arrays.asList(UserRole.ADMIN), null));				
			return new AuthenticationManagerBuilder(new NopPostProcessor()).userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()).and().build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
   @SuppressWarnings("rawtypes")
   private static class NopPostProcessor implements ObjectPostProcessor {
        @Override
        public Object postProcess(Object object) {
            return object;
        }
    };
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServer extends
			ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable();
			
			http.authorizeRequests()
				.antMatchers("/oauth/token", "/user/register", "/disease/all")
				.anonymous();
			
			
			http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/answer/**", "/user/**", "/question/**")
				.access("#oauth2.hasScope('read')");
			http.authorizeRequests()
				.antMatchers("/answer/**", "/user/**", "/question/**")
				.access("#oauth2.hasScope('write')")
				.anyRequest()
				.fullyAuthenticated();
			http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/disease/**")				
				.access("#oauth2.hasScope('write')")
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/disease/**")	
				.access("#oauth2.hasScope('write')");
		}

	}

	/**
	 * This class is used to configure how our authorization server (the "/oauth/token" endpoint) 
	 * validates client credentials.
	 */
	@Configuration
	@EnableAuthorizationServer
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@Order(Ordered.LOWEST_PRECEDENCE - 100)
	protected static class OAuth2Config extends
			AuthorizationServerConfigurerAdapter {

		// Delegate the processing of Authentication requests to the framework
		@Autowired
		private AuthenticationManager authenticationManager;

		private ClientDetailsService clientService;

		public OAuth2Config() throws Exception {
			
			// If you were going to reuse this class in another
			// application, this is one of the key sections that you
			// would want to change
			
			
			// Create a service that has the credentials for all our clients
			clientService = new InMemoryClientDetailsServiceBuilder()
					.withClient("admin").authorizedGrantTypes("password")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write")
					.accessTokenValiditySeconds(3600)
					.and()
					.withClient("mobile").authorizedGrantTypes("password")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read","write")
					.accessTokenValiditySeconds(3600).and().build();

		}

		@Bean
		public ClientDetailsService clientDetailsService() throws Exception {
			return clientService;
		}

		/**
		 * This method tells our AuthorizationServerConfigurerAdapter to use the delegated AuthenticationManager
		 * to process authentication requests.
		 */
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		/**
		 * This method tells the AuthorizationServerConfigurerAdapter to use our self-defined client details service to
		 * authenticate clients with.
		 */
		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			clients.withClientDetails(clientDetailsService());
		}

	}
	
	
    // This version uses the Tomcat web container and configures it to
	// support HTTPS. The code below performs the configuration of Tomcat
	// for HTTPS. Each web container has a different API for configuring
	// HTTPS. 
	//
	// The app now requires that you pass the location of the keystore and
	// the password for your private key that you would like to setup HTTPS
	// with. In Eclipse, you can set these options by going to:
	//    1. Run->Run Configurations
	//    2. Under Java Applications, select your run configuration for this app
	//    3. Open the Arguments tab
	//    4. In VM Arguments, provide the following information to use the
	//       default keystore provided with the sample code:
	//
	//       -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit
	//
	//    5. Note, this keystore is highly insecure! If you want more securtiy, you 
	//       should obtain a real SSL certificate:
	//
	//       http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
	//
    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer(
            @Value("${keystore.file:src/main/resources/private/keystore}") String keystoreFile,
            @Value("${keystore.pass:changeit}") final String keystorePass) throws Exception {

		// If you were going to reuse this class in another
		// application, this is one of the key sections that you
		// would want to change
    	
        final String absoluteKeystoreFile = new File(keystoreFile).getAbsolutePath();

        return new EmbeddedServletContainerCustomizer () {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
		            TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
		            tomcat.addConnectorCustomizers(
		                    new TomcatConnectorCustomizer() {
								@Override
								public void customize(Connector connector) {
			                        connector.setSecure(true);
			                        connector.setScheme("https");

			                        Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
			                        proto.setSSLEnabled(true);
			                        proto.setKeystoreFile(absoluteKeystoreFile);
			                        proto.setKeystorePass(keystorePass);
			                        proto.setKeystoreType("JKS");
			                        proto.setKeyAlias("tomcat");
								}
		                    });
		    
			}
        };
    }
	

}
