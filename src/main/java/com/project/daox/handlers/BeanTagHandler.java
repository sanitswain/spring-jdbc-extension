package com.project.daox.handlers;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import com.project.daox.DaoBean;

public class BeanTagHandler extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return DaoBeanFactoryBean.class;
	}

	@Override
	protected void doParse(Element ele, ParserContext ctx, BeanDefinitionBuilder builder) {
		DaoBean bean = new DaoBean();

		String query = ele.getAttribute("query");
		bean.setQuery(query);

		ParserContext rootCtx = new ParserContext(ctx.getReaderContext(), ctx.getDelegate(),
				builder.getBeanDefinition());

		Element paramsEle = DomUtils.getChildElementByTagName(ele, "params");
		if (paramsEle != null) {
			List<Element> paramList = DomUtils.getChildElementsByTagName(paramsEle, "param");
			for (Element param : paramList) {
				ParamTagHandler handler = new ParamTagHandler();
				BeanDefinition def = handler.parse(param, rootCtx);
			}
		}

		builder.addPropertyValue("bean", bean);
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

	}
}
