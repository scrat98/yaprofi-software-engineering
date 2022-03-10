package golovko.artem.profi.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["golovko.artem.profi.service"])
@EnableJpaRepositories(basePackages = ["golovko.artem.profi.service"])
class JpaConfig
