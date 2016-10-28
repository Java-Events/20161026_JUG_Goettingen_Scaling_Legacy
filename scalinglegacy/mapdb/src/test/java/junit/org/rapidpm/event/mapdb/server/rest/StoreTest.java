package junit.org.rapidpm.event.mapdb.server.rest;

import com.google.gson.Gson;
import junit.org.rapidpm.event.mapdb.BasicRestTest;
import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.event.mapdb.server.rest.Store;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
public class StoreTest extends BasicRestTest {

  @Test
  public void testApplicationPath() throws Exception {
    Client client = ClientBuilder.newClient();
    final String generateBasicReqURL = generateBasicReqURL(Store.class);
    System.out.println("generateBasicReqURL = " + generateBasicReqURL);
    final int range = 1_000;
    String val = client
        .target(generateBasicReqURL)
        .path(Store.KEY)
        .path(3333 +"")
        .path(range +"")
        .request()
        .get(String.class);
    System.out.println("val = " + val);

    final List<String> fromJson = new Gson().fromJson(val, List.class);

    Assert.assertEquals(fromJson.size(), range);
    client.close();
  }
}
