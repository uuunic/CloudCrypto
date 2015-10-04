package cn.edu.buaa.crypto.encryption.hibe.bb04.params;

import org.bouncycastle.crypto.CipherParameters;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Created by Weiran Liu on 15-10-1.
 */
public class HIBEBB04PairingKeyEncapsulationPairGenerationParameters implements CipherParameters {
    private HIBEBB04PublicKeyParameters publicKeyParameters;
    private String[] ids;

    public HIBEBB04PairingKeyEncapsulationPairGenerationParameters(
            CipherParameters publicKeyParameters, String[] ids) {
        if (!(publicKeyParameters instanceof HIBEBB04PublicKeyParameters)){
            throw new InvalidParameterException
                    ("Invalid CipherParameter Instance of HIBEBB04 Scheme, find "
                            + publicKeyParameters.getClass().getName() + ", require "
                            + HIBEBB04PublicKeyParameters.class.getName());
        }
        this.publicKeyParameters = (HIBEBB04PublicKeyParameters)publicKeyParameters;
        this.ids = Arrays.copyOf(ids, ids.length);
    }

    public HIBEBB04PublicKeyParameters getPublicKeyParameters() {
        return this.publicKeyParameters;
    }

    public String[] getIds() { return Arrays.copyOf(ids, ids.length); }

    public String getIdAt(int index) { return ids[index]; }
}