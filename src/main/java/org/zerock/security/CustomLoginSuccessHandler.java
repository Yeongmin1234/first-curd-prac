package org.zerock.security;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.log4j.Log4j;
 
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
 
	
	 private RequestCache requestCache = new HttpSessionRequestCache();
	 private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	 
	 private String loginidname;
	 private String defaultUrl;


	 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
    	resultRedirectStrategy(request, response, authentication);
    	
    }
	 public String getLoginidname() {
	        return loginidname;
	    }
	 
	    public void setLoginidname(String loginidname) {
	        this.loginidname = loginidname;
	    }
	 
	    public String getDefaultUrl() {
	        return defaultUrl;
	    }
	 
	    public void setDefaultUrl(String defaultUrl) {
	        this.defaultUrl = defaultUrl;
	    }
	    
    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
	    	
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStratgy.sendRedirect(request, response, targetUrl);
        } else {
            redirectStratgy.sendRedirect(request, response, defaultUrl);
        }
        
    }

 
}
 
