package cn.edu.buaa.crypto.encryption.hibe.bb04;

import cn.edu.buaa.crypto.encryption.hibe.HIBEEngine;
import cn.edu.buaa.crypto.encryption.hibe.HIBEEngineTest;
import cn.edu.buaa.crypto.encryption.hibe.bb04.serialization.HIBEBB04XMLSerializer;
import cn.edu.buaa.crypto.serialization.CipherParameterXMLSerializer;

/**
 * Created by Weiran Liu on 2015/11/3.
 */
public class HIBEBB04EngineTest {
    public static void main(String[] args) {
        HIBEEngine engine = new HIBEBB04Engine();
        CipherParameterXMLSerializer schemeXMLSerializer = HIBEBB04XMLSerializer.getInstance();

        HIBEEngineTest engineTest = new HIBEEngineTest(engine, schemeXMLSerializer);
        engineTest.processTest(160, 256, 10);
    }
}
