package net.jmesnil;

import java.util.Random;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;


@Health
public class HealthProbe implements HealthCheck {

    @Inject
    @ConfigProperty(name="app.health.ratio", defaultValue = "0.8")
    private double healthRatio;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder check = HealthCheckResponse.named("app.healthCheck");
        double val = (new Random()).nextDouble();
        if (val <= healthRatio) {
            return check
                    .withData("failure", Double.toString(val))
                    .withData("ratio", Double.toString(healthRatio))
                    .down().build();
        }
        return check.up().build();
    }
}