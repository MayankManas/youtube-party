package com.partube.io.service;

import com.google.gson.Gson;
import com.partube.io.models.Pool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
public class ManagePools {
  @Autowired ManageUsers userManager;

  private HashMap<UUID, Pool> poolMap = new HashMap<>();

  public void createPool(UUID admin) {
    UUID poolId = UUID.randomUUID();
    Pool private_pool =
        Pool.builder().poolName("private pool#" + admin).poolId(poolId).admin(admin).build();
    log.info("pool created with name {}", private_pool.getPoolName());
    poolMap.put(poolId, private_pool);
  }

  public void updatePoolMap(UUID targetPoolId, Pool pool) {
    log.info("PoolMap with poolId ", targetPoolId, " updated!");
    poolMap.put(targetPoolId, pool);
  }

  public boolean addUserToPool(UUID poolId, UUID hostId, UUID invitee) {
    Pool currPool = poolMap.get(poolId);
    if (currPool.getAdmin() == hostId) {
      if (userManager.getUserMap().containsKey(invitee)) {
        currPool.getUsers().put(invitee, userManager.getUserMap().get(invitee));
        log.info("User {} added to pool {}", invitee, poolId);
        return true;
      } else {
        log.info("invitee {}", invitee, " is not a valid user");
      }
    }
    log.info("host {}", hostId, " is not admin of the pool {}", poolId);
    return false;
  }

  public Pool getPool(UUID poolId) {
    return poolMap.get(poolId);
  }

  public Object getJsonifiedPoolInfo(UUID poolId) {
    Pool currPool = poolMap.get(poolId);
    Gson gson = new Gson();
    if (currPool != null) {
      return gson.toJson(currPool);
    } else return null;
  }
}
