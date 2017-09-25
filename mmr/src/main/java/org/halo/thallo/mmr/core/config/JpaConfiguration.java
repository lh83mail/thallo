package org.halo.thallo.mmr.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by dell01 on 2017/9/30.
 */
@EnableJpaRepositories("org.halo.thallo.mmr.core.repositories")
@EntityScan("org.halo.thallo.mmr.core.entity")
public class JpaConfiguration {
}
