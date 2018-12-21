package com.hxhy.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class MySiteSetting {

	@Value("${com.hxhy.site.host}")
	private String host;
	@Value("${com.hxhy.site.staticHost}")
	private String staticHost;
	@Value("${com.hxhy.site.corpid}")
	private String corpid;
	@Value("${com.hxhy.site.corpsecret}")
	private String corpsecret;
	@Value("${com.hxhy.site.session}")
	private String sessionName;
	
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getStaticHost() {
		return staticHost;
	}
	public void setStaticHost(String staticHost) {
		this.staticHost = staticHost;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getCorpsecret() {
		return corpsecret;
	}
	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}
}
