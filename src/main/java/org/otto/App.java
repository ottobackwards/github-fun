/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.otto;

import java.io.IOException;
import java.util.List;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class App {

  /**
   * To start, test out just getting all the metron pr's and authors.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    try {
      GitHub gitHub = GitHub.connectAnonymously();
      GHRepository metron = gitHub.getRepository("apache/metron");
      List<GHPullRequest> prs = metron.getPullRequests(GHIssueState.OPEN);
      prs.forEach((pr) -> {
        try {
          System.out.println("-------------------------------");
          System.out.println(pr.getTitle());
          System.out.println(pr.getUser().getLogin());
          System.out.println("-------------------------------");
        } catch (IOException ioe) {
          throw new RuntimeException(ioe);
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
}
