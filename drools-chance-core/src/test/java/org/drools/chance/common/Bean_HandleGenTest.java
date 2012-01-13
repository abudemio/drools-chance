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

package org.drools.chance.common;

import org.drools.chance.common.fact.Weight;
import org.drools.chance.degree.DegreeTypeRegistry;
import org.drools.chance.degree.simple.SimpleDegree;
import org.drools.chance.distribution.IDistributionStrategyFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


@Deprecated
public class Bean_HandleGenTest {

    @Before
    public void setUp() throws Exception {
        IDistributionStrategyFactory factory = (IDistributionStrategyFactory) Class.forName("org.drools.chance.distribution.probability.discrete.DiscreteDistributionStrategyFactory").newInstance();
        ChanceStrategyFactory.register(factory.getImp_Kind(), factory.getImp_Model(), factory);


        IDistributionStrategyFactory factory2 = (IDistributionStrategyFactory) Class.forName("org.drools.chance.distribution.probability.dirichlet.DirichletDistributionStrategyFactory").newInstance();
        ChanceStrategyFactory.register(factory2.getImp_Kind(), factory2.getImp_Model(), factory2);

        
        IDistributionStrategyFactory factory3 = (IDistributionStrategyFactory) Class.forName("org.drools.chance.distribution.fuzzy.linguistic.ShapedFuzzyPartitionStrategyFactory").newInstance();
        ChanceStrategyFactory.register(factory3.getImp_Kind(), factory3.getImp_Model(), factory3);

        IDistributionStrategyFactory factory4 = (IDistributionStrategyFactory) Class.forName("org.drools.chance.distribution.fuzzy.linguistic.LinguisticPossibilityDistributionStrategyFactory").newInstance();
        ChanceStrategyFactory.register(factory4.getImp_Kind(), factory3.getImp_Model(), factory4);

        
        DegreeTypeRegistry.getSingleInstance().registerDegreeType("simple",SimpleDegree.class);
    }


    @After
    public void tearDown() throws Exception {

    }





    @Test
    @Ignore
    public  void testHandle() throws Exception {



        System.out.println("We have a null bean");
        Bean b = new Bean();

        junit.framework.TestCase.assertNull(b.getAge());
        junit.framework.TestCase.assertNull(b.getName());


        System.out.println(b);
        System.out.println("\n---------------------------");

        System.out.println("Setting a handle. The handle has meta-field with prior probabilities: the bean now reflects that");
        Bean_HandleGen bh = new Bean_HandleGen(b);


        System.out.println(b);
        System.out.println(bh);

        junit.framework.TestCase.assertEquals(20, bh.getAgeValue().intValue());
        junit.framework.TestCase.assertEquals("philip",bh.getFieldValue());

        junit.framework.TestCase.assertEquals(20, b.getAge().intValue());
        junit.framework.TestCase.assertEquals("philip",b.getName());
        
        
        junit.framework.TestCase.assertEquals(50,b.getWeight(),1e-02);
        junit.framework.TestCase.assertEquals(Weight.FAT,b.getBody());
        


        System.out.println("---------------------------");
        System.out.println("Now, setting/updating HANDLE values : field (name)=john, age ++=18, weight=20.0. NOT body...");
        bh.setField("john");
        bh.updateAge(18);
        bh.setWeight(20.0);

        junit.framework.TestCase.assertEquals(18, b.getAge().intValue());
        junit.framework.TestCase.assertEquals("john",b.getName());

        junit.framework.TestCase.assertEquals(18, bh.getAgeValue().intValue());
        junit.framework.TestCase.assertEquals("john",bh.getFieldValue());
        
        
        
        junit.framework.TestCase.assertEquals(20.0,b.getWeight());
        junit.framework.TestCase.assertEquals(Weight.SLIM,b.getBody());
        junit.framework.TestCase.assertEquals(0.8, bh.getBody().getDegree(Weight.SLIM).getValue());
        junit.framework.TestCase.assertEquals(0.2,bh.getBody().getDegree(Weight.FAT).getValue());
        


        System.out.println("yet, some merging was done ... (Notice body)");
        System.out.println(b);
        System.out.println(bh);
        
        System.out.println("---------------------------");
        System.out.println("Now, time to set body=FAT. linguistic. See weight... ");


        
        bh.setBody(Weight.FAT);
        junit.framework.TestCase.assertEquals(1.0,bh.getBody().getDegree(Weight.FAT).getValue());
        junit.framework.TestCase.assertEquals(0.0,bh.getBody().getDegree(Weight.SLIM).getValue());
        
        junit.framework.TestCase.assertEquals(100.0,b.getWeight(),1e-2);
        junit.framework.TestCase.assertEquals(100.0,bh.getWeight(),1e-2);
        

        System.out.println("After more changes:");
        System.out.println(b);
        System.out.println(bh);
    }






    public void testGetBean() throws Exception {

    }

    public void testGetFieldHistory() throws Exception {

    }

    public void testGetField() throws Exception {

    }

    public void testGetFieldValue() throws Exception {

    }

    public void testSetField() throws Exception {

    }



    public void testUpdateField() throws Exception {

    }

    public void testGetAgeHistory() throws Exception {

    }

    public void testGetAge() throws Exception {

    }

    public void testGetAgeValue() throws Exception {

    }

    public void testSetAge() throws Exception {

    }



    public void testUpdateAge() throws Exception {

    }
}
