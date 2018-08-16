package com.zz.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.security.InvalidParameterException;
import java.util.Scanner;

import static com.zz.utils.HashService.isEqual;

/** 
* RandomGenerator Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 16, 2018</pre> 
* @version 1.0 
*/ 
public class RandomGeneratorTest { 

    RandomGenerator generator;
@Before
public void before() throws Exception {
    generator=new RandomGenerator();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setSeed(byte[] seed) 
* 
*/ 
@Test
public void testSetSeedSeed() throws Exception { 
//TODO: Test goes here...

        generator.setSeed(new byte[]{});//测试给乱七八糟的种子会不会崩溃
        generator.getNextByte();
        generator.getNextByteArray();
        generator.getNextChar();
        generator.getNextInt();
        generator.getNextString(123);

        RandomGenerator generator=new RandomGenerator();

        generator.setSeed(new String(""));
        generator.getNextByte();
        generator.getNextByteArray();
        generator.getNextChar();
        generator.getNextInt();
        generator.getNextString(123);

    Assert.assertEquals("相同的种子得到了不同的序列",this.generator,generator);
    Assert.assertEquals(
            "相同的种子得到了不同的序列",
            this.generator.getNextString(13),
            generator.getNextString(13));
    Assert.assertEquals("相同的种子得到了不同的序列",this.generator.getNextInt(),generator.getNextInt());
    generator.getNextByte();

    //System.out.println(generator.bytecount);
    Assert.assertTrue("不同的状态得到了相同的序列",!isEqual(this.generator.getNextByteArray(),generator.getNextByteArray()));
    //System.out.println(generator.bytecount);
    Assert.assertNotEquals("不同的状态得到了相同的序列",this.generator,generator);
    //System.out.println(generator);
    byte[] example=new byte[]{
            (byte) 0x26, (byte)0xeb, (byte) 0x3a, (byte)0x52,
            (byte) 0x4e, (byte)0x5a, (byte) 0x74, (byte)0x5f,
            (byte) 0xa4, (byte)0x77, (byte) 0xb8, (byte)0xf5,
            (byte) 0x61, (byte)0x22, (byte) 0xff, (byte)0xc5};
    Assert.assertTrue(isEqual(generator.state,example));
} 

/** 
* 
* Method: setRandomSeed() 
* 
*/ 
@Test
public void testSetRandomSeed() throws Exception {
    generator.setRandomSeed();//测试随机种子
    generator.getNextString(123);

    RandomGenerator generator2=new RandomGenerator();
    generator2.getNextString(123);

    boolean[] result=new boolean[6];
    result[0]=this.generator.getNextByteArray()==generator2.getNextByteArray();
    result[1]=this.generator.getNextChar()==generator2.getNextChar();
    result[2]=this.generator.getNextLetterOrDigit()==generator2.getNextLetterOrDigit();
    result[3]= this.generator.getNextString(12).equals(generator2.getNextString(12));
    result[4]=this.generator.getNextByte()==generator2.getNextByte();
    result[5]=this.generator.getNextInt()==generator2.getNextInt();
    int count=0;
    for(boolean a:result){
        if(a)count++;
    }
    if(count>=2)throw new Exception("随机函数连续发生2次以上碰撞，这个错误如果偶发属于正常，如果多发，随机函数哈希有问题");

}


} 
