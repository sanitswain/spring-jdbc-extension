package com.project.daox.handlers;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import com.project.daox.DaoBean;
import com.project.daox.DaoParam;

public class BeanTagHandler2 extends AbstractBeanDefinitionParser {

	protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
		return parseBeanElement(element);
	}

	private static AbstractBeanDefinition parseBeanElement(Element element) {
		BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(DaoBeanFactoryBean.class);
		factory.addPropertyValue("bean", parseBean(element));

		Element params = DomUtils.getChildElementByTagName(element, "params");

		List<Element> paramList = DomUtils.getChildElementsByTagName(params, "component");
		if (paramList != null && paramList.size() > 0) {
			parseParamList(paramList, factory);
		}

		return factory.getBeanDefinition();
	}

	private static BeanDefinition parseBean(Element element) {
		BeanDefinitionBuilder component = BeanDefinitionBuilder.rootBeanDefinition(DaoBean.class);
		component.addPropertyValue("query", element.getAttribute("query"));
		return component.getBeanDefinition();
	}

	private static void parseParamList(List<Element> childElements, BeanDefinitionBuilder factory) {
		ManagedList<BeanDefinition> children = new ManagedList<>(childElements.size());

		for (Element element : childElements) {
			// children.add(parseBeanElement(element));
		}

		factory.addPropertyValue("params", children);
	}

	public static class DaoBeanFactoryBean implements FactoryBean<DaoBean> {

		private DaoBean bean;

		public DaoBean getObject() throws Exception {
			return this.bean;
		}

		public Class<DaoBean> getObjectType() {
			return DaoBean.class;
		}

		public boolean isSingleton() {
			return false;
		}

		public void setParam(DaoBean bean) {
			this.bean = bean;
		}

		public void setParams(List<DaoParam> params) {
			bean.setParams(params);
		}

		public void setBean(DaoBean bean) {
			this.bean = bean;
		}

	}
}
