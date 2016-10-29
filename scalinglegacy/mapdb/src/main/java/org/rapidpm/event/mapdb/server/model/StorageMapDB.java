package org.rapidpm.event.mapdb.server.model;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
public class StorageMapDB {

  @PostConstruct
  private void postConstruct() {
    System.out.println("postConstruct => ");

    final Path dataPath = FileSystems.getDefault().getPath("_data", "_tmp");
    try {
      final Path directories = Files.createDirectories(dataPath);
      System.out.printf("directories - " + directories);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (db == null) {
      //map001.close();
      //db.close();
      db = DBMaker
//        .fileDB("_data/_tmp/file.db")
          .fileDB(new File(dataPath.toFile(), "file.db"))
          .transactionEnable()
          .closeOnJvmShutdown()
          .fileDeleteAfterClose()
          .make();

      map001 = (HTreeMap<Integer, String>) db
          .hashMap("map001")
          .createOrOpen();
    }

  }

  public void commit() {
    db.commit();
  }

  public HTreeMap<Integer, String> map001() {
    return map001;
  }

  private HTreeMap<Integer, String> map001;
  private DB db;


}
