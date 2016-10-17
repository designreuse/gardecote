package com.gardecote;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.license4j.License;

import org.springframework.context.annotation.ScopedProxyMode;
@Component
@Scope(value="session", proxyMode =ScopedProxyMode.TARGET_CLASS)

public class LicenceAc {
private License licAc;

public LicenceAc() {
	super();
	// TODO Auto-generated constructor stub
	
}

public License getLicAc() {
	return this.licAc;
}

public void setLicAc(License licAc1) {
	this.licAc = licAc1;
}


}
