package com.project.daox;

import org.springframework.jdbc.core.RowMapper;

public class DaoParam {

	public enum ParamMode {
		IN, OUT, INOUT
	}

	private String name;
	private ParamMode mode;
	private int type;
	private RowMapper<?> rowMapper;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String[] arr = name.split(":");
		if (arr.length == 2) {
			this.mode = ParamMode.valueOf(arr[0].toUpperCase());
			this.name = arr[0];
		} else {
			this.name = name;
		}
	}

	public ParamMode getMode() {
		return mode;
	}

	public void setMode(ParamMode mode) {
		this.mode = mode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public RowMapper<?> getRowMapper() {
		return rowMapper;
	}

	public void setMapper(Object mapper) throws Exception {
		if (mapper instanceof String) {
			@SuppressWarnings("unchecked")
			Class<RowMapper<?>> clazz = (Class<RowMapper<?>>) Class.forName(mapper.toString());
			this.rowMapper = clazz.newInstance();
		} else {
			this.rowMapper = (RowMapper<?>) mapper;
		}
	}

	@Override
	public String toString() {
		return "DaoParam [name=" + name + ", mode=" + mode + ", type=" + type + ", rowMapper=" + rowMapper + "]";
	}

}
