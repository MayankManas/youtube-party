package com.partube.io.service;

import com.partube.io.models.Pool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class ManagePools {
  private Set<Pool> pools = new HashSet<>();

  public Object createPool(UUID admin) {
    Pool private_pool = Pool.builder().poolName("private pool").admin(admin).build();
    log.info("pool created with name {}", private_pool.getPoolName());
    pools.add(private_pool);
    return private_pool;
  }
}
