//package io.github.lh83mail.thallo.gateway.config;
//
//import com.alicp.jetcache.anno.support.GlobalCacheConfig;
//import com.alicp.jetcache.anno.support.SpringConfigProvider;
//import com.alicp.jetcache.autoconfigure.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Import;
//
//import javax.annotation.PostConstruct;
//
//@Configuration
//@EnableConfigurationProperties(JetCacheProperties.class)
//public class RedisConfig {
//    private AutoConfigureBeans _autoConfigureBeans = new AutoConfigureBeans();
//
//
//    @Bean
//    public AutoConfigureBeans autoConfigureBeans() {
//        return _autoConfigureBeans;
//    }
//
//    public static final String GLOBAL_CACHE_CONFIG_NAME = "globalCacheConfig";
//    private GlobalCacheConfig _globalCacheConfig;
//
//    @Bean(name = GLOBAL_CACHE_CONFIG_NAME)
//    public GlobalCacheConfig globalCacheConfig(JetCacheProperties props) {
//        if (_globalCacheConfig != null) {
//            return _globalCacheConfig;
//        }
//        _globalCacheConfig = new GlobalCacheConfig();
//        _globalCacheConfig.setHiddenPackages(props.getHiddenPackages());
//        _globalCacheConfig.setStatIntervalMinutes(props.getStatIntervalMinutes());
//        _globalCacheConfig.setAreaInCacheName(props.isAreaInCacheName());
//        _globalCacheConfig.setPenetrationProtect(props.isPenetrationProtect());
//        _globalCacheConfig.setEnableMethodCache(props.isEnableMethodCache());
//        return _globalCacheConfig;
//    }
//
//
//    @Configuration
//    @DependsOn(GLOBAL_CACHE_CONFIG_NAME)
//    public static class InnerConfig {
//        @Bean
//        public static BeanDependencyManager beanDependencyManager(){
//            return new BeanDependencyManager();
//        }
//
//
//        @Bean
//        public SpringConfigProvider springConfigProvider() {
//            return new SpringConfigProvider();
//        }
//
//
//    }
//
//    @DependsOn(GLOBAL_CACHE_CONFIG_NAME)
//    @Configuration
//    @Import({RedisAutoConfiguration.class,
//            CaffeineAutoConfiguration.class,
//            MockRemoteCacheAutoConfiguration.class,
//            LinkedHashMapAutoConfiguration.class,
//            RedisLettuceAutoConfiguration.class,
//            RedisSpringDataAutoConfiguration.class})
//    public static class My {
//        @Autowired
//        private GlobalCacheConfig globalCacheConfig;
//        @Autowired
//        AutoConfigureBeans autoConfigureBeans;
//
//        @PostConstruct
//        public void post() {
//            globalCacheConfig.setLocalCacheBuilders(autoConfigureBeans.getLocalCacheBuilders());
//            globalCacheConfig.setRemoteCacheBuilders(autoConfigureBeans.getRemoteCacheBuilders());
//        }
//    }
//
//}
