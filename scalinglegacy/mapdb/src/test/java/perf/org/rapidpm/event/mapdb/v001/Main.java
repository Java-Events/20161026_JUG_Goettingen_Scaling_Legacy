package perf.org.rapidpm.event.mapdb.v001;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;
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
public class Main {

  public static void main(String[] args) {

    final DB db = DBMaker
        .fileDB("_data/_tmp/file.db")
        .fileMmapEnableIfSupported()
//        .transactionEnable()
        .make();

    final ConcurrentMap<Integer, String> map = (ConcurrentMap<Integer, String>) db
        .hashMap("map001")
        .create();


    IntStream.range(0, 10_000_000)
        .parallel()
        .forEach(e -> map.put(e, "counted until " + e));

    //db.getStore().compact();

    db.close();


  }
}
