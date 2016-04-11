package com.warehouse.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
public class NoCacheFilter implements Filter, Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(NoCacheFilter.class);
	private static final long serialVersionUID = -3296164022833156227L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse hsr = (HttpServletResponse) res;
		Calendar inOneMonth = Calendar.getInstance();
		inOneMonth.add(Calendar.MONTH, 1);
		hsr.setDateHeader("Expires", inOneMonth.getTimeInMillis());
		hsr.setHeader("Pragma", " ");
		hsr.setHeader("Content-Type", "text/*,image/*,application/*");
		hsr.setHeader("Vary", "Accept-Encoding");
		hsr.setHeader("Accept-Encoding", "gzip,deflate");
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		LOGGER.info("In NoCacheFilter:destroy");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LOGGER.info("In NoCacheFilter:init");
	}
}