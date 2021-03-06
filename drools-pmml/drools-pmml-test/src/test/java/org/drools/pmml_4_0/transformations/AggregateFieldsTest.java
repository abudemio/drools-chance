/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.pmml_4_0.transformations;


import org.drools.pmml_4_0.DroolsAbstractPMMLTest;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class AggregateFieldsTest extends DroolsAbstractPMMLTest {

    private static final boolean VERBOSE = false;
    private static final String source = "org/drools/pmml_4_0/test_derived_fields_aggregate.xml";
    private static final String packageName = "org.drools.pmml_4_0.test";



    @Before
    public void setUp() throws Exception {
        setKSession(getModelSession(source, VERBOSE));
        setKbase(getKSession().getKnowledgeBase());
    }



    @Test
    @Ignore
    //FIXME I used to keep all null-context fields... but then memory would blow up. Now I keep only the last one, but then I must override that for accumulates...
    public void testAggregate() throws Exception {

        getKSession().getWorkingMemoryEntryPoint("in_Limit").insert(18);

        WorkingMemoryEntryPoint ep = getKSession().getWorkingMemoryEntryPoint("in_Age");
            ep.insert(10);
            ep.insert(20);
            ep.insert(30);
            ep.insert(40);

        getKSession().fireAllRules();

        checkFirstDataFieldOfTypeStatus(getKbase().getFactType(packageName,"Summa"),true,false, null,90.0);

    }





}
