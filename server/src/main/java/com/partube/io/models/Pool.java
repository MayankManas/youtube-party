package com.partube.io.models;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.graph.Graph;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.security.Key;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
@Builder
public class Pool implements Serializable {
  private UUID poolId;
  private UUID admin;
  private String poolName;
  private HashMap<UUID, User> users;
//  private LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
//          .maximumSize(10000)
//          .expireAfterWrite(10, TimeUnit.MINUTES)
//          .build(
//                  new CacheLoader<Key, Graph>() {
//                    public Graph load(Key key) {
//                      return createExpensiveGraph(key);
//                    }
//                  });
  public static PoolBuilder builder() {
    return new PoolBuilder();
  }
}
