/*
 * Copyright (C) 2017 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.julienviet.pgclient.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.function.Function;

public interface Connection {

  void init(Holder holder);

  boolean isSsl();

  default void schedule(CommandBase cmd) {
    schedule(cmd, null);
  }

  void schedule(CommandBase cmd, Handler<Void> completionHandler);

  default void schedulePrepared(String sql, Function<AsyncResult<PreparedStatement>, CommandBase> supplier) {
    schedulePrepared(sql, supplier, null);
  }

  void schedulePrepared(String sql, Function<AsyncResult<PreparedStatement>, CommandBase> supplier, Handler<Void> completionHandler);

  void close(Holder holder);

  interface Holder {

    Connection connection();

    void handleClosed();

    void handleException(Throwable err);

  }
}
