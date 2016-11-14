package com.gardecote;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;



import org.springframework.stereotype.Component;

import com.license4j.License;
import com.license4j.LicenseValidator;
import com.license4j.ValidationStatus;
import com.license4j.util.FileUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//AuthenticationSuccessHandler {
	  @Autowired
	    HttpSession session;
	  @Autowired
	    private LicenceAc ourLic;
      @Override
    public void onAuthenticationSuccess(HttpServletRequest request,	HttpServletResponse response,Authentication auth)
		throws IOException, ServletException {
	// initialization logic after login
        String licenseString=null;
  		try {
  			licenseString = FileUtils.readFile("C:/AA/L4J/1472791619681.l4j");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
      	String publickeyString=null;
  		try {
  			publickeyString = FileUtils.readFile("C:/AA/L4J/pubkey.l4j");
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	License licenseSS = LicenseValidator.validate(
                   licenseString,          // REQUIRED - license string
                   publickeyString,        // REQUIRED - public key
                   "gestion-tickets-v1",        // REQUIRED - product id
                   "Professional",   // product edition if needed
                   "1.0",   // product version if needed
                   null,                   // current date, null for current date
                   null); 

      	ourLic.setLicAc(licenseSS);
      	System.out.println("licenseSS");
        System.out.println(licenseSS.getValidationStatus());
	// redirect
if (auth != null) {
	  response.setStatus(HttpServletResponse.SC_OK);
	}
//	SavedRequest savedReq = (SavedRequest) session.getAttribute(WebAttributes.SAVED_REQUEST);
  if(licenseSS.getValidationStatus()==ValidationStatus.LICENSE_EXPIRED) {
	 
	  response.setStatus(HttpServletResponse.SC_OK);
	
	    response.sendRedirect(request.getContextPath() + "/start");
	}
	else {
		 session.invalidate();
		response.setStatus(HttpServletResponse.SC_OK);
		 response.sendRedirect(request.getContextPath() + "/licence.html");
	}
    }
}