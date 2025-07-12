package org.afflato.momentum.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "strategy")
public class StrategyProperties {

    private Map<String, String> csv = new HashMap<>();
    private Map<String, String> json = new HashMap<>();

}