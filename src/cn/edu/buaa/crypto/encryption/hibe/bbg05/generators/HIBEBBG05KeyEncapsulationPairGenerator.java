package cn.edu.buaa.crypto.encryption.hibe.bbg05.generators;

import cn.edu.buaa.crypto.Utils;
import cn.edu.buaa.crypto.encryption.hibe.bbg05.params.HIBEBBG05CiphertextParameters;
import cn.edu.buaa.crypto.encryption.hibe.bbg05.params.HIBEBBG05PairingKeyEncapsulationPairGenerationParameters;
import cn.edu.buaa.crypto.encryption.hibe.bbg05.params.HIBEBBG05PublicKeyParameters;
import cn.edu.buaa.crypto.pairingkem.generator.PairingKeyEncapsulationPairGenerator;
import cn.edu.buaa.crypto.pairingkem.params.PairingKeyEncapsulationPair;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.bouncycastle.crypto.CipherParameters;

import java.util.Arrays;

/**
 * Created by Weiran Liu on 2015/11/3.
 */
public class HIBEBBG05KeyEncapsulationPairGenerator implements PairingKeyEncapsulationPairGenerator {
    private HIBEBBG05PairingKeyEncapsulationPairGenerationParameters params;

    @Override
    public void init(CipherParameters params) {
        this.params = (HIBEBBG05PairingKeyEncapsulationPairGenerationParameters)params;
    }

    @Override
    public PairingKeyEncapsulationPair generateEncryptionPair() {
        HIBEBBG05PublicKeyParameters publicKeyParameters = this.params.getPublicKeyParameters();
        Pairing pairing = PairingFactory.getPairing(publicKeyParameters.getParameters());
        String[] ids = this.params.getIds();
        Element[] elementIds = Utils.MapToZr(pairing, ids);

        Element s = pairing.getZr().newRandomElement().getImmutable();
        Element sessionKey = pairing.pairing(publicKeyParameters.getG1(), publicKeyParameters.getG2()).powZn(s).getImmutable();
        byte[] byteArraySessionKey = sessionKey.toBytes();

        Element B = publicKeyParameters.getG().powZn(s).getImmutable();
        Element C = publicKeyParameters.getG3().getImmutable();
        for (int i=0; i<this.params.getLength(); i++){
            C = C.mul(publicKeyParameters.getHsAt(i).powZn(elementIds[i])).getImmutable();
        }
        C = C.powZn(s).getImmutable();
        return new PairingKeyEncapsulationPair(
                Arrays.copyOf(byteArraySessionKey, byteArraySessionKey.length),
                new HIBEBBG05CiphertextParameters(publicKeyParameters.getParameters(), ids.length, B, C));
    }
}
