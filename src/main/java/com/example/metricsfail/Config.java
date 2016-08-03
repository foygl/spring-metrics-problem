package com.example.metricsfail;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    // Note: If we export this bean, the problem goes away.
//    @Bean
//    @ExportMetricWriter
//    MetricWriter metricWriter() {
//        return new StatsdMetricWriter("prefix-", "localhost", 8125);
//    }
}
