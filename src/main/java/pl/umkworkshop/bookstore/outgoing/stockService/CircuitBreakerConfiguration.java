package pl.umkworkshop.bookstore.outgoing.stockService;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "stock-service.circuit-breaker")
public class CircuitBreakerConfiguration {
    private boolean enabled;
    private int failureRateThreshold;
    private int waitDurationInOpenStateInMillis;
    private int permittedNumberOfCallsInHalfOpenState;
    private int slidingWindowSize;
    private int minimumNumberOfCalls;


    @Bean
    public CircuitBreaker stockServiceCircuitBreaker() {
        return CircuitBreaker.of("stockServiceCircuitBreaker", createConfig());
    }

    private CircuitBreakerConfig createConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenStateInMillis))
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .slidingWindow(slidingWindowSize, minimumNumberOfCalls, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED,
                        CircuitBreakerConfig.SlidingWindowSynchronizationStrategy.LOCK_FREE)
                .build();
    }

    // Getters and Setters

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getFailureRateThreshold() {
        return failureRateThreshold;
    }

    public void setFailureRateThreshold(int failureRateThreshold) {
        this.failureRateThreshold = failureRateThreshold;
    }

    public int getWaitDurationInOpenStateInMillis() {
        return waitDurationInOpenStateInMillis;
    }

    public void setWaitDurationInOpenStateInMillis(int waitDurationInOpenStateInMillis) {
        this.waitDurationInOpenStateInMillis = waitDurationInOpenStateInMillis;
    }

    public int getPermittedNumberOfCallsInHalfOpenState() {
        return permittedNumberOfCallsInHalfOpenState;
    }

    public void setPermittedNumberOfCallsInHalfOpenState(int permittedNumberOfCallsInHalfOpenState) {
        this.permittedNumberOfCallsInHalfOpenState = permittedNumberOfCallsInHalfOpenState;
    }

    public int getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public int getMinimumNumberOfCalls() {
        return minimumNumberOfCalls;
    }

    public void setMinimumNumberOfCalls(int minimumNumberOfCalls) {
        this.minimumNumberOfCalls = minimumNumberOfCalls;
    }
}