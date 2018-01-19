package com.project.daox.handlers;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DaoxTagsHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("bean", new BeanTagHandler2());
	}

}
