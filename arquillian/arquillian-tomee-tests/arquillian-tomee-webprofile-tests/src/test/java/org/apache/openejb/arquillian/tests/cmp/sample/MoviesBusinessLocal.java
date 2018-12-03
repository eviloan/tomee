/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openejb.arquillian.tests.cmp.sample;

import java.rmi.RemoteException;
import java.util.Collection;

public interface MoviesBusinessLocal extends javax.ejb.EJBLocalObject {

    int addMovie(final String title, final String director, int year) throws RemoteException, MovieException;
    int addActor(final int movieId, final String firstName, final String lastName) throws RemoteException, MovieException;
    Movie findByPrimaryKey(final int id) throws RemoteException, MovieException;
    Collection findAll() throws RemoteException, MovieException;
    void delete(Integer id) throws RemoteException, MovieException;
}
