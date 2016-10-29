package org.rapidpm.event.mapdb.server.rest;

import com.google.gson.Gson;
import org.rapidpm.event.mapdb.server.services.StorageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 27.10.16.
 */

@Path("/store")
public class Store {
  public static final String KEY = "key";
  public static final String RANGE = "range";
  public static final String GET = "get";
  public static final String FILLMAP = "fillmap";
  private static final String AMMOUNT = "ammount";
  private static final String STARTKEY = "startkey";

  // crypted values -> AES -> json

  @Inject private StorageService storageService;

  @GET()
  @Path(GET + "/{" + KEY + "}/{" + RANGE + "}")
  @Produces(MediaType.APPLICATION_JSON)
  public String get(@PathParam(KEY) final String key, @PathParam(RANGE) final String range) {
    final LocalDateTime nowStart = LocalDateTime.now();
    final int parseIntKey = Integer.parseInt(key);
    final int parseIntRange = Integer.parseInt(range);
    final List<String> loadRange = storageService.loadRange(parseIntKey, parseIntRange);
    final LocalDateTime nowStop = LocalDateTime.now();
    final Duration between = Duration.between(nowStart, nowStop);
    System.out.println("between = " + between);
    return new Gson().toJson(loadRange);
  }

  @GET()
  @Path(FILLMAP + "/{" + STARTKEY + "}/{" + AMMOUNT + "}")
  @Produces("text/plain")
  public String createNewMap(@PathParam(STARTKEY) final Integer startKey,
                             @PathParam(AMMOUNT) final Integer ammount) {
    storageService.fillMapWithAmmount(startKey, ammount);
    return "DONE";
  }


}
