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

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class MovieBean implements EntityBean {

    public MovieBean() {
    }

    public Integer ejbCreate(final String director, String title, final int year) {
        this.setDirector(director);
        this.setTitle(title);
        this.setYear(year);
        return null;
    }

    public abstract java.lang.Integer getId();

    public abstract void setId(java.lang.Integer id);

    public abstract String getDirector();

    public abstract void setDirector(String director);

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract int getYear();

    public abstract void setYear(int year);

    public abstract Collection getActors();

    public abstract void setActors(Collection actors);

    public void addActor(String firstName, String lastName) {
        try {
            final InitialContext context = new InitialContext();

            final ActorLocalHome actorBean = (ActorLocalHome) context.lookup("java:comp/env/ejb/ActorBean");
            final Actor actor = actorBean.create(firstName, lastName);

            final Collection actors = this.getActors();
            actors.add(actor);

        } catch (NamingException | CreateException e) {
            throw new EJBException(e);
        }
    }

    public Collection getActorVO() {
        List result = new ArrayList();

        final Collection actors = this.getActors();
        final Iterator iterator = actors.iterator();

        while (iterator.hasNext()) {
            Actor actor = (Actor) iterator.next();
            result.add(ActorVO.from(actor));
        }

        return result;
    }

}
