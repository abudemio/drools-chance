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

package org.drools.pmml_4_0.global;


import org.drools.definition.KnowledgePackage;
import org.drools.pmml_4_0.DroolsAbstractPMMLTest;
import org.drools.pmml_4_0.PMML4Compiler;
import org.drools.pmml_4_0.PMML4Wrapper;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;


public class HeaderTest extends DroolsAbstractPMMLTest {





    @Test
    public void testPMMLHeader() {
        String source = "test_header.xml";


		PMML4Wrapper wrapper = new PMML4Wrapper();
			wrapper.setPack("org.drools.pmml_4_0.test");

        boolean header = false;
        boolean timestamp = false;
        boolean appl = false;
        boolean descr = false;
        boolean copyright = false;
        boolean annotation = false;

		String theory = new PMML4Compiler().compile(source,null);
        BufferedReader reader = new BufferedReader(new StringReader(theory));
        try {
            String line = "";
            while ((line=reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("// Imported PMML Model Theory")) header = true;
                else if (line.startsWith("// Creation timestamp :")) timestamp = line.contains("now");
                else if (line.startsWith("// Description :")) descr = line.contains("test");
                else if (line.startsWith("// Copyright :")) copyright = line.contains("opensource");
                else if (line.startsWith("// Annotation :")) annotation = line.contains("notes here");
                else if (line.startsWith("// Trained with :")) appl = line.contains("handmade");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fail();
        }
        assertTrue(header);
        assertTrue(timestamp);
        assertTrue(descr);
        assertTrue(copyright);
        assertTrue(annotation);
        assertTrue(appl);



        StatefulKnowledgeSession ksession = getSession(theory);
        KnowledgePackage pack = ksession.getKnowledgeBase().getKnowledgePackage("org.drools.pmml_4_0.test");
        assertNotNull(pack);

    }



}