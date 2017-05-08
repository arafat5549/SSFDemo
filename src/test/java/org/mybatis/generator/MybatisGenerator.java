package org.mybatis.generator;

import java.net.URISyntaxException;

import org.mybatis.generator.api.ShellRunner;

/**
 * <pre>
 * add pagination using mysql limit. 
 * This class is only used in ibator code generator.
 * </pre>
 */
public class MybatisGenerator {
	public static void main(String[] args) {
		System.out.println("开始生成代码...");
		MybatisGenerator t = new MybatisGenerator();
		String config = "generatorConfiguration.xml";
		try {
			config = t.getClass().getResource(config).toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.out.println("完整的配置文件路径：" + config);
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
		System.out.println("完成。");
	}
}
