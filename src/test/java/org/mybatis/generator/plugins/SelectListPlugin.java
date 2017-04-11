package org.mybatis.generator.plugins;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * <pre>
 * add pagination using mysql limit. 
 * This class is only used in ibator code generator.
 * </pre>
 */
public class SelectListPlugin extends PluginAdapter {

	/*
	 * @Override public boolean modelExampleClassGenerated(TopLevelClass
	 * topLevelClass, IntrospectedTable introspectedTable) { // add field,
	 * getter, setter for limit clause addLimit(topLevelClass,
	 * introspectedTable, "limitStart"); addLimit(topLevelClass,
	 * introspectedTable, "limitEnd"); return
	 * super.modelExampleClassGenerated(topLevelClass, introspectedTable); }
	 */

	/**
	 * 重写（修改了） selectByExample 域的默认生成内容
	 */
	/*
	 * @Override public boolean
	 * sqlMapSelectByExampleWithoutBLOBsElementGenerated( XmlElement element,
	 * IntrospectedTable introspectedTable) { XmlElement
	 * isParameterPresenteElemen = (XmlElement) element
	 * .getElements().get(element.getElements().size() - 1); XmlElement
	 * isNotNullElement = new XmlElement("isGreaterEqual"); //$NON-NLS-1$
	 * isNotNullElement.addAttribute(new Attribute("property", "limitStart"));
	 * //$NON-NLS-1$ //$NON-NLS-2$ isNotNullElement.addAttribute(new
	 * Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
	 * isNotNullElement.addElement(new TextElement(
	 * "limit $limitStart$ , $limitEnd$"));
	 * isParameterPresenteElemen.addElement(isNotNullElement); return
	 * super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
	 * introspectedTable); }
	 */

	/*
	 * private void addLimit(TopLevelClass topLevelClass, IntrospectedTable
	 * introspectedTable, String name) { CommentGenerator commentGenerator =
	 * context.getCommentGenerator(); Field field = new Field();
	 * field.setVisibility(JavaVisibility.PROTECTED);
	 * field.setType(FullyQualifiedJavaType.getIntInstance());
	 * field.setName(name); field.setInitializationString("-1");
	 * commentGenerator.addFieldComment(field, introspectedTable);
	 * topLevelClass.addField(field); char c = name.charAt(0); String camel =
	 * Character.toUpperCase(c) + name.substring(1); Method method = new
	 * Method(); method.setVisibility(JavaVisibility.PUBLIC);
	 * method.setName("set" + camel); method.addParameter(new
	 * Parameter(FullyQualifiedJavaType .getIntInstance(), name));
	 * method.addBodyLine("this." + name + "=" + name + ";");
	 * commentGenerator.addGeneralMethodComment(method, introspectedTable);
	 * topLevelClass.addMethod(method); method = new Method();
	 * method.setVisibility(JavaVisibility.PUBLIC);
	 * method.setReturnType(FullyQualifiedJavaType.getIntInstance());
	 * method.setName("get" + camel); method.addBodyLine("return " + name +
	 * ";"); commentGenerator.addGeneralMethodComment(method,
	 * introspectedTable); topLevelClass.addMethod(method); }
	 */

	/**
	 * 增加接口方法
	 */
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(String.class
				.getName());
		importedTypes.add(type);
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = FullyQualifiedJavaType
				.getNewListInstance();
		FullyQualifiedJavaType listType;
		if (introspectedTable.getRules().generateBaseRecordClass()) {
			listType = new FullyQualifiedJavaType(introspectedTable
					.getBaseRecordType());
		} else if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			listType = new FullyQualifiedJavaType(introspectedTable
					.getPrimaryKeyType());
		} else {
			throw new RuntimeException("RuntimeError.12");
		}

		importedTypes.add(listType);
		returnType.addTypeArgument(listType);
		method.setReturnType(returnType);

		method.setName("selectList");
		method.addParameter(new Parameter(type, "sql")); //$NON-NLS-1$   
		
		
		
		Method method1 = new Method();
		method1.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType1 = FullyQualifiedJavaType.getObjectInstance();
		FullyQualifiedJavaType listType1;
		if (introspectedTable.getRules().generateBaseRecordClass()) {
			listType1 = new FullyQualifiedJavaType(introspectedTable
					.getBaseRecordType());
		} else if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			listType1 = new FullyQualifiedJavaType(introspectedTable
					.getPrimaryKeyType());
		} else {
			throw new RuntimeException("RuntimeError.12");
		}
		method1.setReturnType(returnType1);

		
		method1.setName("select"+listType1.getShortName());
		method1.addParameter(new Parameter(type, "sql")); //$NON-NLS-1$   

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
		interfaze.addMethod(method1);

		return true;
	}

	/**
	 * 增加配置文件域
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		// 获取returnType
/*		String returnType = null;
		if (introspectedTable.getRules().generateBaseRecordClass()) {
			returnType = introspectedTable.getBaseRecordType();
		} else {
			returnType = introspectedTable.getPrimaryKeyType();
		}*/

		FullyQualifiedJavaType listType1;
		if (introspectedTable.getRules().generateBaseRecordClass()) {
			listType1 = new FullyQualifiedJavaType(introspectedTable
					.getBaseRecordType());
		} else if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			listType1 = new FullyQualifiedJavaType(introspectedTable
					.getPrimaryKeyType());
		} else {
			throw new RuntimeException("RuntimeError.12");
		}
		
		// 新建一个select Statement
		XmlElement answer = new XmlElement("select");
		answer.addAttribute(new Attribute("id", "selectList"));
		answer.addAttribute(new Attribute("parameterType", "java.lang.String"));
		answer.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		answer.addElement(new TextElement("<![CDATA[ ${value} ]]>"));
		
		
		// 新建一个select Statement
		XmlElement answer1 = new XmlElement("select");
		answer1.addAttribute(new Attribute("id", "select"+listType1.getShortName()));
		answer1.addAttribute(new Attribute("parameterType", "java.lang.String"));
		answer1.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		answer1.addElement(new TextElement("<![CDATA[ ${value} ]]>"));

		XmlElement parentElement = document.getRootElement();
		parentElement.addElement(answer);
		parentElement.addElement(answer1);
		return true;
	}

	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}
}
