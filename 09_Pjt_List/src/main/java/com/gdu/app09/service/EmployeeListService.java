package com.gdu.app09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {

	public void getEmployeeListUsingPagination(HttpServletRequest requeset, Model model);
	public Map<String, Object> getEmployeeListUsingScroll(HttpServletRequest request);
	public void getEmployeeListUsingSearch(HttpServletRequest requeset, Model model);
	public Map<String, Object> getAutoComplete(HttpServletRequest request);
	
}
