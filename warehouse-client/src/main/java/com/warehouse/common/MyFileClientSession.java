package com.warehouse.common;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class MyFileClientSession implements PhaseListener, Serializable {

	private static final long serialVersionUID = -713479805697942219L;

	@Override
	public void afterPhase(PhaseEvent pe) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CURRENT_TIME", System.currentTimeMillis());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		Calendar inOneMonth = Calendar.getInstance();
		inOneMonth.add(Calendar.MONTH, 1);
		response.setDateHeader("Expires", inOneMonth.getTimeInMillis());
		response.setHeader("Pragma", "");
		response.setHeader("Content-Type", "text/*,image/*,application/*");
		response.setHeader("Vary", "Accept-Encoding");
		response.setHeader("Accept-Encoding", "gzip,deflate");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}