package com.project.daox;

import java.util.List;

public class DaoBean {

	private String query;
	private List<DaoParam> params;

	public DaoBean() {
		super();
	}

	public DaoBean(String query, List<DaoParam> params) {
		super();
		this.query = query;
		this.params = params;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<DaoParam> getParams() {
		return params;
	}

	public void setParams(List<DaoParam> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "DaoBean [query=" + query + ", params=" + params + "]";
	}

}
