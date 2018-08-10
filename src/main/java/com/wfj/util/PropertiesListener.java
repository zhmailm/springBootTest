package com.wfj.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;


/**
 * 配置文件监听器，用来加载自定义配置文件
 * 
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:38:25 
 * @version V1.0
 * @since JDK ： 1.7
 */
public class PropertiesListener implements ApplicationListener<ApplicationEvent> {

    private String propertyFileName;

    public PropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		PropertiesListenerConfig.loadAllProperties(propertyFileName);
		
	}
}