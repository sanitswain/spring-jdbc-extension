package com.project.daox.handlers;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.project.daox.DaoParam;
import com.project.daox.context.DaoContext;

public class ParamTagHandler extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return ParamFactoryBean.class;
	}

	@Override
	protected void doParse(Element ele, ParserContext ctx, BeanDefinitionBuilder builder) {
		DaoParam obj = new DaoParam();

		try {
			String pname = ele.getAttribute("name");
			String sqlType = ele.getAttribute("sqltype");
			String mapper = ele.getAttribute("mapper");

			obj.setName(pname);
			if (mapper != null) {
				obj.setMapper(mapper);
			}

			FieldRetrievingFactoryBean sqlTypeBean = (FieldRetrievingFactoryBean) DaoContext.getBean(sqlType);
			int type = (Integer) sqlTypeBean.getObject();
			obj.setType(type);

		} catch (Exception e) {
			e.printStackTrace();
		}

		builder.addPropertyValue("param", obj);
	}

	public static class ParamFactoryBean implements FactoryBean<DaoParam> {

		private DaoParam param;

		public DaoParam getObject() throws Exception {
			return this.param;
		}

		public Class<DaoParam> getObjectType() {
			return DaoParam.class;
		}

		public boolean isSingleton() {
			return false;
		}

		public void setParam(DaoParam param) {
			this.param = param;
		}

	}

}
