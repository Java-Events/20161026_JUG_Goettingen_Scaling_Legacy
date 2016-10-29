package org.rapidpm.event.mapdb.server.services;

import org.mapdb.HTreeMap;
import org.rapidpm.event.mapdb.server.model.StorageMapDB;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class StorageService {


  @Inject private StorageMapDB storageMapDB;

  public List<String> loadRange(Integer startkey, int range) {
    return IntStream
        .range(startkey, startkey + range)
        .boxed()
        .map(storageMapDB.map001()::get)
//        .map(o -> (String) o)
        .collect(Collectors.toList());
  }


  public void fillMapWithAmmount(final Integer startKey, final Integer ammount) {
    final HTreeMap<Integer, String> map001 = storageMapDB.map001();
    IntStream
        .range(startKey, ammount)
        .forEach(e -> map001.put(e, "counted until " + e));
    storageMapDB.commit();
  }
}
