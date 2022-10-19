package com.neo.configuration;

import com.neo.SourceWrapper;
import com.neo.properties.NeoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@ConditionalOnProperty(name = "switch", havingValue = "on")
@Configuration
public class AutoConfigure {
    @Autowired
    private NeoProperties neoProperties;

    @Bean
    public SourceWrapper initStarter() {
        SourceWrapper sw=new SourceWrapper();
        if(neoProperties.getName()!=null){
            sw.setName(neoProperties.getName());
        }else {
            sw.setName("default name");
        }
        if(neoProperties.getText()!=null){
            sw.setText(neoProperties.getText());
        }else {
            sw.setText("default Text");
        }
        System.out.println("=============================================");
        System.out.println("this initStarter init value is "+sw.toString());
        return sw;
    }

}